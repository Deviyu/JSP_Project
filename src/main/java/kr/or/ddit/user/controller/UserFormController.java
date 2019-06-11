package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

/**
 * Servlet implementation class UserCreateController
 */
@WebServlet("/userForm")
@MultipartConfig(maxFileSize=1024*1024*3,maxRequestSize=1024*1024*15)
public class UserFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(UserFormController.class);
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자 등록 화면으로 이동
		request.getRequestDispatcher("/user/userForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String userId		 = request.getParameter("userId");
		String name 		 = request.getParameter("name");
		String pass 		 = request.getParameter("pass");
		String alias 		 = request.getParameter("alias");
		String zipcd 		 = request.getParameter("zipcd");
		String addr1 		 = request.getParameter("addr1");
		String addr2 		 = request.getParameter("addr2");
		String filename 	 = request.getParameter("filename");
		String birth_str	 = request.getParameter("birth");
		
		String pass_enc = KISA_SHA256.encrypt(pass);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth 			 = null;
		try { birth = sdf.parse(birth_str); } catch (ParseException e) { e.printStackTrace();}
		
		

		
		UserVO userVO = new UserVO(userId, name, alias, pass_enc,
				addr1, addr2, zipcd, birth, filename);
		
		//사용자가 입력한 userId가 이미 존재하는 userId인지 체크
		UserVO checkDupl = userService.getUser(userId);
		logger.debug("checkDupl : {}",checkDupl);
		//존재하지 않을 경우
		if(checkDupl==null) {
			
			//profile 파일 업로드 처리
			Part profile_part = request.getPart("filename");
			
			if(profile_part.getSize()>0) {
				//사용자가 파일을 업로드한 경우
				String contentDisposition = profile_part.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String extention = PartUtil.getExtention(fileName);
				
				//연도에 해당하는 폴더가 있는지, 연도안에 월에 해당하는 폴더가 있는지
				PartUtil.checkUploadFolder();
				File uploadFolder = new File(PartUtil.getUploadPath());
				if(uploadFolder.exists()) {
					String path = uploadFolder+ File.separator + UUID.randomUUID().toString() + extention;
					profile_part.write(path);
					profile_part.delete();
					userVO.setPath(path);
					userVO.setFilename(fileName);
				} 
			}
			int result = userService.insertUser(userVO);
			if(result == 1) {
				//정상입력시
				//사용자 페이징리스트 1페이지로 이동
				response.sendRedirect(request.getContextPath() + "/userPagingList?result=1");
			} else {
				//비정상처리시
				//사용자가 입력한 값을 input에 다시 넣어준다.
				request.getRequestDispatcher("/user/userForm.jsp").forward(request, response);
			}
		} else {
		//존재할 경우
			//이미 존재하는 userId입니다. 라는 alert창을 띄운다.
			//사용자 등록페이지로 이동, 사용자가 입력한 값을 input에 넣어준다.
			request.setAttribute("duplId", "true");
			doGet(request, response);
		}
		
	}

}
