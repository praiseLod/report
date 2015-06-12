package com.fdauto.report;


/**
 * 模板内容统一接口
 * 
 * @author PraiseLod
 * @date 2015年6月11日
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
	Object put(String name,Object value);
}
