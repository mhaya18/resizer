package tools;

import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class GetCurrentJpegFile {

	public List<File> execute() {
		// create ipeg File
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				if (name.indexOf(".jpeg") != -1 || name.indexOf(".JPG") != -1 || name.indexOf(".jpg") != -1) {
					return true;
				} else {
					return false;
				}
			}
		};

		File dir = new File(System.getProperty("user.dir"));
		File[] files = dir.listFiles(filter);
		List<File> list = new ArrayList<>();

		for (File file : files) {
			list.add(file);
		}
		return list;
	}
}
