package Compression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class decom {
	private static HashMap<String, Character> find;
	private static FileWriter fileWriter;

	public static void read(String dat, String out) throws IOException {
		FileReader fr;
		int chInt;
		String headerch;
		String headerco;
		String binary = "";
		String[] hch = new String[256];
		char[] hchar = new char[256];// 放header字母
		String[] hstring = new String[256];// 放header binary string
		try {
			fr = new FileReader(dat);
			BufferedReader br = new BufferedReader(fr);
			headerch = br.readLine();
			headerco = br.readLine();
			hch = headerch.split(" ");
			hstring = headerco.split(" ");
			for (int i = 0; i < hch.length; i++) {// header 轉char
				chInt = Integer.parseInt(hch[i]);
				hchar[i] = (char) chInt;
			}
			String temp = null;
			String temp1 = null;
			while (br.ready()) {
				// 最後一個有可能不是7bits，要放條件，砍掉最後一字元的第一個1
				int tempInput = br.read();
				if (!br.ready()) {
					temp = Integer.toString(tempInput, 2);
					temp = temp.substring(1);
					binary += temp;
					break;
				}
				temp = Integer.toString(tempInput, 2);
				temp1 = String.format("%1$7s", temp).replace(' ', '0');// 補滿七位數
				binary += temp1;
			}
			find = new HashMap<>();
			for (int i = 0; i < hch.length; i++) {
				find.put(hstring[i], hchar[i]);// 加到hash map 裡
			}

			// 比較turn -> find
			fileWriter = new FileWriter(out, false);
			String tempS = "";
			int f = 1;
			int s = 0;
			while (true) {
				if (binary.length() < f)
					break;

				tempS = binary.substring(s, f);
				if (find.containsKey(tempS)) {
					// 換行只有一個
					fileWriter.write(find.get(tempS));
					s = f;
					f = f + 1;

				} else {
					f++;
				}
			}
			fileWriter.flush();
			fileWriter.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		read("input_1.dat", "output_1.txt");
		find = new HashMap<>();
		read("input_2.dat", "output_2.txt");
		find = new HashMap<>();
		read("input_3.dat", "output_3.txt");
		find = new HashMap<>();
		read("input_4.dat", "output_4.txt");
		find = new HashMap<>();
		read("input_5.dat", "output_5.txt");
	}

}
