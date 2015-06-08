package com.fdauto.report.word;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fdauto.report.ReportContext;

/**
 * 基于aspose word 的 wordContxt模板内容类
 * 
 * @author praiseLod
 * @date 2015年6月8日
 * @version 
 */
public interface WordContext extends ReportContext {
	/**
	 * 填充基本内容参数。
	 * <p>提供该对象的类型。解析器将根据该对象类型最终将生成{@code Map<String,Object>}类型
	 * @param obj	指定模型对象
	 * @param clazz 指定模型对象的类型
	 * @return AbstractWordContext
	 */
	WordContext put(Object obj, Class<?> clazz);
	
	/**
	 * 填充循环内容参数。
	 * @param name          循环参数名
	 * @param rangeParam	参数值
	 * @return AbstractWordContext
	 */
	WordContext putRangeParam(String name,List<Map<String, Object>> rangeParam);
	
	/**
	 * 填充循环内容参数。如果模板中有多个同名的循环参数名，则只能匹配第一个表
	 * <p>{@code rangeParam}为一个对象模型的集合。并将根据提交的{@code clazz}解析该对象模型最终将生成{@code List<Map<String,Object>>}类型
	 * @param rangeParam	参数值（对象模型的集合）
	 * @param clazz			对象模型的类型
	 * @param name          循环参数名
	 * @return AbstractWordContext
	 */
	WordContext putRangeParam(String name,Collection<?> rangeParam,Class<?> clazz);;
	 
	/**
	 *	获取循环参数
	 * @return Map<String,List<Map<String,Object>>>
	 */
	Map<String, List<Map<String, Object>>> getRangeParam();
}
