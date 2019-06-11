package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService implements IUserService{
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	
	IUserDao dao;
	
	
	public UserService() {
		dao = new UserDao();
	}
	
	/**
	* Method : userList
	* 작성자 : PC07
	* 변경이력 : 2019-05-23 처음 생성
	* 			 2019-05-24 Dao와 연결
	* @return List<UserVO> userList
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVO> userList() {
    	List<UserVO> userList = null;
		
    	userList = dao.userList();
    			
		return userList;
	}


	/**
	* Method : getUser
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		UserVO userVO = null;
		
		userVO = dao.getUser(userId);
		
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
	public Map<String, Object> userPagingList(PageVO pageVO) {
		//1. List<UserVO>, int를 필드로 하는 VO를 만들거나
		//2. List<Object> resultList를 만들거나 -- 비추천
		
		//3. HashMap<String, Object> resultMap을 만들거나
		//	resultMap.put("pagingList", pagingList);
		//	resultMap.put("usersCnt", usersCnt);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagingList", dao.userPagingList(pageVO));
		
		//usersCnt -> paginationSize로 변경
		int usersCnt = dao.usersCnt();
		int pageSize = pageVO.getPageSize();
		int paginationSize = (int)(Math.ceil((double)usersCnt/pageSize));
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}
	
	/**
	 * Method : insertUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-30 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 등록 메서드
	 */
	@Override
	public int insertUser(UserVO userVO) {
		return dao.insertUser(userVO);
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 삭제 메서드
	 */
	@Override
	public int deleteUser(String userId) {
		return dao.deleteUser(userId);
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
		return dao.updateUser(userVO);
	}

	/**
	 * Method : encryptAllUserPass
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-04 처음 생성
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 일괄 적용 Batch
	 * 			   :		절 대 실 행 하 지 마
	 */
	@Override
	public int encryptAllUserPass() {
		// 실행흐즈믈르그 흐뜨
		if(1==1) return 0;
		//0. sql 실행에 필요한 sqlSession 객체를 생성
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//1. 모든 사용자 정보 조회(단, 기존 암호화 적용 사용자 제외 -> brain, jakeharunt)
		List<UserVO> userList = dao.userList_2(sqlSession);
		//2. 조회된 사용자의 비밀번호를 암호화 적용후 사용자 업데이트
		int cntSum = 0;
		for(UserVO userVO : userList) {
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			int cnt = dao.updateUserEncryptPass(sqlSession, userVO);
			cntSum += cnt;
			if(cnt != 1) {
				sqlSession.rollback();
				break;
			}
		}
		//3. sqlSession 객체를 commit, close.
		sqlSession.commit();
		sqlSession.close();
		return cntSum;
	}
	
//	public static void main(String[] args) {
//		IUserService userService = new UserService();
//		int cnt = userService.encryptAllUserPass();
//		logger.debug("cnt : {}",cnt);
//	}
}
