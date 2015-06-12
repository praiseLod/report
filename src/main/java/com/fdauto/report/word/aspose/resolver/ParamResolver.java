package com.fdauto.report.word.aspose.resolver;

import java.util.Map;


/**
 * 模板参数转换类，模板所接受的数据格式为{@code Map<String,Object>},与{@code List<Map<String,Object>>}<br>
 * 两种形式。
 * 
 * @author PraiseLod
 * @date 2015年6月12日
 * @version 
 */
public interface ParamResolver {
	Map<String, Object> resolve(Object obj,Class<?> clazz);
}
