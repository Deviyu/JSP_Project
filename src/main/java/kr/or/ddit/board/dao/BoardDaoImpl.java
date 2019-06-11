package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class BoardDaoImpl implements IBoardDao {
	
	@Override
	public List<BoardVO> boardList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.boardList");
		sqlSession.close();
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("board.insertBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int deleteBoard(String board_Id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.delete("board.deleteBoard", board_Id);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("board.updateBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int switchStatus(BoardVO boardVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("board.switchStatus", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public BoardVO getBoard(String board_Id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.getBoard", board_Id);
		sqlSession.close();
		return boardVO;
	}

}
