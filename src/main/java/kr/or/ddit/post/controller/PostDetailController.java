package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.append.model.AppendVO;
import kr.or.ddit.append.service.AppendServiceImpl;
import kr.or.ddit.append.service.IAppendService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet("/postDetail")
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostDetailController.class);
	
	IPostService postService;
	IBoardService boardService;
	IReplyService replyService;
	IAppendService appendService;
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
		replyService = new ReplyServiceImpl();
		appendService = new AppendServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post_Id = request.getParameter("post_Id");
		PostVO postVO = postService.getPost(post_Id);
		String board_Id = postVO.getBoard_Id();
		BoardVO boardVO = boardService.getBoard(board_Id);
		List<ReplyVO> replyList = replyService.replyPostList(post_Id);
		List<AppendVO> appendList = appendService.appendListPost(post_Id);
		
		request.setAttribute("appendList", appendList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("postVO", postVO);
		request.getRequestDispatcher("/post/post.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
