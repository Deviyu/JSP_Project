package kr.or.ddit.append.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.append.model.AppendVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppendDaoImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(AppendDaoImplTest.class);
	
	private IAppendDao appendDao;
	
	@Before
	public void setup() {
		appendDao = new AppendDaoImpl();
	}
	
	/**
	 * Method : insertAppendTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 첨부파일 추가 테스트 메서드
	 */
	@Test
	public void insertAppendTest() {
		/***Given***/
		String append_path = "d:\\upload\\2019\\06\\3b7d5970-8f58-4b9f-aaa3-7209760bcd14.jpg";
		String append_filename = "brainOverclock.jpg";
		String post_id = "POST000027";
		AppendVO appendVO = new AppendVO(post_id, append_path, append_filename);
		/***When***/
		int result = appendDao.insertAppend(appendVO);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * Method : deleteAppendTest
	 * 작성자 : jakeh
	 * 변경이력 :
	 * Method 설명 : 첨부파일 삭제 테스트 메서드
	 */
	@Test
	public void deleteAppendTest() {
		/***Given***/
		String append_id = "APPEND00000026";
		/***When***/
		int result = appendDao.deleteAppend(append_id);
		/***Then***/
		assertEquals(0, result);
	}
	
	/**
	 * Method : appendListPostTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 게시물에 할당된 첨부파일 조회 테스트 메서드
	 */
	@Test
	public void appendListPostTest() {
		/***Given***/
		String post_id = "POST000027";
		/***When***/
		List<AppendVO> appendList = appendDao.appendListPost(post_id);
		/***Then***/
		assertNotNull(appendList);
	}
	
	/**
	 * Method : getAppendTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 특정 첨부파일 조회 메서드
	 */
	@Test
	public void getAppendTest() {
		/***Given***/
		String append_id = "APPEND00000025";
		/***When***/
		AppendVO appendVO = appendDao.getAppend(append_id);
		/***Then***/
		assertNotNull(appendVO);
		logger.debug("appendVO : {}", appendVO);
	}
}
