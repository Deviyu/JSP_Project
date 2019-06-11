package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceImplTest {
	private IBoardService service;
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardServiceImplTest.class);
	
	@Before
	public void setup() {
		service = new BoardServiceImpl();
	}

	/**
	 * Method : boardListTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 전체 게시판 리스트 조회 테스트 메서드
	 */
	@Test
	public void boardListTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = service.boardList();
		/***Then***/
		assertNotNull(boardList);
		logger.debug("boardList : {}",boardList); 
	}
	
	/**
	 * Method : getBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 특정 게시물 조회 테스트 메서드
	 */
	@Test
	public void getBoardTest() {
		/***Given***/
		String board_Id = "BOARD00001";
		/***When***/
		BoardVO boardVO = service.getBoard(board_Id);
		/***Then***/
		assertEquals("자유게시판", boardVO.getBoard_name());
	}
	
	/**
	 * Method : insertBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 게시판 생성 테스트 메서드
	 */
	@Test
	public void insertBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO("jakeharunt", "질문게시판", "Y", new Date());
		/***When***/
		int result = service.insertBoard(boardVO);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * Method : deleteBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04
	 * Method 설명 : 게시판 삭제 테스트 메서드
	 */
	@Test
	public void deleteBoardTest() {
		/***Given***/
		String board_Id = "BOARD00005";
		/***When***/
		int result = service.deleteBoard(board_Id);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * Method : updateBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 게시판 수정 테스트 메서드
	 */
	@Test
	public void updateBoardTest() {
		/***Given***/
		String board_Id = "BOARD00004";
		BoardVO boardVO = service.getBoard(board_Id);
		
		boardVO.setBoard_name("공지사항");
		/***When***/
		int result = service.updateBoard(boardVO);
		BoardVO updatedVO = service.getBoard(board_Id);
		/***Then***/
		assertEquals(1, result);
		assertEquals("공지사항", updatedVO.getBoard_name());
	}
}
