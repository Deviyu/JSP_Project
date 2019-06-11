package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostController.class);
	
	private IPostService postService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_Id = request.getParameter("board_Id");
		logger.debug("board_Id : {}",board_Id);
		BoardVO boardVO = boardService.getBoard(board_Id);
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		int pageSize = 10;
		int postCnt = postService.postCnt(board_Id);
		logger.debug("postCnt : {}",postCnt);
		
		int paginationSize = (int)(Math.ceil((double)postCnt / pageSize));
		logger.debug("paginationSize : {}",paginationSize);
		List<PostVO> postList = postService.postPagingList(board_Id, new PageVO(page, pageSize));
		request.setAttribute("postList", postList);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("page", page);
		request.setAttribute("paginationSize", paginationSize);
		request.getRequestDispatcher("/board/board_post.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
