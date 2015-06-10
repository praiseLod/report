package com.fdauto.report.word.util;

import com.aspose.words.CompositeNode;
import com.aspose.words.Document;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.Node;
import com.aspose.words.NodeImporter;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.Section;

/**
 * AposeWord帮助类
 * 
 * @author PraiseLod
 * @date 2015年6月10日
 * @version 
 */
public class AsposeWordUitl {

	private AsposeWordUitl() {
		super();
	}
	
	/**
	 * Inserts content of the external document after the specified node.
	 *
	 * @param insertAfterNode Node in the destination document after which the content
	 * should be inserted. This node should be a block level node (paragraph or table).
	 * @param srcDoc The document to insert.
	 */
	public static void InsertDocumentWithSectionFormatting(Node insertAfterNode, Document srcDoc) throws Exception
	{
	    // Make sure that the node is either a pargraph or table.
	    if ((insertAfterNode.getNodeType() != NodeType.PARAGRAPH) &
	      (insertAfterNode.getNodeType() != NodeType.TABLE))
	        throw new Exception("The destination node should be either a paragraph or table.");

	    // Document to insert srcDoc into.
	    Document dstDoc = (Document)insertAfterNode.getDocument();

	    // To retain section formatting, split the current section into two at the marker node and then import the content from srcDoc as whole sections.
	    // The section of the node which the insert marker node belongs to

	    Section currentSection = (Section)insertAfterNode.getAncestor(NodeType.SECTION);

	    // Don't clone the content inside the section, we just want the properties of the section retained.
	    Section cloneSection = (Section)currentSection.deepClone(false);

	    // However make sure the clone section has a body, but no empty first paragraph.
	    cloneSection.ensureMinimum();

	    cloneSection.getBody().getFirstParagraph().remove();

	    // Insert the cloned section into the document after the original section.
	    insertAfterNode.getDocument().insertAfter(cloneSection, currentSection);

	    // Append all nodes after the marker node to the new section. This will split the content at the section level at
	    // the marker so the sections from the other document can be inserted directly.
	    Node currentNode = insertAfterNode.getNextSibling();
	    while (currentNode != null)
	    {
	        Node nextNode = currentNode.getNextSibling();
	        cloneSection.getBody().appendChild(currentNode);
	        currentNode = nextNode;
	    }

	    // This object will be translating styles and lists during the import.
	    NodeImporter importer = new NodeImporter(srcDoc, dstDoc, ImportFormatMode.USE_DESTINATION_STYLES);

	    // Loop through all sections in the source document.
	    for (Section srcSection : srcDoc.getSections())
	    {
	        Node newNode = importer.importNode(srcSection, true);
	        // Append each section to the destination document. Start by inserting it after the split section.
	        dstDoc.insertAfter(newNode, currentSection);
	        currentSection = (Section)newNode;
	    }
	}
	
	/**
	 * Inserts content of the external document after the specified node.
	 * Section breaks and section formatting of the inserted document are ignored.
	 *
	 * @param insertAfterNode Node in the destination document after which the content
	 * should be inserted. This node should be a block level node (paragraph or table).
	 * @param srcDoc The document to insert.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void insertDocument(Node insertAfterNode, Document srcDoc) throws Exception
	{
	    // 确保节点是一个段落或是表格
	    if ((insertAfterNode.getNodeType() != NodeType.PARAGRAPH) &
	      (insertAfterNode.getNodeType() != NodeType.TABLE))
	        throw new IllegalArgumentException("The destination node should be either a paragraph or table.");

	    // 将插入以目标段落的父容器中
	    CompositeNode dstStory = insertAfterNode.getParentNode();

	    // 为导入对象添加风格
	    NodeImporter importer = new NodeImporter(srcDoc, insertAfterNode.getDocument(), ImportFormatMode.KEEP_SOURCE_FORMATTING);

	    // 遍历源文档中的所有部分。
	    for (Section srcSection : srcDoc.getSections())
	    {
	        //遍历所有的块级别节点(段落和表)的主体部分。
	        for (Node srcNode : ((Iterable<Node>) srcSection.getBody()))
	        {
	            // 跳过节点如果是最后一个空段部分。
	            if (srcNode.getNodeType() == (NodeType.PARAGRAPH))
	            {
	                Paragraph para = (Paragraph)srcNode;
	                if (para.isEndOfSection() && !para.hasChildNodes())
	                    continue;
	            }

	            // 这将创建一个克隆节点的,插入到目标文档。
	            Node newNode = importer.importNode(srcNode, true);

	            // 在引用的节点后插入新了节点
	            dstStory.insertAfter(newNode, insertAfterNode);
	            insertAfterNode = newNode;
	        }
	    }
	}
	
}
