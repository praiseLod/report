package report.word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**
 * 
 * ����ASPOSE�Ƚϳ��ڴ棬������һ����ļ��ͻ������������������ú�java��������-Xms512m -Xmx512m(�ο�ֵ)
 * �������ʣ�����CSDN���ؽ�������,������ϵQQ569925980
 * 
 * @author Spark
 *
 */
public class Test {

    /**
     * ��ȡlicense
     * 
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        // ��֤License
        if (!getLicense()) {
            return;
        }

        try {
            File file = new File("d://");
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document("收入证明.docx");
            
            doc.save(os, SaveFormat.DOCX);//ȫ��֧��DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF �໥ת��
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
