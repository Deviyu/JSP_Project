package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class PartUtilTest {
	
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(PartUtilTest.class);
	@Test
	public void getFilenameTest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"profile\"; filename=\"74516598.1.jpg\"";
		String contentDisposition2 = "form-data; name=\"profile\"; filename=\"1234.jpg\"";
		/***When***/
		String fileName = PartUtil.getFileName(contentDisposition);
		String fileName2 = PartUtil.getFileName(contentDisposition2);
		/***Then***/
		assertEquals("74516598.1.jpg", fileName);
		assertEquals("1234.jpg", fileName2);
	}
	
	@Test
	public void getFilenameTest2() {
		/***Given***/
		String contentDisposition = "form-data; name=\"profile\"";
		/***When***/
		String fileName = PartUtil.getFileName(contentDisposition);
		/***Then***/
		assertEquals("", fileName);
	}
	
	@Test
	public void getFileName_NullTest(){
		/***Given***/
		String contentDisposition = null;
		/***When***/
		String fileName = PartUtil.getFileName(contentDisposition);
		/***Then***/
		assertEquals("", fileName);
	}
	
	@Test
	public void uuidTest() {
		/***Given***/
		
		/***When***/
		logger.debug(UUID.randomUUID().toString());
		/***Then***/

	}
	@Test
	public void getExtentionTest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"profile\"; filename=\"74516598.1.jpg\"";
		String contentDisposition2 = "form-data; name=\"profile\"; filename=\"745165981.jpg\"";
		String contentDisposition3 = "form-data; name=\"profile\"; filename=\"745165981\"";
		/***When***/
		String fileName = PartUtil.getFileName(contentDisposition);
		String fileName2 = PartUtil.getFileName(contentDisposition2);
		String fileName3 = PartUtil.getFileName(contentDisposition3);
		String extention = PartUtil.getExtention(fileName);
		String extention2 = PartUtil.getExtention(fileName2);
		String extention3 = PartUtil.getExtention(fileName3);
		logger.debug("extention : {}", extention);
		logger.debug("extention2 : {}", extention2);
		logger.debug("extention3 : {}", extention3);
		/***Then***/
		assertEquals(".jpg", extention);
		assertEquals(".jpg", extention2);
		assertEquals("", extention3);
	}
}
