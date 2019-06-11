package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceTest.class);
	
	private IUserService userService;
	
	@Before
	public void setup() {
		userService = new UserService();
	}
	
	/**
	* Method : userListTest
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* Method 설명 : 전체 사용자 조회 기능 테스트
	*/
	@Test
	public void userListTest() {
		/***Given***/
		/***When***/
		List<UserVO> userList = userService.userList();
		/***Then***/
		assertEquals("brown", userList.get(0).getUserId());
		assertEquals(105, userList.size());
		logger.debug("userList : {}", userList);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC07
	* 변경이력 : 2019-05-24 처음 생성
	* Method 설명 : 특정 사용자 정보조회 기능 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "cony";
		/***When***/
		UserVO userVO = userService.getUser(userId);
		/***Then***/
		assertEquals("cony", userVO.getUserId());
		assertEquals("토끼", userVO.getAlias());
		assertEquals("코니", userVO.getName());
		logger.debug("userVO : {}", userVO); 
	}
	
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(3, 10);
		/***When***/
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		List<UserVO> pagingList = (List<UserVO>) resultMap.get("pagingList");
		int paginationSize = (int) resultMap.get("paginationSize");
		/***Then***/
		
		//pagingList Test
		assertNotNull(pagingList);
		assertEquals(10, pagingList.size());
		//userCnt Test
		assertEquals(11, paginationSize);
		
		logger.debug("pagingList : {}",pagingList);
		logger.debug("pageVO : {}", pageVO);
		logger.debug("paginationSize : {}",paginationSize);
	}
	
	@Test
	public void ceilTest() {
		int usersCnt = 105;
		int pageSize = 10;
		
		double paginationSize = Math.ceil((double)usersCnt/pageSize);
		assertEquals(11, (int)paginationSize);
	}
	
	/**
	 * Method : insert_DeleteUserTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * Method 설명 : 사용자 추가 / 삭제 테스트
	 */
	@Test
	public void insert_DeleteUserTest() {
		/***Given***/
		String userId = "jakeharunt";
		UserVO userVO = new UserVO(userId, "유승진", "Devi", "java201901", "대전광역시 중구 태평로 15 (태평동, 버드내마을아파트)", "106동 204호", "34890", new Date(), "noimage.png");
		/***When***/
		int insertResult = userService.insertUser(userVO);
		int deleteResult = userService.deleteUser(userId);
		/***Then***/
		assertEquals(1, insertResult);
		assertEquals(1, deleteResult);
	}
	
	/**
	 * Method : updateUserTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-31 처음 생성
	 * Method 설명 : 사용자 수정 테스트
	 */
	@Test
	public void updateUserTest() {
		/***Given***/
		UserVO userVO = new UserVO("jakeharunt", "유승진22", "Devi22", "22java201901", "대전광역시 중구 태평로 15 (태평동, 버드내마을아파트)", "104동 206호", "34890", new Date(), "noimage.png");
		/***When***/
		int updateResult = userService.updateUser(userVO);
		/***Then***/
		assertEquals(1, updateResult);
	}
	
}
