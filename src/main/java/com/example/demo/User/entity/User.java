package com.example.demo.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "userid", nullable = false)
    private String userId;

    @Column(name = "userpassword", nullable = false)
    private String userPassword;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "usernickname", nullable = false)
    private String userNickname;

    @Column(name = "useremail", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "userphone")
    private String userPhone;

    @Column(name = "userprofileimg")
    private String userProfileImg;

    @Column(name = "useradmin", nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private String userAdmin;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
