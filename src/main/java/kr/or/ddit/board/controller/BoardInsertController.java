package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class InsertBoardController
 */
@WebServlet("/insertBoard")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(BoardInsertController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String board_name = request.getParameter("board_name");
		String board_usable = request.getParameter("board_usable");
		Date board_date = new Date();
		UserVO userVO = (UserVO)request.getSession().getAttribute("USER_INFO");
		String userId = userVO.getUserId();
		logger.debug("board_name  : {}", board_name);
		logger.debug("board_usable : {}", board_usable);
		logger.debug("board_date : {}", board_date);
		logger.debug("userId : {}", userId);
		BoardVO boardVO = new BoardVO(userId, board_name, board_usable, board_date);
		
		int result = boardService.insertBoard(boardVO);
		if(result==1) {
			response.sendRedirect(request.getContextPath()+"/boardList?result="+result);
		}
	}

}
