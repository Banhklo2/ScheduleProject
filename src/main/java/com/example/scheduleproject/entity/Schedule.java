package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)  // 제목 (null 불가, 최대 50자)
    private String title;

    @Column(nullable = false, length = 500) // 내용 (null 불가, 최대 500자)
    private String content;

    @Column(nullable = false, length = 30) // 작성자 이름 (null 불가, 최대 30자)
    private String writer;

    @Column(nullable = false, length = 100) // 비밀번호 (null 불가)
    private String password;

    // 일정 생성용 생성자
    public Schedule(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    // 일정 수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 비밀번호 검증 메서드
    public void checkPassword(String inputPassword) {

        // 비밀번호를 아예 안 넣은 경우
        if (inputPassword == null || inputPassword.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        // DB에 저장된 비밀번호와 다를 경우
        if (!this.password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
