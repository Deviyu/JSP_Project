package kr.or.ddit.user.controller;

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

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProfileController.class);
	
	IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserVO userVO = userService.getUser(userId);
		String path = userVO.getPath() == null ? getServletContext().getRealPath("/image/no_image.gif") : userVO.getPath();
		
		File image = new File(path);
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = new FileInputStream(image);
		byte[] buffer = new byte[512];
		while( (fis.read(buffer, 0, 512))!= -1){
			sos.write(buffer);
		}
		
		fis.close();
		sos.close();
	}

}
