

/**
 * Created by fang on 5/19/17.
 */
public class LinearTimeSort {
    public void countingSort(int[]A, int[]B, int k){
        int[]C = new int[k+1];
        for(int j=0;j<A.length;j++){
            int index = A[j];
            C[index]++;
        }
        for(int i=1;i<=k;i++){
            C[i] +=C[i-1];
        }

        for(int j=A.length-1;j>=0;j--){
            B[C[A[j]]-1] = A[j];                     //here is the difference between the book index from 0
            C[A[j]] = C[A[j]]-1;                     //update postion information
        }
    }

    public static void main(String[]args){
        int[] A={2,5,3,0,2,3,0,3};
        int[] B= new int[A.length];
        LinearTimeSort test = new LinearTimeSort();
        test.countingSort(A,B,5);
        for(Integer tmp:B){
            System.out.print(tmp+" ");
        }
        

    }
}
