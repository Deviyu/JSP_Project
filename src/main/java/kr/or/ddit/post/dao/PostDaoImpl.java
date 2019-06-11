package kr.or.ddit.post.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;

import org.apache.ibatis.session.SqlSession;

public class PostDaoImpl implements IPostDao {
	
	/**
	 * Method : postListAll
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 게시판 구분없는 전체 게시글 리스트 조회 메서드
	 */
	@Override
	public List<PostVO> postListAll() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> postList = sqlSession.selectList("post.postListAll");
		sqlSession.close();
		return postList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> postList = sqlSession.selectList("post.postList", board_Id);
		sqlSession.close();
		return postList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVO postVO = sqlSession.selectOne("post.getPost", post_Id);
		sqlSession.close();
		return postVO;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.selectOne("post.postCnt", board_Id);
		sqlSession.close();
		return cnt;
	}

	@Override
	public List<PostVO> postPagingList(String board_Id, PageVO pageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		Map<String, Object> params = new HashMap<>();
		params.put("board_Id", board_Id);
		params.put("page", pageVO.getPage());
		params.put("pageSize", pageVO.getPageSize());
		List<PostVO> postList = sqlSession.selectList("post.postPagingList", params);
		sqlSession.close();
		return postList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("post.insertPost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String postId = sqlSession.selectOne("post.getLastPostId");
		sqlSession.close();
		return postId;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("post.disablePost", post_Id);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("post.updatePost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

}
