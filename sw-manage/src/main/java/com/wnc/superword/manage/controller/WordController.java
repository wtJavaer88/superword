package com.wnc.superword.manage.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wnc.basic.BasicFileUtil;
import com.wnc.utils.UrlPicDownloader;

import translate.site.iciba.CibaWordTranslate;

@Controller
@RequestMapping(value = "/word")
public class WordController {

	@RequestMapping(value = "/mp3", method = RequestMethod.POST)
	@ResponseBody
	public String scheduleDownload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam String word) {
		response.setCharacterEncoding("UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
			// 获取文件的路径
			String folder = session.getServletContext().getRealPath("/") + "mp3\\";
			String filepath = folder + word + ".mp3";
			if (!BasicFileUtil.isExistFile(folder)) {
				BasicFileUtil.makeDirectory(folder);
			}
			System.out.println(filepath);
			File file = new File(filepath);
			if (!file.exists()) {
				String url = "";
				try {
					CibaWordTranslate translate = new CibaWordTranslate(word);
					url = translate.getSoundStr();
					System.out.println(url);
					UrlPicDownloader.download(url, filepath);
				} catch (Exception e) {
					System.out.println("下载失败:" + url);
				}
			}
			if (file.exists()) {
				if (file.length() > 0)
					return filepath;
				else {
					file.delete();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}