package MyAlgorithms;

import java.util.Random;

/**
 * Created by fang on 5/24/17.
 */
public class RandomShuffle {
    public static void main(String[] args){
        int[] nums={1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        for(int i=nums.length-1;i>=1;i--){
            int j = new Random().nextInt(i+1);
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        for(Integer tmp:nums){
            System.out.print(tmp+" ");
        }
        Random rand = new Random();
        for(int i=0;i<100;i++){
            System.out.println(rand.nextInt(100));
        }
    }

}
