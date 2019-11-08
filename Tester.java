import tools.GetCurrentJpegFile;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		List<File> list = new ArrayList<>();
		GetCurrentJpegFile f = new GetCurrentJpegFile();
		list = f.execute();
		for (File file : list) {
			System.out.println(file);
		}
	}
}
