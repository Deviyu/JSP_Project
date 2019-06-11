package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostModifyController
 */
@WebServlet("/postMdf")
@MultipartConfig(maxFileSize=1024*1024*4,maxRequestSize=1024*1024*20)
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostModifyController.class);
	
	private IPostService postService;
	private IBoardService boardService;
	private IAppendService appendService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
		appendService = new AppendServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String post_id = request.getParameter("post_Id");
		PostVO postVO = postService.getPost(post_id);
		PostVO reply_PostVO = postVO.getReplyTo() == null ? null : postService.getPost(postVO.getReplyTo());
		BoardVO boardVO = boardService.getBoard(postVO.getBoard_Id());
		List<AppendVO> appendList = appendService.appendListPost(post_id);
		if(reply_PostVO != null) {
			request.setAttribute("reply_PostVO", reply_PostVO);
		}
		request.setAttribute("appendList", appendList);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("postVO", postVO);
		
		request.getRequestDispatcher("/post/modifyPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String post_id = request.getParameter("post_id");
		logger.debug("PostMdf doPost");
		logger.debug("post_id : {}",post_id);
		PostVO postVO = postService.getPost(post_id);
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("smarteditor");
		postVO.setPost_title(post_title);
		postVO.setPost_content(post_content);
		
		String[] delFiles = request.getParameter("delFiles").split(", ");
		
		for(String append_id : delFiles) {
			logger.debug("append_id : {}", append_id);
			appendService.deleteAppend(append_id);
		}
		
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts) {
			logger.debug("part.getName() : {}", part.getName());
			if(part.getName().startsWith("file") && part.getSize()!=0) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String extentionName = PartUtil.getExtention(fileName);
				PartUtil.checkUploadFolder();
				
				File uploadFolder = new File(PartUtil.getUploadPath());
				if(uploadFolder.exists()) {
					String append_path = uploadFolder + File.separator + UUID.randomUUID().toString() + extentionName;
					if(fileName!="") {
						String append_filename = fileName;
						AppendVO appendVO = new AppendVO(post_id, append_path, append_filename);
						int appResult = appendService.insertAppend(appendVO);
						logger.debug("appendResult : {}", appResult);
						part.write(append_path);
						part.delete();
					}
				}
			}
		}
		
		int result = postService.updatePost(postVO);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/postDetail?post_Id="+post_id);
		}
		
	}

}
