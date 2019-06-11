package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

/**
 * Servlet implementation class BoardModifyController
 */
@WebServlet("/boardModify")
public class BoardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String board_Id = request.getParameter("board_Id");
		String board_name = request.getParameter("board_name");
		String board_usable = request.getParameter("board_usable");
		BoardVO boardVO = boardService.getBoard(board_Id);
		
		boardVO.setBoard_name(board_name);
		boardVO.setBoard_usable(board_usable);
		
		int result = boardService.updateBoard(boardVO);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/boardList?result="+result);
		}
		
	}

}
