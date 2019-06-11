package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoImplTest {
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoImplTest.class);
	private IBoardDao boardDao;
	@Before
	public void setup() {
		boardDao = new BoardDaoImpl();
	}
	
	/**
	 * Method : insertBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 게시판 생성 테스트 메서드 
	 */
	@Test
	@Ignore
	public void insertBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO("jakeharunt", "건의게시판", "Y", new Date());
		/***When***/
		int result = boardDao.insertBoard(boardVO);
		
		/***Then***/
		logger.debug("result : {}", result);
		assertEquals(1, result);
	}
	
	/**
	 * Method : boardListTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 전체 게시판 조회 테스트 메서드
	 */
	@Test
	@Ignore
	public void boardListTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardDao.boardList();
		/***Then***/
		logger.debug("boardList : {}", boardList);
		assertEquals(3, boardList.size());

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
		BoardVO boardVO = boardDao.getBoard(board_Id);
		/***Then***/
		assertEquals("자유게시판", boardVO.getBoard_name());
	}
	
	/**
	 * Method : deleteBoardTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * Method 설명 : 게시판 삭제 테스트 메서드
	 */
	@Test
	@Ignore
	public void deleteBoardTest() {
		/***Given***/
		String board_Id = "BOARD0002";
		/***When***/
		int result = boardDao.deleteBoard(board_Id);
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
	public void updateBoardTest(){
		/***Given***/
		String board_Id = "BOARD00002";
		BoardVO boardVO = boardDao.getBoard(board_Id);
		boardVO.setBoard_usable("N");
		/***When***/
		int result = boardDao.updateBoard(boardVO);
		/***Then***/
		assertEquals(1, result);
	}

}
