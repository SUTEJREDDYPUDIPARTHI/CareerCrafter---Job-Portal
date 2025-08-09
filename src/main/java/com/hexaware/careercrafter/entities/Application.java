package com.hexaware.careercrafter.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;


/*
 * Job application is a entity that is represented by JobSeeker
 * It links a JobSeeker with a JobPosting
 * 
 */


@Entity
@Table(name="applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int applicationId;
	
	@ManyToOne
	@JoinColumn(name = "job_id", nullable = false)
	private JobPosting job;

	@ManyToOne
	@JoinColumn(name = "seeker_id", nullable = false)
	private JobSeeker jobSeeker;

	@Column(updatable = false)
	private LocalDateTime applicationDate = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationStatus status = ApplicationStatus.APPLIED;

	@Column(columnDefinition = "TEXT")
	private String coverLetter;
	private String resumeFilePath;

	public enum ApplicationStatus {
		APPLIED, IN_REVIEW, SHORTLISTED, REJECTED, HIRED
	}
	
	public Application() {
		
	}

	public Application(int applicationId, JobPosting job, JobSeeker jobSeeker, LocalDateTime applicationDate,
			ApplicationStatus status, String coverLetter, String resumeFilePath) {
		this.applicationId = applicationId;
		this.job = job;
		this.jobSeeker = jobSeeker;
		this.applicationDate = applicationDate;
		this.status = status;
		this.coverLetter = coverLetter;
		this.resumeFilePath = resumeFilePath;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public JobPosting getJob() {
		return job;
	}

	public void setJob(JobPosting job) {
		this.job = job;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getResumeFilePath() {
		return resumeFilePath;
	}

	public void setResumeFilePath(String resumeFilePath) {
		this.resumeFilePath = resumeFilePath;
	}
	
}
