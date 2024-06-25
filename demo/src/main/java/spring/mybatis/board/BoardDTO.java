package spring.mybatis.board;
//BOARD 테이블 1개 레코드 구성 컬럼들 = 변수
public class BoardDTO {
	int seq;
	String title, contents, writer;
	int pw, viewcount;
	String writingtime;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getWritingtime() {
		return writingtime;
	}
	public void setWritingtime(String writingtime) {
		this.writingtime = writingtime;
	}
	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", writer=" + writer + ", pw="
				+ pw + ", viewcount=" + viewcount + ", writingtime=" + writingtime + "]";
	}
	
}
