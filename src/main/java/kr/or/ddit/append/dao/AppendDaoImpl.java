package kr.or.ddit.append.dao;

import java.util.List;

import kr.or.ddit.append.model.AppendVO;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class AppendDaoImpl implements IAppendDao{
	
	/**
	 * Method : insertAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param appendVO
	 * @return
	 * Method 설명 : 첨부파일 추가 메서드
	 */
	@Override
	public int insertAppend(AppendVO appendVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("append.insertAppend", appendVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	/**
	 * Method : deleteAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 첨부파일 삭제 메서드
	 */
	@Override
	public int deleteAppend(String append_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.delete("append.deleteAppend", append_id);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	/**
	 * Method : appendListPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_id
	 * @return
	 * Method 설명 : 게시물에 할당된 첨부파일 조회 메서드
	 */
	@Override
	public List<AppendVO> appendListPost(String post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AppendVO> appendList = sqlSession.selectList("append.appendListPost", post_id);
		sqlSession.close();
		return appendList;
	}
	
	/**
	 * Method : getAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 특정 첨부파일 조회 메서드
	 */
	@Override
	public AppendVO getAppend(String append_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AppendVO appendVO = sqlSession.selectOne("append.getAppend", append_id);
		sqlSession.close();
		return appendVO;
	}

}
