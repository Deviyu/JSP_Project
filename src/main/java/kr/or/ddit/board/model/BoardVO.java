package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVO {
	private String board_Id;
	private String userId;
	private String board_name;
	private String board_usable;
	private Date board_date;
	
	
	public BoardVO() {
		
	}
	
	public BoardVO(String board_Id, String userid, String board_name,
			String board_usable, Date board_date) {
		super();
		this.board_Id = board_Id;
		this.userId = userid;
		this.board_name = board_name;
		this.board_usable = board_usable;
		this.board_date = board_date;
	}
	
	public BoardVO(String userId, String board_name, String board_usable,
			Date board_date) {
		this.userId = userId;
		this.board_name = board_name;
		this.board_usable = board_usable;
		this.board_date = board_date;
	}

	public String getBoard_id() {
		return board_Id;
	}
	
	public void setBoard_id(String board_Id) {
		this.board_Id = board_Id;
	}
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userId) {
		this.userId = userId;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_usable() {
		return board_usable;
	}
	public void setBoard_usable(String board_usable) {
		this.board_usable = board_usable;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	@Override
	public String toString() {
		return "BoardVO [board_Id=" + board_Id + ", userId=" + userId
				+ ", board_name=" + board_name + ", board_usable="
				+ board_usable + ", board_date=" + board_date + "]";
	}
	
}
