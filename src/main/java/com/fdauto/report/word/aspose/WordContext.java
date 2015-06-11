package com.fdauto.report.word.aspose;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.aspose.words.IFieldMergingCallback;
import com.fdauto.report.ReportContext;
import com.fdauto.report.word.aspose.resolver.ParamResolver;

/**
 * 基于aspose word 的 wordContxt模板内容类
 * 
 * @author praiseLod
 * @date 2015年6月8日
 * @version 
 */
public interface WordContext extends ReportContext  {
	
	List<String> getNames();
	
	List<Object> getValues();
	
	/**
	 * 为模板变量设值
	 * <p>提供的值为模型对象。解析器将根据该对象类型最终将生成{@code Map<String,Object>}类型
	 * @param obj	指定模型对象
	 * @param clazz 指定模型对象的类型
	 * @return AbstractWordContext
	 */
	WordContext put(Object obj, Class<?> clazz);
	
	/**
	 * 为表格变量设值
	 * @param name          表格变量名
	 * @param rangeParam	变量值
	 * @return AbstractWordContext
	 */
	WordContext putTableParam(String name,List<Map<String, Object>> rangeParam);
	
	/**
	 * 为表格变量设值。如果模板中有多个同名的表格变量，则只能匹配第一张表
	 * <p>{@code rangeParam}为一个对象模型的集合。并将根据提交的{@code clazz}解析该对象模型最终将生成{@code List<Map<String,Object>>}类型
	 * @param rangeParam	参数值（对象模型的集合）
	 * @param clazz			对象模型的类型
	 * @param name          表格变量名
	 * @return AbstractWordContext
	 */
	WordContext putTableParam(String name,Collection<?> rangeParam,Class<?> clazz);;
	 
	/**
	 *	获取表格变量
	 * @return Map<String,List<Map<String,Object>>>
	 */
	Map<String, List<Map<String, Object>>> getTableParam();
	
	/**
	 * 取得合并时模板变量域处理器
	 * @return IFieldMergingCallback
	 */
	IFieldMergingCallback getMailMergeHandler();
	
	
	/**
	 * 增设合并时模板变量域处理器
	 * @param arg0 void
	 */
	void putMailMergeHandler(IFieldMergingCallback arg0);
	
	/**
	 * 设置参数解析器
	 * @param resolver void
	 */
	void setParamResolver(ParamResolver resolver);
}
