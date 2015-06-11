package com.fdauto.report.word.aspose.resolver;

import java.util.Map;

public interface ParamResolver {
	Map<String, Object> resolve(Object obj,Class<?> clazz);
}
