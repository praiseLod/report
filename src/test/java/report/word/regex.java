package report.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.texen.util.FileUtil;


public class regex {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println(new File("template/收入证明.doc").exists());
	}
}
