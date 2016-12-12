package com.wnc.superword.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "club")
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(id)+1 from club")
	private Long id;
	@Column(name = "full_name")
	private String fullName;

	@Column(name = "cn_name")
	private String cnName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getClubStatsUrl() {
		return clubStatsUrl;
	}

	public void setClubStatsUrl(String clubStatsUrl) {
		this.clubStatsUrl = clubStatsUrl;
	}

	private String photo;

	@Column(name = "club_stats_url")
	private String clubStatsUrl;
}
