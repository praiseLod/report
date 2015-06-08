package report.word;

import java.util.Map;

import javax.naming.spi.Resolver;

import report.domain.JobTestimonal;

import com.fdauto.report.resovler.impl.BaseParamResolver;

public class ResolverTest {

	public static void main(String[] args) {
		BaseParamResolver resolver = new BaseParamResolver();
		
		JobTestimonal job = new JobTestimonal();
		job.setCompany_tel("1589485");
		job.setDate("15年2月18日");
		job.setDuty("经理");
		job.setIdCard("5984152184145");
		job.setJob("研发");
		job.setSalary(56);
		job.setUserName("张三");
		job.setResponser("王总");
		
		
		
	}

}
