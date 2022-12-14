package com.example.tooshytoask.Models.Comments;

import com.google.gson.annotations.SerializedName;

public class ParentComment {

	@SerializedName("comment_author_email")
	private String commentAuthorEmail;

	@SerializedName("comment_author")
	private String commentAuthor;

	@SerializedName("comment_parent")
	private String commentParent;

	@SerializedName("comment_date")
	private String commentDate;

	@SerializedName("comment_content")
	private String commentContent;

	@SerializedName("comment_ID")
	private String commentID;

	public String getCommentAuthorEmail(){
		return commentAuthorEmail;
	}

	public String getCommentAuthor(){
		return commentAuthor;
	}

	public String getCommentParent(){
		return commentParent;
	}

	public String getCommentDate(){
		return commentDate;
	}

	public String getCommentContent(){
		return commentContent;
	}

	public String getCommentID(){
		return commentID;
	}
}