package com.example.tooshytoask.Models.Courses;

import com.google.gson.annotations.SerializedName;

public class CourseDataItem {

	@SerializedName("course_progress")
	private int courseProgress;

	@SerializedName("course_name")
	private String courseName;

	@SerializedName("thumbnail_image_url")
	private String thumbnailImageUrl;
	@SerializedName("enroll_image_url")
	private String enroll_image_url;

	@SerializedName("feature_image_url")
	private String featureImageUrl;

	@SerializedName("total_chapter")
	private String totalChapter;

	@SerializedName("enroll_status")
	private String enrollStatus;

	@SerializedName("id")
	private String id;

	@SerializedName("total_likes")
	private String totalLikes;

	public int getCourseProgress(){
		return courseProgress;
	}

	public String getCourseName(){
		return courseName;
	}

	public String getThumbnailImageUrl(){
		return thumbnailImageUrl;
	}

	public String getFeatureImageUrl(){
		return featureImageUrl;
	}

	public String getTotalChapter(){
		return totalChapter;
	}

	public String getEnrollStatus(){
		return enrollStatus;
	}

	public String getId(){
		return id;
	}

	public String getTotalLikes(){
		return totalLikes;
	}

	public String getEnroll_image_url() {
		return enroll_image_url;
	}

	public void setEnroll_image_url(String enroll_image_url) {
		this.enroll_image_url = enroll_image_url;
	}
}