package com.wnc.superword.cloud.xinxin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnc.superword.cloud.xinxin.mapper.FsMediaMapper;
import com.wnc.superword.cloud.xinxin.pojo.FsMedia;
import com.wnc.superword.manage.service.BaseService;

@Service
public class FsMediaService extends BaseService<FsMedia> {
	@Autowired
	FsMediaMapper fsMediaMapper;

	public void saveEntity(FsMedia media) {
		fsMediaMapper.saveEntity(media);
	}

}
