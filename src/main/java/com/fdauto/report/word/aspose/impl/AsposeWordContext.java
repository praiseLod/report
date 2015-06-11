package com.fdauto.report.word.aspose.impl;

import java.util.Map;

import com.fdauto.report.word.aspose.AbstractWordContext;

public class AsposeWordContext extends AbstractWordContext {

	@Override
	protected Map<String, Object> resoveParam(Object obj,Class<?> clazz) {
		return this.resolver.resolve(obj, clazz);
	}
	
}
