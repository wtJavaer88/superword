package com.wnc.superword.cloud.xinxin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.wnc.basic.BasicDateUtil;
import com.wnc.basic.BasicFileUtil;
import com.wnc.superword.cloud.xinxin.pojo.FootStepInfo;
import com.wnc.superword.cloud.xinxin.pojo.FsMedia;
import com.wnc.superword.cloud.xinxin.service.CloudOperateService;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.tools.FileOp;
import com.wnc.tools.ZipUtils;

@RequestMapping("cloud")
@Controller
public class CloudOperateController {
	@Autowired
	CloudOperateService cloudOperateService;

	@RequestMapping(value = "mbupload", method = RequestMethod.POST)
	public ResponseEntity<String> mbup(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			DataSourceTypeManager.set(DataSourceType.DATASOURCE_XXIN);
			if (file != null) {
				String originalFilename = file.getOriginalFilename();
				System.out.println("fileName:" + originalFilename);
				String filepath = request.getSession().getServletContext().getRealPath("/") + "backup" + File.separator
						+ originalFilename;
				String folder = request.getSession().getServletContext().getRealPath("/") + "backup" + File.separator
						+ originalFilename.replaceAll("\\.\\S+", "") + File.separator;
				boolean saveFile = saveFile(file, filepath);
				if (saveFile) {
					System.out.println("保存成功!");
					ZipUtils.upZipFile(new File(filepath), folder);
					List<String> readFrom = FileOp.readFrom(folder + "data.json", "UTF-8");
					if (readFrom.size() > 0) {
						cloudOperateService.saveUploadOperate(readFrom.get(0),
								originalFilename.replaceAll("\\.\\S+", ""));
					}

					return ResponseEntity.ok("1");
				} else {
					System.out.println("保存失败!");
					return ResponseEntity.ok("0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "queryUTime", method = RequestMethod.POST)
	public ResponseEntity<String> queryUploadTime(@RequestParam("user_id") String user_id,
			@RequestParam("device_id") String device_id, HttpServletRequest request) {
		try {
			DataSourceTypeManager.set(DataSourceType.DATASOURCE_XXIN);
			System.out.println(user_id);
			String s = cloudOperateService.queryUTime(user_id, device_id);
			System.out.println("SS:" + s);
			return ResponseEntity.ok(s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "download", method = RequestMethod.POST)
	public ResponseEntity<String> download(@RequestParam("user_id") String user_id,
			@RequestParam("device_id") String device_id, HttpServletRequest request) {
		String beginTime = BasicDateUtil.getCurrentDateTimeString();
		try {
			DataSourceTypeManager.set(DataSourceType.DATASOURCE_XXIN);
			List<FootStepInfo> downloadList = cloudOperateService.downloadList(user_id, device_id);
			if (downloadList.size() > 0) {
				long currentTimeMillis = System.currentTimeMillis();
				String svrDownDir = request.getSession().getServletContext().getRealPath("/") + "download"
						+ File.separator;
				BasicFileUtil.makeDirectory(svrDownDir);
				String saveZipFile = svrDownDir + currentTimeMillis + ".zip";
				String retzipFile = request.getSession().getServletContext().getContextPath() + "/download/"
						+ currentTimeMillis + ".zip";
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_id", user_id);
				jsonObject.put("device_id", device_id);
				String endTime = BasicDateUtil.getCurrentDateTimeString();
				jsonObject.put("download_time", endTime);
				jsonObject.put("data", downloadList);

				String jsonFile = svrDownDir + "data.json";
				BasicFileUtil.writeFileString(jsonFile, jsonObject.toString(), null, false);
				List<File> resFileList = new ArrayList<>();
				resFileList.add(new File(jsonFile));

				for (FootStepInfo fs : downloadList) {
					for (FsMedia media : fs.getMedias()) {
						String savedBackupFile = getSavedBackupFile(media.getSaveFolder(), media.getMediaName(),
								request);
						System.out.println("文件地址:" + savedBackupFile);
						resFileList.add(new File(savedBackupFile));
					}
				}
				ZipUtils.zipFiles(resFileList, new File(saveZipFile));
				cloudOperateService.saveDownloadOperate(user_id, device_id, beginTime, endTime);
				return ResponseEntity.ok(retzipFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	public String getSavedBackupFile(String folderName, String fileName, HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/") + "backup" + File.separator + folderName
				+ File.separator + fileName;
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
