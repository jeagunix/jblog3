package kr.co.itcen.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String text;
	private String writeDate;
	private Long categoryNo;
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", text=" + text + ", writeDate=" + writeDate + ", categoryNo="
				+ categoryNo + "]";
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
