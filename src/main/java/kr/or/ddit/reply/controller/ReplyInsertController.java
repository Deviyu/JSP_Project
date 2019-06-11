package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/replyInsert")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyInsertController.class);
	
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO userVO = (UserVO)request.getSession().getAttribute("USER_INFO");
		String userId = userVO.getUserId();
		String reply_content = request.getParameter("reply_content");
		Date reply_date = new Date();
		String post_id = request.getParameter("post_Id");
		String reply_usable = "Y";
		
		ReplyVO replyVO = new ReplyVO(post_id, reply_content, reply_date, userId, reply_usable);
		
		int result = replyService.insertReply(replyVO);
		
		if(result == 1) {
			response.sendRedirect(request.getContextPath()+"/postDetail?post_Id="+post_id);
		}
	}

}
