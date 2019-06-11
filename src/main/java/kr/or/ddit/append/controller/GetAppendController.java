package kr.or.ddit.append.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.append.model.AppendVO;
import kr.or.ddit.append.service.AppendServiceImpl;
import kr.or.ddit.append.service.IAppendService;

/**
 * Servlet implementation class GetAppendController
 */
@WebServlet("/getAppend")
public class GetAppendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IAppendService appendService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(GetAppendController.class);
	
	@Override
	public void init() throws ServletException {
		appendService = new AppendServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String append_id = request.getParameter("append_id");
		logger.debug("append_id : {}", append_id);
		AppendVO appendVO = appendService.getAppend(append_id);
		String filename = appendVO.getAppend_filename();
		String append_path = appendVO.getAppend_path();
		
		File file = new File(append_path);
		
		String downname = new String(filename.getBytes("UTF-8"), "8859_1");
		
		response.setContentType("application/octet-stream");
		response.setHeader("content-Disposition", "attachment;filename=\"" + downname + "\";");
		
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] b = new byte[1024];
		int data = 0;
		
		while((data=(fis.read(b, 0, b.length)))!= -1){
			sos.write(b, 0, data);
		}
		
		sos.flush();
		sos.close();
		fis.close();
	}

}
