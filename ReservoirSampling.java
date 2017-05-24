package MyAlgorithms;

/**
 * Created by fang on 5/24/17.
 */
import java.util.*;
public class ReservoirSampling {
    public static void main(String[] args){
        int[] s = {0,1,2,3,4,5,6,7,8,9,10,11};
        int k = 4;
        int[] R = new int[k];
        for(int i=0;i<k;i++){
            R[i] = s[i];
        }
        for(int i=k;i<s.length;i++){
            int j = new Random().nextInt(i);
            if(j<k){
                R[j] = s[i];
            }
        }

        for(Integer tmp:R){
            System.out.println(tmp);
        }
    }
}
