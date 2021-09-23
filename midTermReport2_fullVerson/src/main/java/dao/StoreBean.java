package dao;

import java.io.Serializable;

public class StoreBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String storeid;
	private String startdate;
	private String stuserid;
	private String title;
	private String manager;
	private String stadd;
	private String tel;
	private String intro;
	
	

	// 可設定及存取：stuserid,sttitle,manager,stadd,tell,intro,*photo

	// 只能存取：storeid,startdate

	public StoreBean() {

	}

	public StoreBean(String stuserid, String title, String manager, String stadd, String tel, String intro) {
		this.stuserid = stuserid;
		this.title = title;
		this.manager = manager;
		this.stadd = stadd;
		this.tel = tel;
		this.intro = intro;

	}

	public String getStuserid() {
		return stuserid;
	}

	public void setStuserid(String stuserid) {
		this.stuserid = stuserid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStadd() {
		return stadd;
	}

	public void setStadd(String stadd) {
		this.stadd = stadd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

//	public Blob getPhoto() {
//		return photo;
//	}
//	public void setPhoto(Blob photo) {
//		this.photo = photo;
//	}

}
