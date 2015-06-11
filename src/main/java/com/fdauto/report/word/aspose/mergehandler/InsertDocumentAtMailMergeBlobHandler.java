package com.fdauto.report.word.aspose.mergehandler;

import java.util.regex.Pattern;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;
import com.fdauto.report.word.aspose.util.AsposeWordUitl;

/**
 * 实现以邮件合并的方式合并文档
 * 模板中mergeField名必须遵循 <i>NestDoc_*</i> 格式定义。如 : NestDoc_doc1 
 * 
 * @author PraiseLod
 * @date 2015年6月10日
 * @version 
 */
public class InsertDocumentAtMailMergeBlobHandler implements IFieldMergingCallback {

	 /**
     * This handler makes special processing for the "Document_1" field.
     * The field value contains the path to load the document.
     * We load the document and insert it into the current merge field.
     */
    public void fieldMerging(FieldMergingArgs e) throws Exception
    {
        if (Pattern.matches("NestDoc_.*", e.getDocumentFieldName()))
        {
            // Use document builder to navigate to the merge field with the specified name.
            DocumentBuilder builder = new DocumentBuilder(e.getDocument());
            builder.moveToMergeField(e.getDocumentFieldName());

            // The name of the document to load and insert is stored in the field value.
            Document subDoc = new Document((String)e.getFieldValue());

            // Insert the document.
            AsposeWordUitl.insertDocument(builder.getCurrentParagraph(), subDoc);

            // The paragraph that contained the merge field might be empty now and you probably want to delete it.
            if (!builder.getCurrentParagraph().hasChildNodes())
                builder.getCurrentParagraph().remove();

            // Indicate to the mail merge engine that we have inserted what we wanted.
            e.setText(null);
        }
    }

    public void imageFieldMerging(ImageFieldMergingArgs args) throws Exception
    {
        // Do nothing.
    }
}
