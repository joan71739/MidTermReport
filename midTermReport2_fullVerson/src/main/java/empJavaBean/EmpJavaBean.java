package empJavaBean;


import java.io.Serializable;
import java.sql.Date;

public class EmpJavaBean implements Serializable{
	private int id;
	private String name;
	private String job;
	private String hiredate;
	private int sal;
	
public EmpJavaBean() {
		
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getJob() {
	return job;
}

public void setJob(String job) {
	this.job = job;
}

public String getHiredate() {
	return hiredate;
}

public void setHiredate(String hiredate) {
	this.hiredate = hiredate;
}

public int getSal() {
	return sal;
}

public void setSal(int sal) {
	this.sal = sal;
}


}
