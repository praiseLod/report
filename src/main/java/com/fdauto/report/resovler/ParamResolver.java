package com.fdauto.report.resovler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ParamResolver {
	Map<String, Object> resolve(Object obj,Class<?> clazz);
	List<Map<String, Object>> resolve(Collection<?> collection,Class<?> clazz);
}
