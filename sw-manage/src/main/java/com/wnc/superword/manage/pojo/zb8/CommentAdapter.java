package com.wnc.superword.manage.pojo.zb8;

import com.wnc.news.api.common.Comment;

public class CommentAdapter {
	public static ArticleComment getArticleComment(Comment comment) {
		ArticleComment articleComment = new ArticleComment();
		articleComment.setContent(comment.getContent());
		articleComment.setDown(comment.getDown());
		articleComment.setUp(comment.getUp());
		articleComment.setPriority(comment.getPriority());
		articleComment.setUserId(comment.getUserId());
		return articleComment;
	}

}
