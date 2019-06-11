package kr.or.ddit.reply.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplyServiceImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(ReplyServiceImplTest.class);
	
	private IReplyService replyService;
	
	@Before
	public void setup() {
		replyService = new ReplyServiceImpl();
	}
	
	/**
	 * Method : insertReplyTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 댓글 추가 테스트 메서드
	 */
	@Test
	public void insertReplyTest() {
		/***Given***/
		String userid = "jakeharunt";
		String reply_content = "테스트 댓글입니다.";
		String post_id = "POST000027";
		String reply_usable = "Y";
		Date reply_date = new Date();
		ReplyVO replyVO = new ReplyVO(post_id, reply_content, reply_date, userid, reply_usable);
		/***When***/
		int result = replyService.insertReply(replyVO);
		/***Then***/
		logger.debug("result : {}", result);
		logger.debug("replyVO : {}", replyVO);
		assertEquals(1, result);
	}
	
	/**
	 * Method : disableReplyTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 댓글 삭제(=비활성화) 테스트 메서드
	 */
	@Test
	public void disableReplyTest() {
		/***Given***/
		String reply_id = "REPLY00000024";
		/***When***/
		int result = replyService.disableReply(reply_id);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * Method : replyPostListTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 특정 게시글의 댓글 목록 조회 테스트 메서드
	 */
	@Test
	public void replyPostListTest() {
		/***Given***/
		String post_id = "POST000027";
		/***When***/
		List<ReplyVO> replyList = replyService.replyPostList(post_id);
		/***Then***/
		logger.debug("replyList : {}", replyList);
		assertNotNull(replyList);
		assertEquals(3, replyList.size());
	}
	
	/**
	 * Method : getReplyTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * Method 설명 : 특정 댓글 정보조회 메서드
	 */
	@Test
	public void getReplyTest() {
		/***Given***/
		String reply_id = "REPLY00000024";
		/***When***/
		ReplyVO replyVO = replyService.getReply(reply_id);
		/***Then***/
		assertNotNull(replyVO);
		logger.debug("replyVO : {}", replyVO);
	}
}
