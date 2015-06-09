package com.fdauto.report.resovler;

import java.util.Map;

public interface ParamResolver {
	Map<String, Object> resolve(Object obj,Class<?> clazz);
}
