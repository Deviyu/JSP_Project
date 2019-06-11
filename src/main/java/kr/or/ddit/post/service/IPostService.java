package kr.or.ddit.post.service;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;

public interface IPostService {
	/**
	 * Method : postListAll
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 게시판 구분없는 전체 게시글 리스트 조회 메서드
	 */
	List<PostVO> postListAll();
	
	/**
	 * Method : postList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param board_Id
	 * @return
	 * Method 설명 : 특정 게시판의 전체 게시글 조회 메서드
	 */
	List<PostVO> postList(String board_Id);
	
	/**
	 * Method : getPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param post_Id
	 * @return
	 * Method 설명 : 특정 게시글 조회 메서드
	 */
	PostVO getPost(String post_Id);
	
	/**
	 * Method : postCnt
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * @param board_Id
	 * @return
	 * Method 설명 : 특정 게시판의 게시물건수 조회 메서드
	 */
	int postCnt(String board_Id);
	
	/**
	 * Method : postPagingList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05
	 * @param board_Id
	 * @param pageVO
	 * @return
	 * Method 설명 : 특정 게시판의 페이징처리된 게시물 리스트 조회 메서드
	 */
	List<PostVO> postPagingList(String board_Id, PageVO pageVO);
	
	/**
	 * Method : insertPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @return
	 * Method 설명 : 게시글 추가 메서드
	 */
	int insertPost(PostVO postVO);
	
	/**
	 * Method : getLastPostId
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @return
	 * Method 설명 : 마지막에 추가된 게시물ID 조회 메서드
	 */
	String getLastPostId();
	
	/**
	 * Method : disablePost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_Id
	 * @return
	 * Method 설명 : 게시물 삭제(= 열람 불가상태) 메서드
	 */
	int disablePost(String post_Id);
	
	/**
	 * Method : updatePost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param postVO
	 * @return
	 * Method 설명 : 게시물 수정 메서드
	 */
	int updatePost(PostVO postVO);
}
