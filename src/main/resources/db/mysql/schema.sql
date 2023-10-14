# CREATE DATABASE bit_homework;
# USE bit_homework;

DROP TABLE IF EXISTS member_bookmark;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS member_board_likes;
DROP TABLE IF EXISTS member_board_dislikes;

CREATE TABLE member
(
    member_id INT AUTO_INCREMENT NOT NULL,
    email     VARCHAR(50),
    password  VARCHAR(255),
    nickname  VARCHAR(10),
    PRIMARY KEY (member_id)
);

/*
# 게시글 카테고리
CREATE TABLE category
(
    category_id INT AUTO_INCREMENT,
    name        VARCHAR(20),
    PRIMARY KEY (category_id)
);
*/

# 게시글(회원ID 외래키)
CREATE TABLE board
(
    board_id     INT AUTO_INCREMENT,
    member_id    INT,
    writer       VARCHAR(10),
    category     VARCHAR(20),
    password     VARCHAR(255),
    subject      VARCHAR(50),
    content      TEXT,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    views        INT      DEFAULT 0,
    filepath     VARCHAR(255),
    likes        INT      DEFAULT 0,
    dislikes     INT      DEFAULT 0,
    PRIMARY KEY (board_id)
);

# 스크랩
CREATE TABLE member_bookmark
(
    member_id INT,
    board_id  INT,
    PRIMARY KEY (member_id, board_id)
);

# 좋아요
CREATE TABLE member_board_likes
(
    member_id INT,
    board_id  INT,
    PRIMARY KEY (member_id, board_id)
);

# 싫어요
CREATE TABLE member_board_dislikes
(
    member_id INT,
    board_id  INT,
    PRIMARY KEY (member_id, board_id)
);
