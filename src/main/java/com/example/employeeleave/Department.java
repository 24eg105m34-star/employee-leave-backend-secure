package com.example.employeeleave;
import jakarta.persistence.*;
@Entity @Table(name="departments") public class Department { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,unique=true) private String name; private String description; public Department(){} public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;} public void setName(String v){name=v;} public void setDescription(String v){description=v;} }
