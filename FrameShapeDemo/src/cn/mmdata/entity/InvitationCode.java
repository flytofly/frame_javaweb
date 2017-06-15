package cn.mmdata.entity;

public class InvitationCode {
	private String invitation_id;
	private String code;
	private String calidity_date;
	private String manager;
	private int status;
	public String getInvitation_id() {
		return invitation_id;
	}
	public void setInvitation_id(String invitation_id) {
		this.invitation_id = invitation_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCalidity_date() {
		return calidity_date;
	}
	public void setCalidity_date(String calidity_date) {
		this.calidity_date = calidity_date;
	}
	
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
		

}
