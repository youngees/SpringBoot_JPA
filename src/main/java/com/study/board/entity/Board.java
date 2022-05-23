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
 * @Entity : 해당 클래스가 테이블과 매핑되는 JPA의 엔티티 클래스임을 의미한다. 기본적으로 클래스명(Camel Case)을 테이블명(Snake Case)으로 매핑한다. 
 * 			(ex. user_role 이라는 테이블은 UserRole이라는 클래스로 네이밍) 혹시라도 클래스명과 테이블명이 다를 수밖에 없는 상황에서는 클래스 레벨에 @Table을 선언하고,
 *			@Table(name = "user_role")과 같이 name 속성을 이용해서 처리해 주면 된다. 
 *
 *		※ Entity 클래스는 테이블 그 자체와 같기때문에 각각 멤버변수가 해당 테이블의 컬럼이라는 의미가 되고, 컬럼에 대한 setter를 무작정 생성하는 경우,
 * 		  객체의 값이 어느 시점에 변했는지 알 수 없다. 이러한 이유로 Entity 클래스에는 절대로 set메서드가 존재해서는 안된다.
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
    private Long id; // PK (데이터의 양이 많지 않으면 Integer를 사용해도 괜찮을 듯 하다.)

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
    
    /**
     * 게시글 수정
     */
    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * 조회 수 증가
     */
    public void increaseHits() {
        this.hits++;
    }

    /**
     * 게시글 삭제
     */
    public void delete() {
        this.deleteYn = 'Y';
    }

}