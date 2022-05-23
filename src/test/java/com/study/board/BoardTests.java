package com.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;

/*
 * @SpringBootTest : 기존의 레거시 프로젝트와는 달리 스프링 부트는 해당 어노테이션만 선언하면 테스팅이 가능하다.
*/
@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    /*
     * 게시글 저장에 이용되는 params는 builder 패턴을 통해 생성된 객체이다.
     * 생성자와는 달리 빌더패턴을 이용하면 어떤 멤버에 어떤 값을 세팅하는지 직관적으로 확인이 가능하다.
    */
    void save() {

        // 1. 게시글 파라미터 생성
        Board params = Board.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .writer("도뎡이")
                .hits(0)
                .deleteYn('N')
                .build();

        // 2. 게시글 저장
        boardRepository.save(params);

        // 3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById((long) 1).get();
        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
        assertThat(entity.getTitle()).isEqualTo("도뎡이");
    }

    @Test
    /*
     * boardRepository의 count()와 findAll()메서드를 이용해서 전체게시글 수와 전체 게시글 리스트를 조회하는 쿼리를 실행한다.  
    */
    void findAll() {

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();
    }

    @Test
    void delete() {

        // 1. 게시글 조회
        Board entity = boardRepository.findById((long) 1).get();

        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}