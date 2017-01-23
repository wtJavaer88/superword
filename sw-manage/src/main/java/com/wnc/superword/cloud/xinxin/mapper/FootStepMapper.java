package com.wnc.superword.cloud.xinxin.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.cloud.xinxin.pojo.FootStepInfo;

public interface FootStepMapper extends Mapper<FootStepInfo> {

	List<FootStepInfo> findAll();
}