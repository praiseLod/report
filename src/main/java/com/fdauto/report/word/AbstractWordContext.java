package com.fdauto.report.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdauto.report.ReportContext;
import com.fdauto.report.resovler.ParamResolver;
import com.fdauto.report.resovler.impl.BaseParamResolver;

/**
 * <p>基于aspose word实现的模板内容设置类。</p>
 * <p>其内容参数的数据结构只允许有两种，
 * <li>{@code Map<String,Object> Object} 只能为一个8大基本数据类型，{@code byte[]} 可为模板提交图片
 * <li>{@code List<Map<String,Object>>} 为模板中《TableStart:xx》 内容的循环输出提供数据
 * </p>
 * @author praiseLod
 * @date 2015年6月6日
 * @version 
 */
public abstract class  AbstractWordContext implements WordContext {
	/**
	 * 基础输出参数
	 */
	protected Map<String, Object> param;
	/**
	 * 循环输出参数
	 */
	protected Map<String, List<Map<String, Object>>> rangeParam;
	
	/**
	 * 对象解析器
	 */
	protected ParamResolver resolver;

	
	public AbstractWordContext() {
		super();
		this.resolver = new BaseParamResolver();
		this.param = new HashMap<String, Object>();
		this.rangeParam = new HashMap<String, List<Map<String,Object>>>();
	}
	
	
	/**
	 * 参数解析
	 * 将对象模型解析成{@code Map<String,Object>} ,模板只接受java基本类型值
	 * @return Map<String,Object>
	 */
	protected abstract  Map<String, Object> resoveParam();
	
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
	public WordContext putRangeParam(String name,List<Map<String, Object>> rangeParam) {
		this.rangeParam.put(name, rangeParam);
		return this;
	}

	@Override
	public WordContext putRangeParam(String name,Collection<?> rangeParam, Class<?> clazz) {
		this.rangeParam.put(name,this.resolver.resolve(rangeParam, clazz));
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
	public Map<String, List<Map<String, Object>>> getRangeParam() {
		return this.rangeParam;
	}
	
	
	
}
