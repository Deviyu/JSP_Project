package kr.or.ddit.post.service;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVO;

public class PostServiceImpl implements IPostService {
	IPostDao postDao;
	public PostServiceImpl() {
		postDao = new PostDaoImpl();
	}
	
	/**
	 * Method : postListAll
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 게시판 구분없는 전체 게시글 리스트 조회 메서드
	 */
	@Override
	public List<PostVO> postListAll() {
		return postDao.postListAll();
	}
	
	/**
	 * Method : postList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param board_Id
	 * @return
	 * Method 설명 : 특정 게시판의 전체 게시글 조회 메서드
	 */
	@Override
	public List<PostVO> postList(String board_Id) {
		return postDao.postList(board_Id);
	}
	
	/**
	 * Method : getPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param post_Id
	 * @return
	 * Method 설명 : 특정 게시글 조회 메서드
	 */
	@Override
	public PostVO getPost(String post_Id) {
		return postDao.getPost(post_Id);
	}
	
	/**
	 * Method : postCnt
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * @param board_Id
	 * @return
	 * Method 설명 : 특정 게시판의 게시물건수 조회 메서드
	 */
	@Override
	public int postCnt(String board_Id) {
		return postDao.postCnt(board_Id);
	}

	/**
	 * Method : postPagingList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * @param board_Id
	 * @param pageVO
	 * @return
	 * Method 설명 : 특정 게시판의 게시물 - 페이징 처리 메서드
	 */
	@Override
	public List<PostVO> postPagingList(String board_Id, PageVO pageVO) {
		return postDao.postPagingList(board_Id, pageVO);
	}
	
	/**
	 * Method : insertPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @return
	 * Method 설명 : 게시글 추가 메서드
	 */
	@Override
	public int insertPost(PostVO postVO) {
		return postDao.insertPost(postVO);
	}
	
	/**
	 * Method : getLastPostId
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @return
	 * Method 설명 : 마지막에 추가된 게시물ID 조회 메서드
	 */
	@Override
	public String getLastPostId() {
		return postDao.getLastPostId();
	}
	
	/**
	 * Method : disablePost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_Id
	 * @return
	 * Method 설명 : 게시물 삭제(= 열람 불가상태) 메서드
	 */
	@Override
	public int disablePost(String post_Id) {
		return postDao.disablePost(post_Id);
	}
	
	/**
	 * Method : updatePost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param postVO
	 * @return
	 * Method 설명 : 게시물 수정 메서드
	 */
	@Override
	public int updatePost(PostVO postVO) {
		return postDao.updatePost(postVO);
	}

}
