package com.wnc.superword.cloud.xinxin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnc.superword.cloud.xinxin.mapper.FootStepMapper;
import com.wnc.superword.cloud.xinxin.pojo.FootStepInfo;
import com.wnc.superword.manage.service.BaseService;

@Service
public class FootStepService extends BaseService<FootStepInfo> {

	@Autowired
	private FootStepMapper footStepMapper;

	public void saveEntity(FootStepInfo entity) {
		FootStepInfo entity2 = new FootStepInfo();
		entity2.setUuid(entity.getUuid());
		if (queryListByWhere(entity2).size() > 0) {
			footStepMapper.updateByUUID(entity);
		} else
			footStepMapper.saveEntity(entity);
	}

	public List<FootStepInfo> queryList(Map<String, Object> map) {
		return footStepMapper.queryList(map);
	}
}
