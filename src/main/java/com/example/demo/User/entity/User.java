package com.example.demo.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "cohort_id")
    private Integer cohortId;

    @Column(name = "assigned_cohort")
    private Integer assignedCohort;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition = "ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending'")
    private ApprovalStatus approvalStatus;

    @Column(name = "profileimg", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'basicprofileimg.jpg'")
    private String profileImg;

    @Column(name = "isdelete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    @Builder.Default
    private Boolean isDeleted = false;

    public enum Role {
        Student,
        Instructor,
        Researcher,
        Admin
    }

    public enum ApprovalStatus {
        Pending,
        Approved,
        Rejected
    }
}
