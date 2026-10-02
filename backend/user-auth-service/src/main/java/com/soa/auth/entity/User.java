package com.soa.auth.entity;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=120) private String email;
 @Column(nullable=false) private String password;
 @Column(nullable=false,length=100) private String fullName;
 @Column(nullable=false) private boolean active=true;
 @ManyToMany(fetch=FetchType.EAGER)
 @JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
 private Set<Role> roles=new HashSet<>();
 protected User(){}
 public User(String email,String password,String fullName){this.email=email;this.password=password;this.fullName=fullName;}
 public String getEmail(){return email;} public String getPassword(){return password;}
 public String getFullName(){return fullName;} public boolean isActive(){return active;}
 public Set<Role> getRoles(){return roles;}
}