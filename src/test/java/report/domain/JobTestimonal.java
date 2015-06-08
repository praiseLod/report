package report.domain;

/**
 * @author praiseLod
 * @date 2015年6月5日
 * @version
 */
public class JobTestimonal {
	private String userName;
	private String job;
	private String company_tel;
	private String date;
	private Integer salary;
	private String duty;
	private String idCard;
	private String responser;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getResponser() {
		return responser;
	}
	public void setResponser(String responser) {
		this.responser = responser;
	}
	@Override
	public String toString() {
		return "JobTestimonal [userName=" + userName + ", job=" + job
				+ ", company_tel=" + company_tel + ", date=" + date
				+ ", salary=" + salary + ", duty=" + duty + ", idCard="
				+ idCard + ", responser=" + responser + "]";
	}
	
	
}
