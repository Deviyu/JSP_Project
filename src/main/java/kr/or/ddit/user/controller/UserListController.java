package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/userList")
public class UserListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// userList 객체를 userList.jsp에서 참고할 수 있도록 request객체에 속성으로 넣어준다.
    	request.setAttribute("userList", userService.userList());
    	
    	// userList객체를 이용하여 사용자 화면을 생성하는 userList.jsp에게 위임
    	request.getRequestDispatcher("/user/userList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
