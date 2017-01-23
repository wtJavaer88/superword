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
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(ID)+1 from footsteps")
	private int id;
	private String uuid;
	private String fsDesc;
	private boolean isDeleted;
	private String tagNames;
	private String day;
	private String createTime;
	private String updateTime;
	private String updateBy;
	private String createBy;
	@Transient
	private List<FsMedia> medias;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
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
		return "FootStepInfo [id=" + id + ", uuid=" + uuid + ", fs_desc=" + fsDesc + ", is_deleted=" + isDeleted
				+ ", tag_names=" + tagNames + ", day=" + day + ", create_time=" + createTime + ", update_time="
				+ updateTime + ", update_by=" + updateBy + ", create_by=" + createBy + ", medias=" + medias + "]";
	}

}
