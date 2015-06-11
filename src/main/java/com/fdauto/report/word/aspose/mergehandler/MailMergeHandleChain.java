package com.fdauto.report.word.aspose.mergehandler;

import java.util.ArrayList;
import java.util.List;

import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;

/**
 * 模板变量处理链
 * 
 * @author PraiseLod
 * @date 2015年6月9日
 * @version 
 */
public class MailMergeHandleChain implements IFieldMergingCallback {

	private final List<IFieldMergingCallback> fieldMergingChain;
	
	
	public MailMergeHandleChain() {
		super();
		this.fieldMergingChain = new ArrayList<IFieldMergingCallback>();
	}

	//文本处理
	@Override
	public void fieldMerging(FieldMergingArgs arg0) throws Exception {
		for(IFieldMergingCallback fmc:fieldMergingChain){
			fmc.fieldMerging(arg0);
		}
	}

	//图片处理
	@Override
	public void imageFieldMerging(ImageFieldMergingArgs arg0) throws Exception {
		for(IFieldMergingCallback fmc:fieldMergingChain){
			fmc.imageFieldMerging(arg0);
		}
	}

	public void putHandler(IFieldMergingCallback arg0){
		fieldMergingChain.add(arg0);
	}
	
}
