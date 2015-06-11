package com.fdauto.report.factory;

/**
 * 报表抽象工厂
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public interface IReportFactory {
	/**
	 *	根据类取得各类型的工厂
	 * @param clazz
	 * @return T
	 */
	<T> T getFactory(Class<T> clazz);
}
