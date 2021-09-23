package dao;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stuserid;
	private String stpassword;
	private String company;

	public LoginBean() {

	}
	
	public LoginBean(String stuserid,String stpassword,String company) {
		this.stuserid=stuserid;
		this.stpassword=stpassword;
		this.company=company;
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStuserid() {
		return stuserid;
	}

	public void setStuserid(String stuserid) {
		this.stuserid = stuserid;
	}

	public String getStpassword() {
		return stpassword;
	}

	public void setStpassword(String stpassword) {
		this.stpassword = stpassword;
	}

	

}
