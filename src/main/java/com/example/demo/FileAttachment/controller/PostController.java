package com.example.demo.controller;

import com.example.demo.FileAttachment.entity.FileAttachment;
import com.example.demo.FileAttachment.repository.FileAttachmentRepository;
import com.example.demo.FileAttachment.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private FileAttachmentRepository fileAttachmentRepository;

    @PostMapping
    public List<String> uploadPost(@RequestParam("title") String title,
                                   @RequestParam("content") String content,
                                   @RequestParam("post_date") String postDate,
                                   @RequestParam("board_id") Long boardId,
                                   @RequestParam("files") List<MultipartFile> files) {
        try {
            List<String> filePaths = files.stream().map(file -> {
                try {
                    String filePath = s3Service.uploadFile(file);

                    FileAttachment fileAttachment = FileAttachment.builder()
                            .boardId(boardId)
                            .filePath(filePath)
                            .uploadDate(LocalDateTime.now())
                            .build();

                    fileAttachmentRepository.save(fileAttachment);
                    return filePath;
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload file", e);
                }
            }).collect(Collectors.toList());

            return filePaths;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Post upload failed!");
        }
    }
}
