package com.example.employeeleave;
import jakarta.persistence.*;
@Entity @Table(name="leave_types")
public class LeaveType {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String name;
 private String description;
 private Integer maxDays=10;
 @Column(nullable=false) private boolean active=true;
 public LeaveType(){} public LeaveType(String n,String d,Integer m){name=n;description=d;maxDays=m;active=true;}
 public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;} public Integer getMaxDays(){return maxDays;} public boolean isActive(){return active;}
 public void setName(String v){name=v;} public void setDescription(String v){description=v;} public void setMaxDays(Integer v){maxDays=v;} public void setActive(boolean v){active=v;}
}
