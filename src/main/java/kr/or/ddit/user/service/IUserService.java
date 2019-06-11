package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	
	/**
	* Method : userList
	* 작성자 : PC07
	* 변경이력 : 2019-05-23 처음 생성
	* @return List<UserVO> userList
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	List<UserVO> userList();
	
	/**
	* Method : getUser
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 정보 조회
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
	Map<String, Object> userPagingList(PageVO pageVO);
	
	/**
	 * Method : insertUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-30 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 등록 메서드
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
	 * Method : encryptAllUserPass
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 일괄 적용 Batch
	 */
	int encryptAllUserPass();
}
