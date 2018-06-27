package Strassen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends Canvas{
	JFrame frame  = null;
	JLabel MSA = new JLabel("MatrixSizeA");
	JLabel MSB = new JLabel("MatrixSizeB");
	JLabel Speed = new JLabel("Speed");
	JTextField size = new JTextField(20);
	JTextField size1 = new JTextField(20);
	JTextField sp = new JTextField(20);
	JPanel panel1 = new JPanel();
	
	ArrayList<int []> A = new ArrayList<int []>();
	ArrayList<int []> B = new ArrayList<int []>();
	int[][] a;
	int[][] b;

	Strassen str = new Strassen();
	
	public GUI() throws IOException {
		frame = new JFrame("Strassen");
		Container contentPane = frame.getContentPane();
		setSize(640,320);
		
		FileReader fr = new FileReader("t1.txt");
		Scanner input = new Scanner(fr);
		while (input.hasNext()) {	
			String tempInput= input.nextLine();
			String[] tempStr = tempInput.split("\\s+");
		    int[] temp = null; 
		    temp = new int[tempStr.length];
		    for(int i = 0 ; i <tempStr.length ; i++){
				temp[i] = Integer.valueOf(tempStr[i]);
		    }
		    A.add(temp);
		}
		FileReader fr1 = new FileReader("t2.txt");
		Scanner inputs = new Scanner(fr1);
		while (inputs.hasNext()) {	
			String tempInput1 = inputs.nextLine();
			String[] tempStr1 = tempInput1.split("\\s+");
		    int[] temp1 = null; 
		    temp1 = new int[tempStr1.length];
		    for(int i = 0 ; i <tempStr1.length ; i++){
				temp1[i] = Integer.valueOf(tempStr1[i]);
		    }
		    B.add(temp1);
		}
		
		a = new int [A.size()][A.get(0).length];
		b = new int [B.size()][B.get(0).length];
		for(int i = 0; i < A.size() ; i++){
			for(int j = 0; j < A.get(0).length; j++){
				a[i][j] = A.get(i)[j];
			}
		}
		for(int i = 0; i < B.size(); i++){
			for(int j = 0; j < B.get(0).length; j++){
				b[i][j] = B.get(i)[j];
			}
		}
		
		double StartTime = 0;
		double EndTime = 0;

		//stray(a, b);
		int [] n = {A.size(), B.get(0).length, B.size(), A.get(0).length};
		int te = -1;
		for(int i = 0; i < 4; i++){
			if(n[i] > te){
				te = n[i];
			}
		}
		size.setText(A.size() + "X" + A.get(0).length);
		size1.setText(B.size() + "X" + B.get(0).length);
		System.out.println("最大邊長： " + te);

		int[][] s = new int [te][te];
		StartTime = System.currentTimeMillis();
		s = Strassen.stray(a, b, te);
		EndTime = System.currentTimeMillis();
		System.out.println("答案： ");
		for (int i = 0; i < A.size(); i++) {
			  for (int j = 0; j < B.get(0).length; j++) {
			    System.out.print(s[i][j] + " "); 
			    }
			  System.out.println();
			  }
		System.out.println(((EndTime - StartTime)/1000) + "秒");
		sp.setText(((EndTime - StartTime)/1000) + "秒");
		size.setEditable(false);
		size1.setEditable(false);
		sp.setEditable(false);
		panel1.add(MSA);
		panel1.add(size);
		panel1.add(MSB);
		panel1.add(size1);
		panel1.add(Speed);
		panel1.add(sp);
		frame.setSize(400, 200);
		contentPane.add(panel1, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
