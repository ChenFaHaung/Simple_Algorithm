package LongestCommonSubsequence;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends Canvas implements ActionListener{
	JFrame frame  = null;
	JTextField m = new JTextField(9);
	JTextField f = new JTextField(9);
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton b1 = new JButton("Model");
	JButton b2 = new JButton("Fake");
	JButton br = new JButton("Run");
	JFileChooser fileChooser = null;
	File file = null;
	File file1 = null;
	LCS lcss = new LCS();
	StringBuffer s = new StringBuffer();
	StringBuffer s1 = new StringBuffer();
	
	public GUI(){
		frame = new JFrame("LCS");
		Container contentPane = frame.getContentPane();
		setSize(320, 320);
		m.setEditable(false);
		f.setEditable(false);
		b1.addActionListener(this);
		b2.addActionListener(this);
		br.addActionListener(this);
		
		panel1.add(b1);
		panel1.add(m);
		panel1.add(b2);
		panel1.add(f);
		panel2.add(br);
		
		fileChooser = new JFileChooser();
		contentPane.add(panel2, BorderLayout.SOUTH);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int result;
		if(e.getActionCommand().equals("Run") && file != null){
			int a = LCS.lcs();
			out(a);
			System.out.println(a);
		}
		if(e.getActionCommand().equals("Model")){
			char[] temps;
 			fileChooser.setDialogTitle("input1");
			result = fileChooser.showOpenDialog(frame);
			if(result == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				m.setText(file.getName());
			try{
				Scanner input = new Scanner(file);
				while(input.hasNext()){
					String tempInput = input.nextLine();
					s.append(tempInput);
				}
				String tempstr = s.toString();
				tempstr = tempstr.replaceAll("\\s+", "");
				tempstr = tempstr.toUpperCase();//轉大寫比較省空間
				temps = tempstr.toCharArray();
				LCS.Mdata(temps);
			}catch (FileNotFoundException e1) {
				e1.printStackTrace();
		}
			}
		}
		
		if(e.getActionCommand().equals("Fake")){
			char[] temps2;
			fileChooser.setDialogTitle("input2");
			result = fileChooser.showOpenDialog(frame);
			if(result == JFileChooser.APPROVE_OPTION){
				file1 = fileChooser.getSelectedFile();
				f.setText(file1.getName());
			FileReader fr;
			String filename;
			filename = file1.toString();
			
			try {
				fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				while(br.ready()){
					String tempInput = br.readLine();
					s1.append(tempInput);
				}
				String tempstr = s1.toString();
				tempstr = tempstr.replaceAll("\\s+", "");
				tempstr = tempstr.toUpperCase();
				temps2 = tempstr.toCharArray();
				LCS.Fdata(temps2);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
		}
		}
public void out(int a){
	FileWriter fw = null;
	BufferedWriter bw =null; 
	try {
		String fileName = "output.txt";
		fw = new FileWriter(new File("output.txt"));
		bw = new BufferedWriter(fw);
		bw.write("LCS的長度是 " + a + ".\n");
		bw.close();
		fw.flush();
		fw.close();
	} catch (Exception e) {
		// TODO: handle exception
	}
}
}
