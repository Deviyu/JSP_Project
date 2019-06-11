package kr.or.ddit.post.model;

import java.util.Date;

public class PostVO {
	private String post_Id;
	private String userId;
	private String post_title;
	private Date post_date;
	private String post_content;
	private String replyTo;
	private String board_Id;
	private String post_usable;
	private String post_group;
	private int	lv;
	private int rn;
	private int seq;
	
	public PostVO() {
		super();
	}
	
	public PostVO(String userId, String post_title, Date post_date,
			String replyTo, String post_content, String board_Id, String post_usable, String post_group) {
		super();
		this.userId = userId;
		this.post_title = post_title;
		this.post_date = post_date;
		this.post_content = post_content;
		this.replyTo = replyTo;
		this.board_Id = board_Id;
		this.post_usable = post_usable;
		this.post_group = post_group;
	}
	
	public PostVO(String post_Id, String userId, String post_title,
			Date post_date, String replyTo, String board_Id, String post_usable, String post_group, String post_content) {
		this(userId, post_title, post_date, post_content,
			replyTo, board_Id, post_usable, post_group);
		this.post_Id = post_Id;
	}
	
	public PostVO(String post_Id, String userId, String post_title,
			Date post_date, String post_content, String replyTo,
			String board_Id, String post_usable, String post_group, int lv,
			int rn, int seq) {
		super();
		this.post_Id = post_Id;
		this.userId = userId;
		this.post_title = post_title;
		this.post_date = post_date;
		this.post_content = post_content;
		this.replyTo = replyTo;
		this.board_Id = board_Id;
		this.post_usable = post_usable;
		this.post_group = post_group;
		this.lv = lv;
		this.rn = rn;
		this.seq = seq;
	}

	public String getPost_Id() {
		return post_Id;
	}
	public void setPost_Id(String post_Id) {
		this.post_Id = post_Id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public String getBoard_Id() {
		return board_Id;
	}
	public void setBoard_Id(String board_Id) {
		this.board_Id = board_Id;
	}
	public String getPost_usable() {
		return post_usable;
	}
	public void setPost_usable(String post_usable) {
		this.post_usable = post_usable;
	}

	public String getPost_group() {
		return post_group;
	}

	public void setPost_group(String post_group) {
		this.post_group = post_group;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}
	

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	@Override
	public String toString() {
		return "PostVO [post_Id=" + post_Id + ", userId=" + userId
				+ ", post_title=" + post_title + ", post_date=" + post_date
				+ ", post_content=" + post_content + ", replyTo=" + replyTo
				+ ", board_Id=" + board_Id + ", post_usable=" + post_usable
				+ ", post_group=" + post_group + ", lv=" + lv + ", rn=" + rn
				+ ", seq=" + seq + "]";
	}




	
}
