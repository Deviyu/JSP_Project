package kr.or.ddit.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet("/postDel")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(PostDeleteController.class);
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post_Id = request.getParameter("post_Id");
		PostVO postVO = postService.getPost(post_Id);
		int result = postService.disablePost(post_Id);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/post?board_Id="+postVO.getBoard_Id());
		}
		
	}

}
