package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/boardList")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService service;
	
	@Override
	public void init() throws ServletException {
		service = new BoardServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("boardList", service.boardList());
		request.getRequestDispatcher("/board/boardList.jsp").forward(request, response);
	}

}
