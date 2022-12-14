package com.example.tooshytoask.Models.Courses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseEnrolledResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("Course_Data")
	private List<CourseDataItem> courseData;

	public String getMsg(){
		return msg;
	}

	public int getCode(){
		return code;
	}

	public List<CourseDataItem> getCourseData(){
		return courseData;
	}
}