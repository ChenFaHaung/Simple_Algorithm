package SCC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TarScc {
	private boolean[] marked; // 記錄走過的
	private int[] lowlink; // 編號
	private Stack<Integer> stack;
	private int time; // pre-order count
	private int V; // 點數
	private List<Integer>[] graph; // 存圖
	private ArrayList<List<Integer>> components; // 存SCC

	public ArrayList<List<Integer>> getComps(List<Integer>[] graph) {
		this.graph = graph;
		V = graph.length;// 點數
		time = 0;
		lowlink = new int[V];
		marked = new boolean[V];
		stack = new Stack<Integer>();
		components = new ArrayList<>();
		for (int i = 1; i < V; i++) {
			if (!marked[i]) {
				DFS(i);// 做DFS
			}
		}
		return components;

	}

	public void DFS(int i) {
		lowlink[i] = time++;
		marked[i] = true;// 作記號
		stack.add(i);
		boolean isComponentRoot = true;
		for (int j : graph[i]) {
			if (!marked[j]) {
				DFS(j);
			}
			if (lowlink[j] < lowlink[i]) {
				lowlink[i] = lowlink[j];
				isComponentRoot = false;
			}
		}
		if (isComponentRoot) {
			List<Integer> component = new ArrayList<Integer>();
			while (true) {
				int x = stack.pop();
				component.add(x);
				lowlink[x] = Integer.MAX_VALUE;
				if (x == i)
					break;
			}
			components.add(component);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int edge;
		int max = -1000;
		int[] start;
		int[] finish;
		String s;
		String Sedge;
		String[] tempS;
		FileReader fr;
		StringBuffer sb1 = new StringBuffer();

		fr = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fr);

		Sedge = br.readLine();
		edge = Integer.parseInt(Sedge);

		start = new int[edge];
		finish = new int[edge];
		tempS = new String[edge * 2];

		while (br.ready()) {
			s = br.readLine();
			sb1.append(s + " ");
		}

		// 切到陣列
		String ss = sb1.toString();
		tempS = ss.split(" ");

		// 存到start, finish 陣列裡
		for (int j = 0; j < (edge * 2); j++) {
			int locate = j % 2;
			if (locate == 0) {
				start[j / 2] = Integer.parseInt(tempS[j]);
			} else if (locate == 1) {
				finish[j / 2] = Integer.parseInt(tempS[j]);
			}
		}

		// 找點的數量
		for(int i = 0; i < edge; i++){
			if(max < start[i]){
				max = start[i];
			}
		}
		// make graph
		List<Integer>[] g = new List[max + 1];
		for (int i = 1; i <= max; i++) {
			g[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < edge; i++) {
			g[start[i]].add(finish[i]);
		}
		TarScc tar = new TarScc();
		ArrayList<List<Integer>> scc = tar.getComps(g);
		
		// 第一個最小數量的component列出所有內容
		int c = 100000;
		int mark = 0 ;
		for(int i = 0; i < scc.size(); i++){
			if(scc.get(i).size() < c){
				c = scc.get(i).size();
				mark = i;
			}
		}
		// 輸出檔案
		FileWriter fw = new FileWriter("output.txt");
		fw.write("SCC個數： " + scc.size() + System.getProperty("line.separator"));
		fw.write("最小個數的集合： " + scc.get(mark));
		fw.flush();
		fw.close();
	}
}
