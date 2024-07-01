package com.example.demo.FileAttachment.repository;

import com.example.demo.FileAttachment.entity.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
}
