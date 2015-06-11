package com.fdauto.report.factory;

import com.fdauto.report.word.aspose.WordContext;
import com.fdauto.report.word.aspose.WordEngine;
import com.fdauto.report.word.aspose.WordTemplate;

/**
 * word模板工厂
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public interface WordFactory {
	WordEngine getEngine();
	WordContext getContext();
	WordTemplate getTemplate();
}
