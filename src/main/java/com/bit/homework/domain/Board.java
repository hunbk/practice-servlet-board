package com.bit.homework.domain;

import java.time.LocalDateTime;

public class Board {

    private Integer boardId;
    private Integer memberId;
    private String writer;
    private String category;
    private String password;
    private String subject;
    private String content;
    private LocalDateTime createdDate;
    private Integer views;
    private String filepath;
    private Integer likes;
    private Integer dislikes;

    public Board() {
    }

    public Board(Integer memberId, String writer, String category, String password, String subject, String content, String filepath) {
        this.memberId = memberId;
        this.writer = writer;
        this.category = category;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.filepath = filepath;
    }

    public Board(Integer boardId, Integer memberId, String writer, String category, String password, String subject, String content, LocalDateTime createdDate, Integer views, String filepath, Integer likes, Integer dislikes) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.writer = writer;
        this.category = category;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.createdDate = createdDate;
        this.views = views;
        this.filepath = filepath;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", memberId=" + memberId +
                ", writer='" + writer + '\'' +
                ", category='" + category + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", views=" + views +
                ", filepath='" + filepath + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
