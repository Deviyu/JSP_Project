package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao implements IUserDao{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDao.class);
	

	/**
	* Method : userList
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @return List<UserVO>
	* Method 설명 : 사용자 전체 조회
	*/
	@Override
	public List<UserVO> userList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVO> userList = sqlSession.selectList("user.userList");
		sqlSession.close();
		return userList;
	}
	
	/**
	* Method : getUser
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @param userId
	* @return UserVO
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVO userVO = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return userVO;
	}
	
	/**
	 * Method : userPagingList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * @param page
	 * @param pageList
	 * @return 
	 * Method 설명 : 특정 페이지의 사용자 정보 조회
	 */
	@Override
	public List<UserVO> userPagingList(PageVO pageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVO> pagingList = sqlSession.selectList("user.userPagingList", pageVO);
		sqlSession.close();
		return pagingList;
	}
	
	/*
	 * Method : usersCnt
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int usersCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int usersCnt = sqlSession.selectOne("user.usersCnt");
		sqlSession.close();
		return usersCnt;
	}
	
	/**
	 * Method : insertUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-30 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 추가 메서드
	 */
	@Override
	public int insertUser(UserVO userVO){
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("user.insertUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	/**
	 * Method : deleteUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제 메서드
	 */
	@Override
	public int deleteUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 수정 메서드
	 */
	@Override
	public int updateUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("user.updateUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	/**
	 * Method : userList_2
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param sqlSession
	 * @return
	 * Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회 메서드
	 */
	@Override
	public List<UserVO> userList_2(SqlSession sqlSession) {
		return sqlSession.selectList("user.userList_2");
	}
	
	/**
	 * Method : updateUserEncryptPass
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 적용
	 */
	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass",userVO);
	}
	
}
