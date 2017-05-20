

/**
 * Created by fang on 5/19/17.
 */
import java.util.*;
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
        for(Integer tmp:B){
            System.out.print(tmp+" ");
        }
    }
 /*If the data distribution is uniform, it will has a linear running time*/
    public void bucketSort(double[] A){
        int n = A.length;
        List<Double>[] B = new LinkedList[n];            // new trick;
        for(int i= 0;i<B.length;i++){
            B[i] = new LinkedList<Double>();
        }
        for(int i=0;i<n;i++){
            int index = (int)(n*A[i]);
            B[index].add(A[i]);
        }

        for(int i=0;i<B.length;i++){
            Collections.sort(B[i]);
        }
        double[] res = new double[n];
        int index = 0;
        for(int i=0;i<B.length;i++){
            for(int j=0;j<B[i].size();j++){
                res[index++] = B[i].get(j);
            }
        }
        for(double tmp:res){
            System.out.print(tmp+" ");
        }
    }
/*Sort point in the unit circle accroding the distance to the original point*/
    public void TwoDbucketSrot(double[][]A){
        int n = A.length;
        List<double[]>[] B = new LinkedList[n];
        for(int i=0;i<n;i++){
            B[i] = new LinkedList<double[]>();
        }
        for(int i=0;i<A.length;i++){
            int index =(int)(n*Math.sqrt(A[i][0]*A[i][0]+A[i][1]*A[i][1]));
            B[index].add(A[i]);
        }
        for(int i=0;i<B.length;i++){
            Collections.sort(B[i],(double[] a,double[] b)->
                    Double.compare(a[0]*a[0]+a[1]*a[1],b[0]*b[0]+b[1]*b[1]));
        }

        for(int i=0;i<B.length;i++){
            for(int j=0;j<B[i].size();j++){
                System.out.println(B[i].get(j)[0]+" "+B[i].get(j)[1]);
            }
        }

    }

    public static void main(String[]args){
        int[] A={2,5,3,0,2,3,0,3};
        int[] B= new int[A.length];
        LinearTimeSort test = new LinearTimeSort();
        //test.countingSort(A,B,5);
        double[] nums = {0.78,0.17,0.39,0.26,0.72,0.94,0.21,0.12,0.23,0.68};
        //test.bucketSort(nums);
        //System.out.println(3*0.15);
        double[][] points = {{0.9,0.1},{0.8,0.55},{0.05,0.06},{0.05,0.05},{0.1,0.1},{0.2,0.2},{0.2,0.25},{0.3,0.3}};
        test.TwoDbucketSrot(points);
        

    }
}
