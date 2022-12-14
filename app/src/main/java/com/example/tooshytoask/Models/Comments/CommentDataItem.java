package com.example.tooshytoask.Models.Comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentDataItem {

	@SerializedName("parent_comment")
	private ParentComment parentComment;

	@SerializedName("child_comment")
	private List<ChildCommentItem> childComment;

	public ParentComment getParentComment(){
		return parentComment;
	}

	public List<ChildCommentItem> getChildComment(){
		return childComment;
	}
}