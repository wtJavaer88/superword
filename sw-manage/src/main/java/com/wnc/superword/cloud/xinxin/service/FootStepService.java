package com.wnc.superword.cloud.xinxin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnc.superword.cloud.xinxin.mapper.FootStepMapper;
import com.wnc.superword.cloud.xinxin.pojo.FootStepInfo;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.service.BaseService;

@Service
public class FootStepService extends BaseService<FootStepInfo> {

	@Autowired
	private FootStepMapper footStepMapper;

	public List<FootStepInfo> queryList(Integer page, Integer rows) {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_XXIN);
		try {
			return this.footStepMapper.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
		return null;
	}

}
