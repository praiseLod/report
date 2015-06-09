package com.fdauto.report.word.custom;

import java.util.List;
import java.util.Map;

import com.aspose.words.IMailMergeDataSource;

/**
 * 自定义实现 aspose word 支持{@code Map<String,Object>} 为数据源 
 * 
 * @author praiseLod
 * @date 2015年6月8日
 * @version 
 */
public class MapMailMergeDataSource implements IMailMergeDataSource{

	
	/**
	 * 循环参数名，即模板中 《TableStart:xx》 所指的 <i>xx</i>
	 */
	private String rangeParamName;
	
	/**
	 * 循环参数值
	 */
	private List<Map<String, Object>> rangeParamValue;
	//当前循环 rangeParamValue 的下标值
	private int rangeParamIndex;
	
	public MapMailMergeDataSource(String rangeParamName,
			List<Map<String, Object>> rangeParamValue) {
		super();
		this.rangeParamName = rangeParamName;
		this.rangeParamValue = rangeParamValue;
		this.rangeParamIndex = -1;
	}
	
	public int rangeParamSize(){
		if(rangeParamValue==null)
			return 0;
		return rangeParamValue.size();
	}
	
	@Override
	public IMailMergeDataSource getChildDataSource(String arg0)
			throws Exception {
		return null;
	}

	@Override
	public String getTableName() throws Exception {
		return this.rangeParamName;
	}

	@Override
	public boolean getValue(String arg0, Object[] arg1) throws Exception {
		boolean result =false;
		
		if( (rangeParamIndex!=-1) && (rangeParamIndex < rangeParamSize()) ){
			if(arg1!=null&&arg1.length>0){
				arg1[0] = rangeParamValue.get(rangeParamIndex).get(arg0);
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public boolean moveNext() throws Exception {
		if(rangeParamIndex==rangeParamSize()-1){
			return false;
		}else{
			rangeParamIndex++;
			return true;
		}
		
	}

}
