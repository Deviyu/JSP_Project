package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.model.ReplyVO;

public class ReplyServiceImpl implements IReplyService {
	private IReplyDao replyDao;
	
	public ReplyServiceImpl() {
		replyDao = new ReplyDaoImpl();
	}
	
	/**
	 * Method : insertReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 추가 메서드
	 */
	@Override
	public int insertReply(ReplyVO replyVO) {
		return replyDao.insertReply(replyVO);
	}
	
	/**
	 * Method : disableReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param reply_id
	 * @return
	 * Method 설명 : 댓글 삭제(= 비활성화) 메서드
	 */
	@Override
	public int disableReply(String reply_id) {
		return replyDao.disableReply(reply_id);
	}
	
	/**
	 * Method : replyPostList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_id
	 * @return
	 * Method 설명 : 특정 게시물에 존재하는 댓글 조회 메서드 
	 */
	@Override
	public List<ReplyVO> replyPostList(String post_id) {
		return replyDao.replyPostList(post_id);
	}
	
	/**
	 * Method : getReply
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param reply_id
	 * @return
	 * Method 설명 : 특정 댓글 정보조회 메서드
	 */
	@Override
	public ReplyVO getReply(String reply_id) {
		return replyDao.getReply(reply_id);
	}

}
