package com.example.employeeleave;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false, unique=true) private String email;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String role="EMPLOYEE";
 private LocalDateTime createdAt=LocalDateTime.now();
 public User(){}
 public User(String name,String email,String password){this.name=name;this.email=email;this.password=password;this.role="EMPLOYEE";}
 public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
 public String getRole(){return role;} public String getPassword(){return password;}
 public void setName(String v){name=v;} public void setEmail(String v){email=v;} public void setPassword(String v){password=v;}
 public void setRole(String v){role=v;}
}
