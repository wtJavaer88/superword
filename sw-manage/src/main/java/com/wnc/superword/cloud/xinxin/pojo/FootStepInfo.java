package com.wnc.superword.cloud.xinxin.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "footsteps")
public class FootStepInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3274963893488139409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String uuid;
	private String fsDesc;
	private Integer isDeleted;
	private String tagNames;
	private String day;
	private String createTime;
	private String updateTime;
	private String uploadTime;
	private String updateBy;
	private String createBy;
	private String deviceId;
	@Transient
	private List<FsMedia> medias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public List<FsMedia> getMedias() {
		return medias;
	}

	public void setMedias(List<FsMedia> medias) {
		this.medias = medias;
	}

	@Override
	public String toString() {
		return "{id:\"" + id + "\", uuid:\"" + uuid + "\",deviceId:\"" + deviceId + "\", fsDesc:\"" + fsDesc
				+ "\", isDeleted:\"" + isDeleted + "\", tagNames:\"" + tagNames + "\", day:\"" + day
				+ "\", createTime:\"" + createTime + "\", updateTime:\"" + updateTime + "\", updateBy:\"" + updateBy
				+ "\", createBy:\"" + createBy + "\", medias:" + medias + "}";
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
