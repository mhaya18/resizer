import tools.GetCurrentJpegFile;
import tools.ResizeImage;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Resizer {

	public static void main(String[] args) {
		// Resize the image file in the current directory.

		System.out.println("--------------------");
		System.out.println("Start");
		System.out.println("--------------------");

		List<File> list = new ArrayList<>();
		// List<File> list_aft = new ArrayList<>();

		GetCurrentJpegFile f = new GetCurrentJpegFile();
		list = f.execute();
		ResizeImage r = new ResizeImage();

		// リサイズ
		for (File file : list) {
			try {
				System.out.println("\n" + file + ":" + String.format("%.4f", (file.length() / 1024.0 / 1024.0)) + "MB");
				File file_aft = r.execute(file);
				System.out.println(" → " + file_aft + ":" + String.format("%.4f", (file_aft.length() / 1024.0 / 1024.0)) + "MB");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 作成リスト

		// 削除
		if (args.length == 1){
			if (args[0].equals("d")) {
				for (File file : list) {
					file.delete();
				}
				System.out.println("\n**The original files has been deleted.**");
			}
		}

		System.out.println("--------------------");
		System.out.println("End");
		System.out.println("--------------------");
	}
}
