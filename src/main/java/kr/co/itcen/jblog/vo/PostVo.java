package kr.co.itcen.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String content;
	private String writeDate;
	private Long categoryNo;
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

}
