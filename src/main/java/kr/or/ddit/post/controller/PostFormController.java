package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.append.model.AppendVO;
import kr.or.ddit.append.service.AppendServiceImpl;
import kr.or.ddit.append.service.IAppendService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostFormController
 */
@WebServlet("/postForm")
@MultipartConfig(maxFileSize=1024*1024*4,maxRequestSize=1024*1024*16)
public class PostFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostFormController.class);
	
	private IBoardService boardService;
	private IPostService postService;
	private IAppendService appendService;
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
		postService = new PostServiceImpl();
		appendService = new AppendServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_Id = request.getParameter("board_Id");
		BoardVO boardVO = boardService.getBoard(board_Id);
		String replyTo = request.getParameter("replyTo");
		if(replyTo!=null) {
			PostVO replyVO = postService.getPost(replyTo);
			request.setAttribute("replyVO", replyVO);
		}
		request.setAttribute("boardVO", boardVO);
		request.getRequestDispatcher("/post/newPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO userVO = (UserVO)request.getSession().getAttribute("USER_INFO");
		String userId = userVO.getUserId();
		String board_Id = request.getParameter("board_Id");
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("smarteditor");
		Date post_date = new Date();
		String post_usable = "Y";
		String replyTo = request.getParameter("replyTo");
		logger.debug("replyTo : {}",replyTo);
		String post_group = null;
		if(!(replyTo.equals(""))) {
			PostVO replyVO = postService.getPost(replyTo);
			post_group = replyVO.getReplyTo() == null ? replyVO.getReplyTo() : replyVO.getPost_group();
		}
		
		PostVO postVO = new PostVO(userId, post_title, post_date, replyTo, post_content, board_Id, post_usable, post_group);
		int result = postService.insertPost(postVO);
		if(result == 1) {
		String postId = postService.getLastPostId();
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts) {
			if(part.getName().startsWith("file") && part.getSize()!=0) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String extentionName = PartUtil.getExtention(fileName);
				PartUtil.checkUploadFolder();
				
				File uploadFolder = new File(PartUtil.getUploadPath());
				if(uploadFolder.exists()) {
					String append_path = uploadFolder + File.separator + UUID.randomUUID().toString() + extentionName;
					String append_filename = fileName;
					AppendVO appendVO = new AppendVO(postId, append_path, append_filename);
					int appResult = appendService.insertAppend(appendVO);
					logger.debug("appendResult : {}", appResult);
					part.write(append_path);
					part.delete();
				}
			}
		}
			response.sendRedirect(request.getContextPath() + "/postDetail?post_Id="+postId);
		}
	}

}
