package kr.or.ddit.append.model;

public class AppendVO {
	private String append_id;
	private String post_id;
	private String append_path;
	private String append_filename;
	
	public AppendVO() {}
	
	public AppendVO(String post_id, String append_path, String append_filename) {
		this.post_id = post_id;
		this.append_path = append_path;
		this.append_filename = append_filename;
	}

	public AppendVO(String append_id, String post_id, String append_path,
			String append_filename) {
		this(post_id, append_path, append_filename);
		this.append_id = append_id;
	}

	public String getAppend_id() {
		return append_id;
	}
	public void setAppend_id(String append_id) {
		this.append_id = append_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getAppend_path() {
		return append_path;
	}
	public void setAppend_path(String append_path) {
		this.append_path = append_path;
	}
	public String getAppend_filename() {
		return append_filename;
	}
	public void setAppend_filename(String append_filename) {
		this.append_filename = append_filename;
	}

	@Override
	public String toString() {
		return "AppendVO [append_id=" + append_id + ", post_id=" + post_id
				+ ", append_path=" + append_path + ", append_filename="
				+ append_filename + "]";
	}
	
	
	
}
