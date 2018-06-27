package Strassen;

import java.io.IOException;

public class Strassen {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GUI start = new GUI();
		
	}
	public static int[][] stray(int [][] a, int [][] b, int len){
		int n = len;
		int lg = (int) Math.ceil(Math.log(n) / Math.log(2));
		int m = (int) Math.pow(2, lg);
		int [][] A = new int [m][m];
		int [][] B = new int [m][m];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++){
				A[i][j] = a[i][j];
			}
		}
		for(int i = 0; i < b.length; i++){
			for(int j = 0; j < b[0].length; j++){
				B[i][j] = b[i][j];
			}
		}

		int[][] c = strayC(A, B);
		int[][] C = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				C[i][j] = c[i][j];
			}
		}
		return C;
	}
	public static int[][] strayC(int[][] A, int[][] B){
		int n = A.length/2;
		int[][] m1 = new int[n][n];
		int[][] m2 = new int[n][n];
		int[][] m3 = new int[n][n];
		int[][] m4 = new int[n][n];
		int[][] m5 = new int[n][n];
		int[][] m6 = new int[n][n];
		int[][] m7 = new int[n][n];
		int[][] c = new int[n][n];
		int[][] d = new int[n][n];
		int[][] ans = new int[2*n][2*n];
		int[][] a11 = new int[n][n];
		int[][] a12 = new int[n][n];
		int[][] a21 = new int[n][n];
		int[][] a22 = new int[n][n];
		int[][] b11 = new int[n][n];
		int[][] b12 = new int[n][n];
		int[][] b21 = new int[n][n];
		int[][] b22 = new int[n][n];
		if(n <= 64){
			return dot(A, B);
		}else{
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				a11[i][j] = A[i][j];
				a12[i][j] = A[i][j + n];
				a21[i][j] = A[i + n][j];
				a22[i][j] = A[i + n][j + n];
				
				b11[i][j] = B[i][j];
				b12[i][j] = B[i][j + n];
				b21[i][j] = B[i + n][j];
				b22[i][j] = B[i + n][j + n];
				
			}
		}
		
		c = add(a11, a22);
		d = add(b11, b22);
		m1 = strayC(c, d);
		
		c = add(a21, a22);
		m2 = strayC(c, b11);
		
		d = sub(b12, b22);
		m3 = strayC(a11, d);
		
		d = sub(b21, b11);
		m4 = strayC(a22, d);
		
		c = add(a11, a12);
		m5 = strayC(c, b12);
		
		c = sub(a21, a11);
		d = add(b11, b12);
		m6 = strayC(c, d);
		
		c = sub(a12, a22);
		d = add(b21, b22);
		m7 = strayC(c, d);
		/*	m[1] = (A[1][1] + A[2][2]) * (B[1][1] + B[2][2]);
		m[2] = (A[2][1] +A[2][2]) * B[1][1];
		m[3] = A[1][1] * (B[1][2] - B[2][2]);
		m[4] = A[2][2] * (B[2][1] - B[1][1]);
		m[5] = (A[1][1] + A[1][2]) *B[1][2];
		m[6] = (A[2][1] - A[1][1]) * (B[1][2] + B[1][2]);
		m[7] = (A[1][2] - A[2][2]) * (B[2][1] + B[2][2]);*/
		c = add(m1, m4);
		d = sub(m5, m7);
		int[][] c11 = sub(c, d);
		
		int[][] c12 = add(m3, m5);
		
		int[][] c21 = add(m2, m4);
		
		c = sub(m1, m2);
		d = add(m3, m6);
		int[][] c22 = add(c, d);
		/*c[1][1] = m[1] + m[4] - m[5] + m[7];
		c[1][2] = m[3] + m[5];
		c[2][1] = m[2] + m[4];
		c[2][2] = m[1] - m[2] + m[3] + m[6];		
		*/
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				ans[i][j] = c11[i][j];
				ans[i][j + n] = c12[i][j];
				ans[i + n][j] = c21[i][j];
				ans[i + n][j + n] = c22[i][j];
			}
		}
					
		return ans;
		}
	}
	public static int[][] add(int[][]a, int[][] b){
		int n = a.length;
		int c[][] = new int[n][n];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		return c;
	}
	public static int[][] sub(int[][]a, int[][] b){
		int n = a.length;
		int c[][] = new int[n][n];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				c[i][j] = a[i][j] - b[i][j];
			}
		}
		return c;
	}
	public static int[][] dot(int[][]a, int[][] b){
		int n = a.length;
		int c[][] = new int[n][n];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				for(int k = 0; k < a.length; k++){
					c[i][k] += a[i][j] * b[j][k];
				}
			}
		}
		return c;
	}
	
}
