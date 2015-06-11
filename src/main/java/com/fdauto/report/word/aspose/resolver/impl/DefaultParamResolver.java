package com.fdauto.report.word.aspose.resolver.impl;

import java.util.List;
import java.util.Map;

import cn.jimmyshi.beanquery.BeanQuery;

import com.fdauto.report.word.aspose.resolver.ParamResolver;

public class DefaultParamResolver implements ParamResolver {

	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public Map<String, Object> resolve(Object obj,Class<?> clazz) {
		BeanQuery query = BeanQuery.selectBean(clazz);
		List<Map<String, Object>> l = query.select(query.allOf(clazz)).from(obj).execute();
		return l.get(0);
	}
}
