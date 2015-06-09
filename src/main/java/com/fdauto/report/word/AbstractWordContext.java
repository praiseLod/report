package com.fdauto.report.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdauto.report.ReportContext;
import com.fdauto.report.resovler.ParamResolver;
import com.fdauto.report.resovler.impl.DefaultParamResolver;

/**
 * <p>基于aspose word实现的模板内容设置类。</p>
 * <p>其内容参数的数据结构只允许有两种，
 * <li>{@code Map<String,Object> Object} 只能为一个8大基本数据类型
 * <li>{@code List<Map<String,Object>>} 为模板中《TableStart:xx》 内容的循环输出提供数据
 * </p>
 * @author praiseLod
 * @date 2015年6月6日
 * @version 
 */
public abstract class  AbstractWordContext implements WordContext {
	/**
	 * 模板变量
	 */
	protected Map<String, Object> param;
	/**
	 * 表格变量
	 */
	protected Map<String, List<Map<String, Object>>> tableParam;
	
	/**
	 * 变量解析器
	 */
	protected ParamResolver resolver;

	
	public AbstractWordContext() {
		super();
		this.resolver = new DefaultParamResolver();
		this.param = new HashMap<String, Object>();
		this.tableParam = new HashMap<String, List<Map<String,Object>>>();
	}
	
	
	/**
	 * 参数解析
	 * 将对象模型解析成{@code Map<String,Object>} ,模板只接受java基本类型值
	 * @return Map<String,Object>
	 */
	protected abstract  Map<String, Object> resoveParam(Object obj,Class<?> clazz);
	
	@Override
	public ReportContext put(String name, Object value) {
		this.param.put(name, value);
		return this;
	}

	@Override
	public List<String> getNames() {
		return new ArrayList<String>(param.keySet());
	}

	@Override
	public List<Object> getValues() {
		return new ArrayList<Object>(param.values());
	}

	@Override
	public WordContext putTableParam(String name,List<Map<String, Object>> tableParam) {
		this.tableParam.put(name, tableParam);
		return this;
	}

	@Override
	public WordContext putTableParam(String name,Collection<?> tableParam, Class<?> clazz) {
		if(tableParam == null) return this;
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for(Object o : tableParam){
			resultList.add(resoveParam(o, clazz));
		}
		this.tableParam.put(name,resultList);
		return this;
	}

	@Override
	public WordContext put(Object obj, Class<?> clazz) {
		this.param.putAll(this.resolver.resolve(obj, clazz));
		return this;
	}
	public ParamResolver getResolver() {
		return resolver;
	}

	public void setResolver(ParamResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getTableParam() {
		return this.tableParam;
	}
	
}
