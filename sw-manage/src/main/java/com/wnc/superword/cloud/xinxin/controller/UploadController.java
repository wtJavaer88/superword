package com.wnc.superword.cloud.xinxin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class UploadController {

	private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value = "/image/upload")
	@ResponseBody
	public String imageUpload(HttpServletRequest request, HttpServletResponse response) {
		String str = upload(request, response);
		String result = "{\"jsonrpc\" : \"2.0\", \"error\" : {\"code\": 101, \"message\": \"Failed to open input stream.\"}, \"id\" : \"id\"}";
		if (str != null) {
			// 上传成功
			result = "{\"jsonrpc\" : \"2.0\", \"result\" : \"success\", \"id\" : \"id\", \"media\" : \"" + str + "\"}";
		}
		return result;
	}

	/**
	 * @param request
	 * @param response
	 */
	private String upload(HttpServletRequest request, HttpServletResponse response) {

		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String originalFilename = file.getOriginalFilename();
					System.out.println("fileName:" + originalFilename);
					String filepath = request.getSession().getServletContext().getRealPath("/") + "backup"
							+ File.separator + originalFilename;
					boolean saveFile = saveFile(file, filepath);
					if (saveFile) {
						System.out.println("保存成功!");
						return "/backup/" + originalFilename;
					}
				}
			}

		}

		return null;
	}

	private boolean saveFile(MultipartFile mpFile, String filePath) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(mpFile.getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}
}
