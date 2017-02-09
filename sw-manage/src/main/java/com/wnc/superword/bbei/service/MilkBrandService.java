package com.wnc.superword.bbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wnc.basic.BasicFileUtil;
import com.wnc.superword.bbei.mapper.MilkBrandMapper;
import com.wnc.superword.bbei.pojo.Milk;
import com.wnc.superword.bbei.pojo.MilkBrand;
import com.wnc.superword.bbei.pojo.Spider;
import com.wnc.superword.manage.db.DataSourceType;
import com.wnc.superword.manage.db.DataSourceTypeManager;
import com.wnc.superword.manage.service.BaseService;
import com.wnc.tools.UrlPicDownloader;

@Service
public class MilkBrandService extends BaseService<MilkBrand> {
	@Autowired
	MilkService milkService;
	@Autowired
	MilkBrandMapper milkBrandMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public void initData() {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_BBEI);
		try {
			Spider spider = new Spider();
			List<MilkBrand> brands = spider.getBrands();
			for (MilkBrand milkBrand : brands) {
				milkBrandMapper.saveEntity(milkBrand);
				System.out.println(milkBrand.getId());
				List<Milk> milks = spider.getMilks(milkBrand.getUrl());
				for (Milk milk : milks) {
					milk.setBrand(milkBrand.getId());
					milkService.save(milk);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}
	}

	public void downPics() {
		DataSourceTypeManager.set(DataSourceType.DATASOURCE_BBEI);
		try {
			List<Milk> queryAll = milkService.queryAll();
			for (Milk milk : queryAll) {
				try {
					String folder = "D:\\bbei-pics\\" + milk.getBrand() + "\\";
					BasicFileUtil.makeDirectory(folder);
					String pic = milk.getPic();
					UrlPicDownloader.download(pic, folder + BasicFileUtil.getFileName(pic));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceTypeManager.reset();
		}

	}

}
