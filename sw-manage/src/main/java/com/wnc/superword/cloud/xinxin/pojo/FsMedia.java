package com.wnc.superword.cloud.xinxin.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "fs_medias")
public class FsMedia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8001226207947247024L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String fsUuid;
	private String mediaName;
	private String mediaFullpath;
	private String mediaType;
	private Long mediaSize;
	private Integer isDeleted;
	private Integer sn;
	private String createTime;

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public long getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(long mediaSize) {
		this.mediaSize = mediaSize;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFsUuid() {
		return fsUuid;
	}

	public void setFsUuid(String fsUuid) {
		this.fsUuid = fsUuid;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id:\"" + id + "\", mediaName:\"" + mediaName + "\", mediaFullpath:\"" + mediaFullpath
				+ "\", mediaType:\"" + mediaType + "\", sn:\"" + sn + "\", mediaSize:\"" + mediaSize
				+ "\", createTime:\"" + createTime + "\", isDeleted:\"" + isDeleted + "\"}";
	}

	public String getMediaFullpath() {
		return mediaFullpath;
	}

	public void setMediaFullpath(String mediaFullpath) {
		this.mediaFullpath = mediaFullpath;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}
}
