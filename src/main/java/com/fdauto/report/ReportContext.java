package com.fdauto.report;

import java.util.List;

/**
 * 为模板提交参数内容
 * 
 * @author praiseLod
 * @date 2015年6月6日
 * @version 
 */
public interface ReportContext {
	
	
	/**
	 * 为模板的参数提交数据
	 * 
	 * @param name 	参数名
	 * @param value	参数值
	 * @return 当前对象，便于级联操作
	 */
	ReportContext put(String name,Object value);
	
	
	List<String> getNames();
	
	List<Object> getValues();
	
	
}
