package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTest {
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);
	
	private IUserDao userDao;
	// JUnit 실행 순서
	
	//@BeforeClass가 적용된 메서드가 1회 실행
	
	//----@Test가 적용된 모든 메서드에 대해 반복적용
	//@Before가 적용된 메서드 실행
	//@Test가 적용된 메서드 실행
	//@After가 적용된 메서드 실행
	
	//@AfterClass가 적용된 메서드가 1회 실행
	
	@BeforeClass
	public static void beforeClass() {
	
	}
	
	@Before
	public void setup() {
		userDao = new UserDao();
	}
	
	@After
	public void teardown() {
		
	}
	
	@AfterClass
	public static void afterClass() {
	
	}
	
	/**
	* Method : userListTest
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* Method 설명 : 사용자 전체조회 테스트
	*/
	@Test
	public void userListTest() {
		/***Given***/
		
		/***When***/
		List<UserVO> userList = userDao.userList();
		/***Then***/
		assertEquals("brown", userList.get(0).getUserId());
		assertEquals(105, userList.size());
		logger.debug("userList : {}", userList);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest(){
		/***Given***/
		String userId = "cony";
		/***When***/
		UserVO userVO = userDao.getUser(userId);
		/***Then***/
		assertNotNull(userVO);
//		assertEquals("cony1234", userVO.getPass());
//		assertEquals("코니", userVO.getName());
//		assertEquals("코니", userVO.getName());
		logger.debug("userVO : {}", userVO);
	}
	
	/**
	 * Method : userPagingListTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void userPagingListTest() {
		//사용자 페이징 리스트 조회
		// 고려사항
		//	- 몇 번째 페이지 조회인지?
		//	- 페이징 몇 건씩 데이터를 보여줄건지? : Query 실행 Parameter에 연관
		//	- 정렬 순서는? -> Parameter화 시킬 수 있다.
		//		--> 일단은 사용자 아이디 순으로 정렬한다.
		
		/***Given***/
		PageVO pageVO = new PageVO(2, 20);
		/***When***/
		List<UserVO> pagingList = userDao.userPagingList(pageVO);
		/***Then***/
		assertEquals(20, pagingList.size());
		logger.debug("pagingList : {}", pagingList);
		logger.debug("pageVO : {}", pageVO);
	}
	
	/**
	 * Method : usersCntTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-27 처음 생성
	 * Method 설명 : 전체 사용자 수 출력 메서드 테스트
	 */
	@Test
	public void usersCntTest() {
		/***Given***/
		/***When***/
		int usersCnt = userDao.usersCnt();
		/***Then***/
		assertEquals(105, usersCnt);
		logger.debug("usersCnt : {}", usersCnt);
	} 
	
	/**
	 * Method : insertUserTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-30 처음 생성
	 * Method 설명 : 사용자 등록 Test
	 */
	@Test
	public void insertUserTest() {
		/***Given***/
		//사용자정보를 담고 있는 vo객체 준비
		UserVO userVO = new UserVO("jakeharunt", "유승진", "Devi", "java201901", "대전광역시 중구 태평로 15 (태평동, 버드내마을아파트)", "106동 204호", "34890", new Date(), "d:/upload/noimage.png");
		/***When***/
		//userDao.insertUser()
		int result = userDao.insertUser(userVO);
		/***Then***/
		//result == 1
		logger.debug("result : {}", result);
		assertEquals(1, result);
	}
	
	/**
	 * Method : deleteUserTest
	 * 작성자 : jakeh
	 * 변경이력 :
	 * Method 설명 : 사용자 삭제 테스트
	 */
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userId = "jakeharunt";
		/***When***/
		int result = userDao.deleteUser(userId);
		/***Then***/
		assertEquals(1, result);

	}
	
	@Test
	public void updateUserTest() {
		/***Given***/
		UserVO userVO = new UserVO("jakeharunt", "updateTest", "Devi", "java201901", "대전광역시 중구 태평로 15 (태평동, 버드내마을아파트)", "106동 204호", "34890", new Date(), "noimage.png");
		/***When***/
		int updateResult = userDao.updateUser(userVO);
		/***Then***/
		assertEquals(1, updateResult);
	}
}
