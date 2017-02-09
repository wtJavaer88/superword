package com.wnc.superword.bbei.mapper;

import com.github.abel533.mapper.Mapper;
import com.wnc.superword.bbei.pojo.MilkBrand;

public interface MilkBrandMapper extends Mapper<MilkBrand> {
	void saveEntity(MilkBrand entity);
}