package kr.or.ddit.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CookieUtilTest {

	/**
	 * Method : getCookieTest
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-28 처음 생성
	 * Method 설명 : getCookie() 테스트
	 */
	@Test
	public void getCookieTest() {
		/***Given***/
		String cookieString = "userId=brown; rememberMe=true; test=testValue";
		CookieUtil.setCookieString(cookieString);
		/***When***/
		String cookieValue = CookieUtil.getCookie("test");
		String cookieValue2 = CookieUtil.getCookie("userId");
		String cookieValue3 = CookieUtil.getCookie("rememberMe");
		String cookieValue4 = CookieUtil.getCookie("userI");
		String cookieValue5 = CookieUtil.getCookie2("test");
		String cookieValue6 = CookieUtil.getCookie2("userId");
		String cookieValue7 = CookieUtil.getCookie2("rememberMe");
		String cookieValue8 = CookieUtil.getCookie2("userI");
		/***Then***/
		assertEquals("testValue", cookieValue);
		assertEquals("brown", cookieValue2);
		assertEquals("true", cookieValue3);
		assertEquals("", cookieValue4);
		assertEquals("testValue", cookieValue5);
		assertEquals("brown", cookieValue6);
		assertEquals("true", cookieValue7);
		assertEquals("", cookieValue8);
	}

}
