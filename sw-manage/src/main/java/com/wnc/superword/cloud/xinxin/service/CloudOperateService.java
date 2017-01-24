package com.wnc.superword.cloud.xinxin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.wnc.basic.BasicDateUtil;
import com.wnc.superword.cloud.xinxin.mapper.CloudOperateMapper;
import com.wnc.superword.cloud.xinxin.pojo.CloudOperate;
import com.wnc.superword.cloud.xinxin.pojo.FootStepInfo;
import com.wnc.superword.cloud.xinxin.pojo.FsMedia;
import com.wnc.superword.manage.service.BaseService;

@Service
public class CloudOperateService extends BaseService<CloudOperate> {
	@Autowired
	FootStepService footStepService;
	@Autowired
	FsMediaService fsMediaService;
	@Autowired
	CloudOperateMapper cloudOperateMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUploadOperate(String json, String folderName) {
		JSONObject jObject = JSONObject.parseObject(json);
		String uploadTime = jObject.getString("upload_time");
		String userId = jObject.getString("user_id");
		String deviceId = jObject.getString("device_id");
		JSONArray jsonArray = jObject.getJSONArray("data");

		List<FootStepInfo> parseArray = jsonArray.parseArray(jsonArray.toString(), FootStepInfo.class);
		if (parseArray.size() > 0) {
			CloudOperate cloudOperate = new CloudOperate();
			cloudOperate.setBeginTime(uploadTime);
			cloudOperate.setEndTime(BasicDateUtil.getCurrentDateTimeString());
			cloudOperate.setUserId(userId);
			cloudOperate.setDeviceId(deviceId);
			cloudOperate.setOperateType(1);
			save(cloudOperate);
			for (FootStepInfo footStepInfo : parseArray) {
				footStepInfo.setUploadTime(uploadTime);
				System.out.println(footStepInfo);
				footStepService.saveEntity(footStepInfo);

				fsMediaService.deleteByUuid(footStepInfo.getUuid());
				for (FsMedia media : footStepInfo.getMedias()) {
					media.setSaveFolder(folderName);
					fsMediaService.saveEntity(media);
				}
			}
		}

	}

	public void saveDownloadOperate(String user_id, String device_id, String beginTime, String endTime) {
		CloudOperate cloudOperate = new CloudOperate();
		cloudOperate.setBeginTime(beginTime);
		cloudOperate.setEndTime(endTime);
		cloudOperate.setUserId(user_id);
		cloudOperate.setDeviceId(device_id);
		cloudOperate.setOperateType(2);
		save(cloudOperate);
	}

	public String queryUTime(String user_id, String device_id) {
		Example example = new Example(CloudOperate.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("userId", user_id);
		createCriteria.andEqualTo("deviceId", device_id);
		createCriteria.andEqualTo("operateType", 1);
		example.setOrderByClause("begin_time desc");
		List<CloudOperate> selectByExample = cloudOperateMapper.selectByExample(example);
		if (selectByExample != null && selectByExample.size() > 0) {
			return selectByExample.get(0).getBeginTime();
		}
		return "";
	}

	public String queryDTime(String user_id, String device_id) {
		Example example = new Example(CloudOperate.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("userId", user_id);
		createCriteria.andEqualTo("deviceId", device_id);
		createCriteria.andEqualTo("operateType", 2);
		example.setOrderByClause("begin_time desc");
		List<CloudOperate> selectByExample = cloudOperateMapper.selectByExample(example);
		if (selectByExample != null && selectByExample.size() > 0) {
			return selectByExample.get(0).getBeginTime();
		}
		return "0000-00-00 00:00:00";
	}

	public List<FootStepInfo> downloadList(String user_id, String device_id) {
		String queryDTime = queryDTime(user_id, device_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("device_id", device_id);
		map.put("update_time", queryDTime);
		return footStepService.findNeedDownList(map);
	}
}
