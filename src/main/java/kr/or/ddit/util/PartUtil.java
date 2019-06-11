package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {
	private static final String UPLOAD_PATH = "d:\\upload\\";

	/**
	 * Method : getFileName
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-03 처음 생성
	 * @param contentDisposition
	 * @return String filename
	 * Method 설명 : content-disposition 헤더에서 파일명을 반환하는 메서드
	 */
	public static String getFileName(String contentDisposition) {
		if(contentDisposition!=null) {
			String[] headers = contentDisposition.split("; ");
			for(String header : headers) {
				if(header.startsWith("filename=")){
					return header.substring(header.indexOf("\"")+1, header.lastIndexOf("\""));
				}
			}
		}
		return "";
	}
	
	/**
	 * Method : getExtention
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-03 처음 생성
	 * @param contentDispositon
	 * @return
	 * Method 설명 : content-disposition 헤더에서 파일의 확장자를 반환하는 메서드
	 */
	public static String getExtention(String fileName) {
		if(fileName!=null && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf("."));
		}
		return "";
	}
	
	/**
	 * Method : checkUploadFolder
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-03 처음 생성
	 * @param yyyy
	 * @param mm
	 * Method 설명 : 년월에 맞는 폴더가 존재하는지 확인하고, 없다면 생성해주는 메서드
	 */
	public static void checkUploadFolder() {
		Date today = new Date();
		SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
		String yyyy = yearSdf.format(today);
		String mm = monthSdf.format(today);
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		if(!yyyyFolder.exists()) 
			yyyyFolder.mkdir();
		
		File mmFolder = new File(UPLOAD_PATH + yyyy + File.separator + mm);
		if(!mmFolder.exists()) {
			mmFolder.mkdir();
		}
	}
	
	/**
	 * Method : getUploadPath
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-03 처음 생성
	 * @param yyyy
	 * @param mm
	 * @return
	 * Method 설명 : 업로드 경로를 반환
	 */
	public static String getUploadPath() {
		Date today = new Date();
		SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
		String yyyy1 = yearSdf.format(today);
		String mm1 = monthSdf.format(today);
		return UPLOAD_PATH + yyyy1 + File.separator + mm1;
	}	
}
