package Compression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

abstract class HufTree implements Comparable<HufTree> {// comparable可排序物件
	public final int frequency;

	public HufTree(int freq) {
		frequency = freq;
	}

	public int compareTo(HufTree tree) {
		return frequency - tree.frequency;
	}
}

class HuffmanLeaf extends HufTree {
	public final char value;

	public HuffmanLeaf(int freq, char val) {
		super(freq);
		value = val;
	}
}

class HufNode extends HufTree {
	public final HufTree left, right; // subtrees
	FileWriter fileWriter;

	public HufNode(HufTree l, HufTree r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}

public class HuffmanCode {
	private static String[] Hufs = new String[256 * 256];
	private static FileWriter fileWriter;
	private static String hufstring;

	public static HufTree buildTree(int[] charFreqs) {
		PriorityQueue<HufTree> trees = new PriorityQueue<HufTree>();
		for (int i = 0; i < charFreqs.length; i++) {
			if (charFreqs[i] > 0) {// leaf
				trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));// 加入物件
			}

		}
		assert trees.size() > 0;
		while (trees.size() > 1) {
			HufTree a = trees.poll();// 取得物件回傳null
			HufTree b = trees.poll();
			trees.offer(new HufNode(a, b));
		}
		return trees.poll();
	}

	public static void Codes(HufTree tree, StringBuffer huf) {// stringbuffer
		// string的暫存空間相當於加一string到後面
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {// instanceof 看b是否為a的子類別
			HuffmanLeaf leaf = (HuffmanLeaf) tree;
			hufstring = huf.toString();
			Hufs[(int) leaf.value] = hufstring;
		} else if (tree instanceof HufNode) {
			HufNode node = (HufNode) tree;// 轉成node type
			huf.append('0');
			Codes(node.left, huf);
			huf.deleteCharAt(huf.length() - 1);// 清掉旗面儲存的huffman code
			huf.append('1');
			Codes(node.right, huf);
			huf.deleteCharAt(huf.length() - 1);
		}
	}

	public static void readfile(String s, String f) throws IOException {
		int[] charFreq = new int[256 * 256];
		String bb;
		char[] p;
		char b2d;
		StringBuffer q = new StringBuffer();
		StringBuffer s1 = new StringBuffer();

		FileReader fr;
		fr = new FileReader(s);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			int tempInput = br.read();
			s1.append((char) tempInput);
		}
		String tempstr = s1.toString();
		p = tempstr.toCharArray();
		for (char c : p) {
			charFreq[c]++;
		}

		HufTree tree = buildTree(charFreq);

		Codes(tree, new StringBuffer());

		for (int i = 0; i < p.length; i++) {
			q.append(Hufs[p[i]]);
		}
		int i = 0;
		String a = null;
		fileWriter = new FileWriter(f, false);
		for (int b = 0; b < Hufs.length; b++) {
			if (Hufs[b] != null) {
				fileWriter.write(b + " ");
			}
		}
		fileWriter.write("\n");
		for (int b = 0; b < Hufs.length; b++) {
			if (Hufs[b] != null) {
				fileWriter.write(Hufs[b] + " ");
			}
		}
		fileWriter.write("\n");
		int c = q.length() / 7 + 1;
		while (c > 0) {
			a = q.substring(i);
			if (a.length() < 7) {
				bb = a.substring(0, a.length());
				bb = "1" + bb;
				b2d = (char) Integer.parseInt(bb, 2);
			} else {
				bb = a.substring(0, 7);
				b2d = (char) Integer.parseInt(bb, 2);
			}
			fileWriter.write(b2d);
			i = i + 7;
			c--;
		}
		fileWriter.flush();
		fileWriter.close();
		fr.close();
	}

	public static void main(String[] args) throws IOException {
		readfile("input_1.txt", "input_1.dat");
		Hufs = new String[256 * 256];
		hufstring = new String();
		readfile("input_2.txt", "input_2.dat");
		Hufs = new String[256 * 256];
		hufstring = new String();
		readfile("input_3.txt", "input_3.dat");
		Hufs = new String[256 * 256];
		hufstring = new String();
		readfile("input_4.txt", "input_4.dat");
		Hufs = new String[256 * 256];
		hufstring = new String();
		readfile("input_5.txt", "input_5.dat");
	}
}
