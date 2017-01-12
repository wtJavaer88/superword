package com.wnc.news.api.mine.zhibo8.comments_analyse;

import com.wnc.news.api.common.Comment;

public abstract class BaseAnalyse {
	Comment comment;
	String quality_tip = "评论质量:";
	int priority;
	BaseAnalyse nextAnalyse;

	public BaseAnalyse(Comment comment) {
		this.comment = comment;
	}

	public void setNext(BaseAnalyse nextAnalyse) {
		this.nextAnalyse = nextAnalyse;
	}

	public BaseAnalyse getNext() {
		return nextAnalyse;
	}

	public abstract boolean isFit();

	public boolean baseFilte() {
		return this.comment.getUp() >= 100;
	}

	public void proceed() {
		if (baseFilte()) {
			if (isFit()) {
				callback();
				return;
			}
			if (getNext() != null)
				getNext().proceed();
			else {
				err();
			}
		} else {
			warning();
		}
	}

	private void warning() {
		System.out.println("not fit the base filter");
	}

	private void err() {
		System.out.println("Can't find any fit project for comment " + comment.getContent() + comment.getUp() + " "
				+ comment.getDown());
	}

	public void callback() {
		this.comment.setPriority(priority);
		System.out.println(quality_tip + ":" + this.comment.getContent() + " / " + this.comment.getUp() + "/"
				+ this.comment.getDown());
	}

}
