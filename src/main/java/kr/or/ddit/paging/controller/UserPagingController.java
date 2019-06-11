package kr.or.ddit.paging.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UserPagingList
 */
@WebServlet("/userPagingList")
public class UserPagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(UserPagingController.class);
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		int page = pageStr==null ? 1 : Integer.parseInt(request.getParameter("page"));
		int pageSize = pageSizeStr==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		PageVO pageVO = new PageVO(page, pageSize);
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		request.setAttribute("pagingList", resultMap.get("pagingList"));
		request.setAttribute("paginationSize", resultMap.get("paginationSize"));
		request.setAttribute("pageVO", pageVO);
		request.getRequestDispatcher("/user/userPagingList.jsp").forward(request, response);
	}
}
