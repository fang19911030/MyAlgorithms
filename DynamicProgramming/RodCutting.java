package MyAlgorithms.DynamicProgramming;

import java.util.HashMap;

/**
 * Created by fang on 5/25/17.
 */
public class RodCutting {
    private HashMap<Integer, Integer>prices;

    public RodCutting() {
        this.prices = new HashMap<>(); // key is length value is price
        int[] length = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        for (int i = 0; i < prices.length; i++) {
            this.prices.put(length[i], prices[i]);
        }
    }

    public int memoizedCutRod(int n){
        int[] r = new int[n+1];
        for(int i=0;i<r.length;i++){
            r[i] = Integer.MIN_VALUE;
        }

        return help(n,r);
    }

    private int help(int n, int[]r){
        if(r[n]>=0){
            return r[n];
        }
        int q;
        if(n==0){
            q=0;
        }else{
            q = Integer.MIN_VALUE;
            for(int i=1;i<=n;i++){
                q = Math.max(q,prices.get(i)+help(n-i,r));
            }
        }
        r[n]=q;
        return q;
    }

    public static void main(String[] args){
        int length = 5;
        RodCutting solution = new RodCutting();
        System.out.println(solution.memoizedCutRod(length));
    }

}
