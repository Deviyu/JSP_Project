package kr.or.ddit.util;

public class CookieUtil {
	public static String cookieString; // 분석할 쿠키 문자열
	
	/**
	 * Method : setCookieString
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-28 처음 생성
	 * @param cookieString
	 * Method 설명 : 분석할 쿠키 문자열을 받는 메서드.
	 */
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
	}
	/**
	 * Method : getCookie
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-28 처음 생성
	 * @param string
	 * @return
	 * Method 설명 : 쿠키 문자열에서 특정 쿠키 값(Value)을 반환하는 메서드
	 */
	public static String getCookie(String cookie) {
		String cookieValue = "";
		if(cookieString != null) {
			String[] cookieArr = cookieString.split("; ");
			for(int i = 0; i<cookieArr.length; i++) {
				String cookie_i = cookieArr[i];
				if(cookie_i.split("=")[0].equals(cookie)) {
					cookieValue = cookie_i.split("=")[1]; 
					break;
				}
			}
		}
		return cookieValue;
	}
	
	
	/**
	 * Method : getCookie2
	 * 작성자 : jakeh
	 * 변경이력 : 2019-05-28 처음 생성
	 * @param cookie
	 * @return
	 * Method 설명 : 쿠키 문자열에서 특정 쿠키 값(Value)을 반환하는 메서드 - 선생님 Ver
	 */
	public static String getCookie2(String cookie) {
		String[] cookieArray = CookieUtil.cookieString.split("; ");
		String cookieValue = "";
		for(String str : cookieArray) {
			if(str.startsWith(cookie+"=")) {
				String[] cookieStr = str.split("=");
				cookieValue = cookieStr[1];
				break;
			}
		}
		return cookieValue;
	}
}
