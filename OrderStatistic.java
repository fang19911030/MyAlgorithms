package MyAlgorithms;

import java.util.*;

/**
 * Created by fang on 5/20/17.
 */
public class OrderStatistic {
    public int randomizedSelect(int[]A, int p, int r, int i){
        if(p==r){
            return A[p];
        }
        int q = randomizedPartition(A,p,r);
        int k = q-p+1;
        if(k==i){
            return A[q];
        }else if(k>i){
            return randomizedSelect(A,p,q-1,i);
        }else{
            return randomizedSelect(A,q+1,r,i-k);
        }
    }
    private void swap(int[]A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    private int randomizedPartition(int[]A, int p , int r){
        Random rand = new Random();
        int i = rand.nextInt(r-p+1)+p;
        swap(A,i,r);
        return partition(A, p, r);
    }

    private int partition(int[]A, int p, int r){
        int x = A[r];
        int l = p-1;
        for(int i=p;i<=r-1;i++){                                      //key point
            if(A[i]<=x){
                l++;
                swap(A,l,i);
            }
        }
        swap(A,l+1,r);
        return l+1;
    }

//    public int select(int[]A,int p, int r, int i){
//        int n = r-p+1;
//        List<Integer>[] B = new LinkedList[(int)Math.floorDiv(n,5)];
//        for(int i2=0;i2<B.length;i2++){
//            B[i2] = new LinkedList<Integer>();
//        }
//        int index = 0;
//        for(int i2=0;i2<B.length-1;i2++){
//            for(int j=0;j<5;j++){
//                B[i2].add(A[index++]);
//            }
//        }
//        while(index<A.length){
//            B[B.length-1].add(A[index++]);
//        }
//        int[] median = new int[B.length];
//        for(int i2=0;i2<B.length;i2++){
//            Collections.sort(B[i2]);
//            int length = B[i2].size();
//            median[i2] = B[i2].get(length/2);
//        }
//        Arrays.sort(median);
//        int medianOfMedian = median.length/2;
//        int q = partition(A,p,r,medianOfMedian);
//        int k = q-p+1;
//        if(k==i){
//            return A[i];
//        }else if(k>i){
//            return select(A,p,q-1,i);
//        }else{
//            return select(A,q+1,r,i-k);
//        }
//
//
//    }
//
//    private int partition(int[] A, int p, int r, int pivot){
//        int l = p-1;
//        int x = A[pivot];
//        for(int i=p;i<r;i++){
//            if(A[i]<=x){
//                l++;
//                swap(A,i,i);
//            }
//        }

//        swap(A,l+1,pivot);
//        return l+1;
//
//    }

    public int getYcoord(int[][]A){
        int[] y = new int[A.length];
        int medianSeqNum = y.length/2;
        for(int i=0;i<y.length;i++){
            y[i] = A[i][1];
        }
        System.out.println(medianSeqNum);

        return randomizedSelect(y,0,A.length-1,medianSeqNum);
    }



    public static void main(String[] args){
        int[]A = {3,2,1,4,5,9,11,12,14,8,22};
        OrderStatistic solution = new OrderStatistic();
        System.out.println(solution.randomizedSelect(A,0,A.length-1,4));
//        int[][] nums={{0,7},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,8}};
//        System.out.print(solution.getYcoord(nums));
        System.out.println(2.5%1);
    }


}
