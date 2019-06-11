package kr.or.ddit.post.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.post.model.PostVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDaoImplTest {
	private static final Logger logger = LoggerFactory
			.getLogger(PostDaoImplTest.class);
	
	IPostDao dao;
	
	@Before
	public void setup() {
		dao = new PostDaoImpl();
	}
	
	/**
	 * Method : postListAllTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * Method 설명 : 게시판 구분없는 전체 게시글 리스트 조회 테스트 메서드
	 */
	@Test
	public void postListAllTest() {
		/***Given***/
		
		/***When***/
		List<PostVO> postList = dao.postListAll();
		
		/***Then***/
		logger.debug("postList : {}",postList);
		assertEquals(3, postList.size());
	}
	
	
	/**
	 * Method : postListTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * Method 설명 : 특정 게시판의 전체 게시글 조회 테스트 메서드
	 */
	@Test
	public void postListTest() {
		/***Given***/
		String board_Id = "BOARD00003";
		/***When***/
		List<PostVO> postList = dao.postList(board_Id);
		
		/***Then***/
		logger.debug("postList : {}", postList);
//		assertEquals(1, postList.size());
		assertNotNull(postList);
	}
	
	
	/**
	 * Method : getPostTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-05 처음 생성
	 * Method 설명 : 특정 게시글 조회 메서드
	 */
	@Test
	public void getPostTest() {
		/***Given***/
		String post_Id = "POST000003";
		/***When***/
		PostVO postVO = dao.getPost(post_Id);
		/***Then***/
		assertNotNull(postVO);
		assertEquals("BOARD00002", postVO.getBoard_Id());
	}
	
	/**
	 * Method : postCntTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * Method 설명 : 특정 게시판의 게시글 수 조회 테스트 메서드
	 */
	@Test
	public void postCntTest() {
		/***Given***/
		String board_Id = "BOARD00001";
		/***When***/
		int cnt = dao.postCnt(board_Id);
		/***Then***/
		assertEquals(2, cnt);
		
	}
}
