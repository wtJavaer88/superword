package com.wnc.superword.cloud.xinxin.pojo;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "fs_medias")
public class FsMedia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8001226207947247024L;
	@Id
	private int id;
	private String fsUuid;
	private String mediaName;
	private int mediapathId;
	@Transient
	private String mediaFullpath;
	private String mediaType;
	private long mediaSize;
	private int isDeleted;
	private int sn;
	private String createTime;

	public int getMediapathId() {
		return mediapathId;
	}

	public void setMediapathId(int mediapathId) {
		this.mediapathId = mediapathId;
	}

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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FsMedia [id=" + id + ", media_name=" + mediaName + ", media_fullpath=" + mediaFullpath + ", media_type="
				+ mediaType + ", sn=" + sn + ", media_size=" + mediaSize + ", create_time=" + createTime
				+ ", is_deleted=" + isDeleted + "]";
	}

	public String getMediaFullpath() {
		return mediaFullpath;
	}

	public void setMediaFullpath(String mediaFullpath) {
		this.mediaFullpath = mediaFullpath;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}
}
