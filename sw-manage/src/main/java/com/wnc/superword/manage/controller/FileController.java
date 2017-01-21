package com.wnc.superword.manage.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wnc.basic.BasicFileUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.tools.ZipUtils;

@RequestMapping("file")
@Controller
public class FileController {

	@RequestMapping(value = "mbupload", method = RequestMethod.POST)
	public ResponseEntity<String> mbup(@RequestParam("userName") String userName,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			if (file != null) {
				String originalFilename = file.getOriginalFilename();
				System.out.println("fileName:" + originalFilename);
				String filepath = request.getSession().getServletContext().getRealPath("/") + "backup" + File.separator;
				boolean saveFile = saveFile(file, filepath, originalFilename);
				if (saveFile) {
					System.out.println("保存成功!");
					return ResponseEntity.ok("1");
				} else {
					System.out.println("保存失败!");
					return ResponseEntity.ok("0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "download", method = RequestMethod.POST)
	public ResponseEntity<String> download(@RequestParam("day") String day, @RequestParam("count") int count,
			HttpServletRequest request) {
		System.out.println("day:" + day);
		try {
			long currentTimeMillis = System.currentTimeMillis();
			String svrDownDir = request.getSession().getServletContext().getRealPath("/") + "download" + File.separator;
			BasicFileUtil.makeDirectory(svrDownDir);
			String saveZipFile = svrDownDir + currentTimeMillis + ".zip";
			String retzipFile = request.getSession().getServletContext().getContextPath() + "/download/"
					+ currentTimeMillis + ".zip";
			if (BasicStringUtil.isNotNullString(day)) {
				String filepath = request.getSession().getServletContext().getRealPath("/") + "mp3" + File.separator;
				File[] listFiles = new File(filepath).listFiles();
				List<File> resFileList = new ArrayList<File>();
				int i = 0;
				for (File file : listFiles) {
					if (i < count) {
						resFileList.add(file);
					}
					i++;
				}
				if (resFileList.size() > 0) {
					System.out.println("resFileList:" + resFileList);
					ZipUtils.zipFiles(resFileList, new File(saveZipFile));
				}
			}
			return ResponseEntity.ok(retzipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	private boolean saveFile(MultipartFile mpFile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + fileName);
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
