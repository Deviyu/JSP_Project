package kr.or.ddit.append.dao;

import java.util.List;

import kr.or.ddit.append.model.AppendVO;

public interface IAppendDao {
	
	
	/**
	 * Method : insertAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param appendVO
	 * @return
	 * Method 설명 : 첨부파일 추가 메서드
	 */
	int insertAppend(AppendVO appendVO);
	
	/**
	 * Method : deleteAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 첨부파일 삭제 메서드
	 */
	int deleteAppend(String append_id);
	
	/**
	 * Method : appendListPost
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-07 처음 생성
	 * @param post_id
	 * @return
	 * Method 설명 : 게시물에 할당된 첨부파일 조회 메서드
	 */
	List<AppendVO> appendListPost(String post_id);
	
	/**
	 * Method : getAppend
	 * 작성자 : jakeh
	 * 변경이력 : 2019-06-10 처음 생성
	 * @param append_id
	 * @return
	 * Method 설명 : 특정 첨부파일 조회 메서드
	 */
	AppendVO getAppend(String append_id);
}
