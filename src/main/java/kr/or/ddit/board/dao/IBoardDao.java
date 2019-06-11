package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	/**
	 * Method : boardList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 전체 게시판 리스트를 받아오는 메서드
	 */
	List<BoardVO> boardList();
	
	/**
	 * Method : insertBoard
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 추가 메서드
	 */
	int insertBoard(BoardVO boardVO);
	
	/**
	 * Method : deleteBoard
	 * 작성자 : jakeh
	 * 변경이력 :
	 * @param board_Id
	 * @return
	 * Method 설명 : 게시판 삭제 메서드
	 */
	int deleteBoard(String board_Id);
	
	/**
	 * Method : updateBoard
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정 메서드
	 */
	int updateBoard(BoardVO boardVO);
	
	/**
	 * Method : getBoard
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param board_id
	 * @return
	 * Method 설명 : 특정 게시판 정보 조회 메서드
	 */
	BoardVO getBoard(String board_Id);
	
	/**
	 * Method : switchStatus
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 상태 변경 메서드
	 */
	int switchStatus(BoardVO boardVO);
}
