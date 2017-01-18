package com.wnc.superword.manage.pojo.zb8;

import javax.persistence.Table;

@Table(name = "COMMENT")
public class ArticleComment {
	private String content;
	private String userId;
	private int up;
	private int down;
	private Long articleId;
	private int priority;// 评论的优先级(或者说等级),自定义排序时很重要
	private String createTime;

	public ArticleComment() {
		setPriority(100);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "内容:" + this.content + "   顶/踩 (" + this.up + "/" + this.down + ")";
	}
}
