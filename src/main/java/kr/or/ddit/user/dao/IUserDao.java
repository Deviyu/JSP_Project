package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;

public interface IUserDao {
	/**
	* Method : userList
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @return
	* Method 설명 : 사용자 전체 조회
	*/
	List<UserVO> userList();
	
	/**
	* Method : getUser
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVO getUser(String userId);
	
	/**
	 * Method : userPagingList
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * @param page
	 * @param pageList
	 * @return 
	 * Method 설명 : 특정 페이지의 사용자 정보 조회
	 */
	List<UserVO> userPagingList(PageVO pageVO);
	
	/**
	 * Method : usersCnt
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	int usersCnt();
	
	/**
	 * Method : insertUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-30 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 추가 메서드
	 */
	int insertUser(UserVO userVO);
	
	/**
	 * Method : deleteUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제 메서드
	 */
	int deleteUser(String userId);
	
	/**
	 * Method : updateUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 수정 메서드
	 */
	int updateUser(UserVO userVO);
	
	/**
	 * Method : userList_2
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param sqlSession
	 * @return
	 * Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회 메서드
	 */
	List<UserVO> userList_2(SqlSession sqlSession);

	/**
	 * Method : updateUserEncryptPass
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 적용
	 */
	int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO);
}
