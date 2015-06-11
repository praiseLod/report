package com.fdauto.report.core.type;

/**
 * 字符编码
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public enum CharSet {
	UTF8("utf-8");
	
	private String value;

	private CharSet(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return this.value;
	}
}
