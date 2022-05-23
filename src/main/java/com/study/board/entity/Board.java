package com.study.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * @Getter : 해당 클래스에 포함된 멤버 변수의 모든 getter 메서드를 생성해주는 롬복의 강력한 기능
 * 
 * @NoArgsConstructor : 해당 클래스의 기본 생성자를 생성해주는 어노테이션. access 속성을 이용해서 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어한다.
 * 
 * @Entity : 해당 클래스가 테이블과 매핑되는 JPA의 엔티티 클래스임을 의미한다. 
 * 
*/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

	/*
	 * @Id : 해당 멤버가 엔티티의 PK임을 의미한다. 
	 * 
	 * @GeneratedValue : PK 생성 전략을 설정하는 어노테이션 
	 * 
	 * @Builder : 롬복에서 제공해주는 빌더라는 기능으로 , 생성자 대신에 이용하는 패턴.
	*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    private int hits; // 조회 수

    private char deleteYn; // 삭제 여부

    private LocalDateTime createdDate = LocalDateTime.now(); // 생성일

    private LocalDateTime modifiedDate; // 수정일

    @Builder
    public Board(String title, String content, String writer, int hits, char deleteYn) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.deleteYn = deleteYn;
    }
    
    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }

}