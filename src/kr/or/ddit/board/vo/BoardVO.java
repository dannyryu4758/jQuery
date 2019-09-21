package kr.or.ddit.board.vo;

public class BoardVO {

	private int seq	     ;
	private String subject   ;
	private String writer    ;
	private String mail      ;
	private String passwor   ;
	private String content   ;
	private int hit	     ;
	private String wip	     ;
	private String wdate	 ;
	
	
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", subject=" + subject + ", writer=" + writer + ", mail=" + mail + ", passwor="
				+ passwor + ", content=" + content + ", hit=" + hit + ", wip=" + wip + ", wdate=" + wdate + "]";
	}
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPasswor() {
		return passwor;
	}
	public void setPasswor(String passwor) {
		this.passwor = passwor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWip() {
		return wip;
	}
	public void setWip(String wip) {
		this.wip = wip;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
	
}