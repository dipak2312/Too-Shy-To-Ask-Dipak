package com.example.tooshytoask.Models.Comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("Comment_Data")
	private List<CommentDataItem> commentData;

	public String getMsg(){
		return msg;
	}

	public int getCode(){
		return code;
	}

	public List<CommentDataItem> getCommentData(){
		return commentData;
	}
}