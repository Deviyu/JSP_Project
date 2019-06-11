package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/userModify")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class UserModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(UserModifyController.class);
	
	private IUserService userService;
	private SimpleDateFormat sdf;
	@Override
	public void init() throws ServletException {
		userService = new UserService();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		logger.debug("userId : {}", userId);
		UserVO userVO = userService.getUser(userId);
		request.setAttribute("userVO", userVO);
		request.getRequestDispatcher("/user/userModify.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("ModifyController doPost()");
		
		String userId		 = request.getParameter("userId");
		String name 		 = request.getParameter("name");
		String pass 		 = request.getParameter("pass");
		String alias 		 = request.getParameter("alias");
		String zipcd 		 = request.getParameter("zipcd");
		String addr1 		 = request.getParameter("addr1");
		String addr2 		 = request.getParameter("addr2");
		Part profile 		 = request.getPart("filename");
		String birth_str	 = request.getParameter("birth");
		String changeImg	 = request.getParameter("changeImg");
		String path = null;
		pass = KISA_SHA256.encrypt(pass);
		
		Date birth 			 = null;
		try { birth = sdf.parse(birth_str); } catch (ParseException e) { e.printStackTrace();}
		UserVO userVO_curr = userService.getUser(userId);
		
		String filename = PartUtil.getFileName(
				profile.getHeader("content-disposition"));
		
		logger.debug("changeImg : {}",changeImg);
		
		if(changeImg==null) { // 이미지 변경 안함 체크시 (null이 아닐 때)
			if(!(filename.equals(""))) { // filename이 null이 아닐 때 (파일 수정이 목적일 때)
				String extention = PartUtil.getExtention(filename);
				PartUtil.checkUploadFolder();
				File uploadFolder = new File(PartUtil.getUploadPath());
				if(uploadFolder.exists()) {
					String uploadPath = uploadFolder + File.separator + UUID.randomUUID().toString() + extention;
					profile.write(uploadPath);
					profile.delete();
					path = uploadPath;
					if(userVO_curr.getPath()!=null) {
						File curr_profile = new File(userVO_curr.getPath());
						if(curr_profile.exists()) {
							curr_profile.delete();
						}
					}
				}
			} else { //filename이 null일 때 (프로필 사진 초기화가 목적일 때)
				path = null;
			}
		} else { // 이미지 변경 안함 체크 안할 시 (변경할 목적일때)
			filename = userVO_curr.getFilename();
			path = userVO_curr.getPath();
		} 
		
		UserVO userVO = new UserVO(userId, name, alias, pass, addr1, addr2, zipcd, birth, path, filename);
		
		int result = userService.updateUser(userVO);
		
		request.setAttribute("result", result);
		response.sendRedirect(request.getContextPath()+ "/user?userId="+userId+"&result=1");
		
	}

}
