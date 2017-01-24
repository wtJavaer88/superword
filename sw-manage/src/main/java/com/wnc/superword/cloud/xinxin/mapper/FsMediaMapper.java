package com.wnc.superword.cloud.xinxin.mapper;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.cloud.xinxin.pojo.FsMedia;

public interface FsMediaMapper extends Mapper<FsMedia> {

	void saveEntity(FsMedia media);

	void deleteByUuid(String uuid);
}