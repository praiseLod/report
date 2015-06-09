package report.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import report.domain.JobTestimonal;
import report.domain.Salary;

import com.fdauto.report.ReportEngine.ReportType;
import com.fdauto.report.ReportTemplate;
import com.fdauto.report.word.WordContext;
import com.fdauto.report.word.WordEngine;
import com.fdauto.report.word.custom.ImageParamHandler;
import com.fdauto.report.word.impl.AsposeWordContext;
import com.fdauto.report.word.impl.AsposeWordEngine;
import com.fdauto.report.word.impl.AsposeWordTemplate;

public class AsposeWordTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		normal();
	}
	
	private static void normal() throws FileNotFoundException {
		//定义模板引擎
		WordEngine engine = new AsposeWordEngine();
		
		//设置内容
		WordContext context = new AsposeWordContext();
		context.put(getParam(), JobTestimonal.class);	//基础对象
		context.put("pic", "WordConvert.png");			//图片
		//context.put("pic2", getByte());                 //图片2
		
		context.putTableParam("user", getRangeParam(), JobTestimonal.class);//表格
		//表格嵌套
		context.putTableParam("salary", getSalarys(), Salary.class);
		context.putTableParam("user2", getRangeParam(), JobTestimonal.class);
		//处理图片大小  --模板变量域处理器
		context.putParamHandler(new ImageParamHandler("pic",100, 50));
		
		//定义模板
		ReportTemplate template = new AsposeWordTemplate("收入证明.doc");
		
		//整合数据
		engine.merge(context, template);
		
		//输出
		engine.save(new FileOutputStream("e://1.doc"),ReportType.DOC);
		
	}
	
	
	
	
	
	
	
	private static byte[] getByte(){
		
		try {
			File image = new File("WordConvert.png");
			FileInputStream inputStream = new FileInputStream(image);
			byte[] imageBtye = new byte[inputStream.available()];
			inputStream.read(imageBtye);
			inputStream.close();
			return imageBtye;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return null;
	}
	private static ArrayList<Salary> getSalarys(){
		ArrayList<Salary>  list = new ArrayList<Salary>();
		
		Salary s1 = new Salary();
		s1.setBase("85元");
		s1.setBx("158元");
		s1.setBz("15元");
		s1.setJb("526元");
		list.add(s1);
		
		Salary s2 = new Salary();
		s2.setBase("596元");
		s2.setBx("68元");
		s2.setBz("95元");
		s2.setJb("566元");
		list.add(s2);
		
		
		return list;
	}
	
	private static Object getParam() {
		JobTestimonal testimonal = new JobTestimonal();
		testimonal.setCompany_tel("1589485");
		testimonal.setDate("15年2月18日");
		testimonal.setDuty("经理");
		testimonal.setIdCard("5984152184145");
		testimonal.setJob("研发");
		testimonal.setSalary(56);
		testimonal.setUserName("张三");
		testimonal.setResponser("王总");
		
		return testimonal;
	}
	private static ArrayList<JobTestimonal> getRangeParam() {
		ArrayList<JobTestimonal> testimonals = new ArrayList<JobTestimonal>();
		
		JobTestimonal testimonal = new JobTestimonal();
		testimonal.setCompany_tel("4894648");
		testimonal.setDate("2615年8月28日");
		testimonal.setDuty("组长");
		testimonal.setIdCard("8986549876");
		testimonal.setJob("研发");
		testimonal.setSalary(56);
		testimonal.setUserName("李四");
		testimonal.setResponser("王总");
		testimonals.add(testimonal);
		
		JobTestimonal t1 = new JobTestimonal();
		t1.setCompany_tel("89435463");
		t1.setDate("15年4月18日");
		t1.setDuty("员工");
		t1.setIdCard("68981687897");
		t1.setJob("销售");
		t1.setSalary(56);
		t1.setUserName("王五");
		t1.setResponser("王总");
		testimonals.add(t1);
		
		JobTestimonal t2 = new JobTestimonal();
		t2.setCompany_tel("1589485");
		t2.setDate("15年9月08日");
		t2.setDuty("公关");
		t2.setIdCard("31498545");
		t2.setJob("研发");
		t2.setSalary(56);
		t2.setUserName("张三");
		t2.setResponser("王总");
		testimonals.add(t2);
		
		return testimonals;
	}
	
	
	
}
