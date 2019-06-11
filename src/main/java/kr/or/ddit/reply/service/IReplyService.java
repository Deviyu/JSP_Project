package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {
	/**
	 * Method : insertReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 추가 메서드
	 */
	int insertReply(ReplyVO replyVO);
	
	/**
	 * Method : disableReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param reply_id
	 * @return
	 * Method 설명 : 댓글 삭제(= 비활성화) 메서드
	 */
	int disableReply(String reply_id);
	
	/**
	 * Method : replyPostList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_id
	 * @return
	 * Method 설명 : 특정 게시물에 존재하는 댓글 조회 메서드 
	 */
	List<ReplyVO> replyPostList(String post_id);
	
	/**
	 * Method : getReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param reply_id
	 * @return
	 * Method 설명 : 특정 댓글 정보조회 메서드
	 */
	ReplyVO getReply(String reply_id);
}
