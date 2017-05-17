/**
 * Created by fang on 5/17/17.
 */
public class MaximumSubArray {
    private int[] findMaxCrossingSubarray(int[]A, int low, int mid, int high){
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int[] res = new int[3];
        for(int i=mid;i>=low;i--){
            sum +=A[i];
            if(sum>leftSum){
                leftSum = sum;
                res[0]=i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for(int j=mid+1;j<=high;j++){
            sum +=A[j];
            if(sum>rightSum){
                rightSum = sum;
                res[1] = j;
            }
        }
        res[2] = leftSum+rightSum;
        return res;
    }

    public int[] findMaximumSubArray(int[]A, int low, int high){
        if(high == low){
            int[] res = new int[3];
            res[0] = low;
            res[1] = high;
            res[2] = A[low];
            return res;
        }else{
            int mid = (low+high)/2;
            int[] left = findMaximumSubArray(A,low,mid);
            int[] right = findMaximumSubArray(A,mid+1,high);
            int[] middle = findMaxCrossingSubarray(A,low,mid,high);
            if(left[2]>=right[2] & left[2]>=middle[2]){
                return left;
            }else if(right[2]>=left[2] & right[2]>=middle[2]){
                return right;
            }else{
                return middle;
            }
        }
    }

    public static void main(String[] args){
        int[] nums={13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        MaximumSubArray solution = new MaximumSubArray();
        int[] res = solution.findMaximumSubArray(nums,0,nums.length-1);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}
