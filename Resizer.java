import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tools.GetCurrentJpegFile;
import tools.ResizeImage;

public class Resizer {

	public static void main(String[] args) {
		// Resize the image file in the current directory.

		System.out.println("Reduce the file size.");

		List<File> list = new ArrayList<>();
		// List<File> list_aft = new ArrayList<>();
		
		GetCurrentJpegFile f = new GetCurrentJpegFile();
		list = f.execute();

		// 縮小対象のファイル数
		int file_cnt = list.size();

		ResizeImage r = new ResizeImage();

		// リサイズ
		String s1 = "=";
		int i = 0;
		int x = 0;
		double d = 0;
		String p = ""; // パーセント
		int p_cnt = 0; // パーセント記号表示個数
		for (File file : list) {
			i++;
			try {
//				System.out.println("\n" + file + ":" + String.format("%.4f", (file.length() / 1024.0 / 1024.0)) + "MB");
				File file_aft = r.execute(file);
				//				System.out.println(" → " + file_aft + ":" + String.format("%.4f", (file_aft.length() / 1024.0 / 1024.0)) + "MB");
				d = (double)i/file_cnt;
				p = String.format("%6.2f", d * 100);
				p_cnt = (int)(d * 100) / 10;
				p_cnt *= 2; // 100%で20個表示
				StringBuffer buf = new StringBuffer();
				buf.append(p);
				buf.append("% |");
				for (int j = 0; j < 20; j++) {
					if (j < p_cnt) {
						buf.append("=");
					} else {
						buf.append(" ");
					}
				}
				buf.append("|");
				if (i == file_cnt) {
					buf.append("   ");
					buf.append(Integer.toString(i));
					buf.append("\n");
				} else {
					x++;
					switch(x) {
					case 1:
						buf.append(" / ");
						break;
					case 2:
						buf.append(" | ");
						break;
					case 3:
						buf.append(" \\ ");
						break;
					case 4:
						buf.append(" - ");
						break;
					case 5:
						buf.append(" | ");
						break;
					case 6:
						buf.append(" \\ ");
						break;
					case 7:
						buf.append(" - ");
						x = 0;
						break;
					}
					buf.append(Integer.toString(i));
					buf.append("\r");
				}
				System.out.print(buf.toString());

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
				System.out.println("\nThe original files has been deleted.");
			}
		}

		System.out.println("Done.");
	}
}
