package com.study.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * 엔티티 클래스와 레파지토리 인터페이스는 꼭 같은 패키지에 위치해야 한다.
 * 
 * extends JpaRepository<Board, Long> : 레파지토리 인터페잉스에서 JpaRepository 인터페이스를 상속받을때 엔티티 클래스의 타입과 PK에 해당하는 데이터 타입을 
 * 									선언하면 해당 엔티티 클래스와 매핑되는 테이블인 board 테이블의 CRUD 기능을 사용할 수 있다.
*/
public interface BoardRepository extends JpaRepository<Board, Long> {

}
