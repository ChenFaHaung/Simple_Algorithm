package LongestCommonSubsequence;

public class LCS {
	static char[] rModel;
	static char[] rFake;
	
	public static int lcs(){
		int s = 0;
		int[] A;
		int[] B;
		char[] M = rModel;
		char[] F = rFake;
		int m = rModel.length;
		int n = rFake.length;
		A = new int[m];
		B = new int[m];
		for(int i = 0; i < m ; i++){
			if(M[i] == F[0]){
				A[i] = 1;
				while(i < m){
					A[i] = 1;
					i++;
				}
				break;
			}
			else{
				A[i] = 0;
			}
		}	
		for(int i = 1; i < n; i++){
			for(int j = 0; j < m; j++){
				if(j == 0){
					if(A[j] == 1) {
						B[j] = 1;
					}
					else if(M[j] == F[j]){
						B[j] = 1;
					}
					else
						B[j] = 0;
				}
				else if(M[j] == F[i]){
					B[j] = A[j - 1] + 1;
				}
				else{
						if(A[j] <= B[j - 1]){
						B[j] = B[j - 1];
						}
						else{
							B[j] = A[j];
						}
				}	
			}
			System.arraycopy(B, 0, A, 0, m);
		}
		s = A[m-1];
		return s;	
	}
	
	public static void Mdata(char[] temps){
		rModel = temps;
		
	}
	public static void Fdata(char[] temps2){
		rFake = temps2;
	}

}
