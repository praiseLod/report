package com.fdauto.report.resovler.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import report.domain.JobTestimonal;
import cn.jimmyshi.beanquery.BeanQuery;

import com.fdauto.report.resovler.ParamResolver;

public class BaseParamResolver implements ParamResolver {

	@Override
	public Map<String, Object> resolve(Object obj,Class<?> clazz) {
		BeanQuery query = BeanQuery.selectBean(clazz);
		List<Map<String, Object>> l = query.select(query.allOf(clazz)).from(obj).execute();
		return l.get(0);
	}

	@Override
	public List<Map<String, Object>> resolve(Collection<?> collection,
			Class<?> clazz) {
		if(collection == null) return null;
		
		List<Map<String, Object>> rangeParam = new ArrayList<Map<String,Object>>();
		for(Object o : collection){
			rangeParam.add(resolve(o, clazz));
		}
		return rangeParam;
	}
	
}
