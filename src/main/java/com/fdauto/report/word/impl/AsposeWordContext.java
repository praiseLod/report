package com.fdauto.report.word.impl;

import java.util.Map;

import com.fdauto.report.word.AbstractWordContext;

public class AsposeWordContext extends AbstractWordContext {

	@Override
	protected Map<String, Object> resoveParam(Object obj,Class<?> clazz) {
		return this.resolver.resolve(obj, clazz);
	}
	
}
