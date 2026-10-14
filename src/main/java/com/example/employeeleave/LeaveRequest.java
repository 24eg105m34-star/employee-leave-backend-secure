package com.example.employeeleave;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name="leave_requests")
public class LeaveRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long userId;
 @Column(nullable=false) private String leaveType;
 @Column(nullable=false) private LocalDate startDate;
 @Column(nullable=false) private LocalDate endDate;
 @Column(nullable=false,length=1000) private String reason;
 @Column(nullable=false) private String status="PENDING";
 private String reviewComment;
 private LocalDateTime createdAt=LocalDateTime.now();
 public LeaveRequest(){}
 public Long getId(){return id;} public Long getUserId(){return userId;} public String getLeaveType(){return leaveType;}
 public LocalDate getStartDate(){return startDate;} public LocalDate getEndDate(){return endDate;}
 public String getReason(){return reason;} public String getStatus(){return status;} public String getReviewComment(){return reviewComment;}
 public LocalDateTime getCreatedAt(){return createdAt;}
 public void setUserId(Long v){userId=v;} public void setLeaveType(String v){leaveType=v;} public void setStartDate(LocalDate v){startDate=v;}
 public void setEndDate(LocalDate v){endDate=v;} public void setReason(String v){reason=v;} public void setStatus(String v){status=v;} public void setReviewComment(String v){reviewComment=v;}
}
