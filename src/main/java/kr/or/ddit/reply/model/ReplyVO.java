package kr.or.ddit.reply.model;

import java.util.Date;

public class ReplyVO {
	private String reply_id;
	private String post_id;
	private String reply_content;
	private Date reply_date;
	private String userid;
	private String reply_usable;
	
	
	public ReplyVO() {}
	
	public ReplyVO(String post_id, String reply_content, Date reply_date,
			String userid, String reply_usable) {
		super();
		this.post_id = post_id;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
		this.userid = userid;
		this.reply_usable = reply_usable;
	}
	
	public ReplyVO(String reply_id, String post_id, String reply_content,
			Date reply_date, String userid, String reply_usable) {
		super();
		this.reply_id = reply_id;
		this.post_id = post_id;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
		this.userid = userid;
		this.reply_usable = reply_usable;
	}

	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getReply_usable() {
		return reply_usable;
	}
	public void setReply_usable(String reply_usable) {
		this.reply_usable = reply_usable;
	}

	@Override
	public String toString() {
		return "ReplyVO [reply_id=" + reply_id + ", post_id=" + post_id
				+ ", reply_content=" + reply_content + ", reply_date="
				+ reply_date + ", userid=" + userid + ", reply_usable="
				+ reply_usable + "]";
	}
	
	
}
