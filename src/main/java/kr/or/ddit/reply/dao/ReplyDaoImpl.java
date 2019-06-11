package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;

public class ReplyDaoImpl implements IReplyDao{
	
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("reply.insertReply",replyVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("reply.disableReply", reply_id);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVO> replyList = sqlSession.selectList("reply.replyPostList", post_id);
		sqlSession.close();
		return replyList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		ReplyVO replyVO = sqlSession.selectOne("reply.getReply",reply_id);
		sqlSession.close();
		return replyVO;
	}
	
	
	 
}
