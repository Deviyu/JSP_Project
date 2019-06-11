package kr.or.ddit.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ReplyDeleteController
 */
@WebServlet("/delReply")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyDeleteController.class);
	
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply_id = request.getParameter("reply_id");
		ReplyVO replyVO = replyService.getReply(reply_id);
		String post_Id = replyVO.getPost_id();
		int result = replyService.disableReply(reply_id);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/postDetail?post_Id="+post_Id);
		}
	}
}
