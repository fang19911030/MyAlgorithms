
import java.util.Arrays;

/**
 * Created by fang on 5/19/17.
 */
public class heapRelevent {
    private int heapSize;
    private int[] A;

    public heapRelevent(int[]nums){
        heapSize = nums.length;
        A = Arrays.copyOf(nums,nums.length);
        for(int i=A.length/2;i>=0;i--){
            maxHeapify(A, i);
        }
    }

    private void maxHeapify(int[] A, int i){
        int left = 2*i+1;
        int right = 2*i+2;
        int largest = i;
        while(left<heapSize){
            if (A[left]>A[largest]){
                largest = left;
            }
            if(right<heapSize && A[right]>A[largest]){                          //the complier difference  why condition 1 doesn't pass dondition 2 will test for &{
                largest = right;
            }
            if(i!=largest){
                swap(A,i,largest);
            }else{
                break;
            }
            left = 2*largest+1;
            right = 2*largest+2;
            i = largest;
        }
    }

    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void sort(){
        for(int i=A.length-1;i>=1;i--){
            swap(A,0,i);
            heapSize--;
            maxHeapify(A,0);
        }
        for(Integer tmp:A){
            System.out.println(tmp);
        }
    }

    public int getMax(){
        if(heapSize<1){
            return Integer.MIN_VALUE;
        }
        int max = A[0];
        A[0] = A[heapSize-1];
        heapSize--;
        maxHeapify(A,0);
        return max;
    }



    public static void main(String[] args){
        int[] nums={9,12,32,45,1,4,66,23};
        heapRelevent solution = new heapRelevent(nums);
        solution.sort();
//        System.out.println(solution.getMax());
//        System.out.println(solution.getMax());

    }

}


