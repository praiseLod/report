package com.fdauto.report.factory.impl;

import com.fdauto.report.factory.ITextFactory;
import com.fdauto.report.text.TextEngine;
import com.fdauto.report.text.impl.VelocityTextEngine;

public class TextFactory implements ITextFactory {

	@Override
	public TextEngine getEngine() {
		return new VelocityTextEngine();
	}

}
