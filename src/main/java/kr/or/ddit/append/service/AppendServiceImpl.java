package kr.or.ddit.append.service;

import java.io.File;
import java.util.List;

import kr.or.ddit.append.dao.AppendDaoImpl;
import kr.or.ddit.append.dao.IAppendDao;
import kr.or.ddit.append.model.AppendVO;

public class AppendServiceImpl implements IAppendService{
	
	IAppendDao appendDao;
	
	public AppendServiceImpl() {
		appendDao = new AppendDaoImpl();
	}
	/**
	 * Method : insertAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param appendVO
	 * @return
	 * Method 설명 : 첨부파일 추가 메서드
	 */
	@Override
	public int insertAppend(AppendVO appendVO) {
		return appendDao.insertAppend(appendVO);
	}
	
	/**
	 * Method : deleteAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 첨부파일 삭제 메서드
	 */
	@Override
	public int deleteAppend(String append_id) {
		AppendVO appendVO = getAppend(append_id);
		int result = appendDao.deleteAppend(append_id);
		if(result==1) {
			File file = new File(appendVO.getAppend_path());
			if(file.exists()){
				file.delete();
			}
		}
		return result;
	}

	/**
	 * Method : appendListPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_id
	 * @return
	 * Method 설명 : 게시물에 할당된 첨부파일 조회 메서드
	 */
	@Override
	public List<AppendVO> appendListPost(String post_id) {
		return appendDao.appendListPost(post_id);
	}
	
	/**
	 * Method : getAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 특정 첨부파일 조회 메서드
	 */
	@Override
	public AppendVO getAppend(String append_id) {
		return appendDao.getAppend(append_id);
	}
	

}
