package MyAlgorithms.DynamicProgramming;

/**
 * Created by fang on 5/26/17.
 */
public class KnapsackProblem {

    public int getSlution(int w, int[]weight, int[] value, int n){
        return help(w, weight,value, n,0,0);
    }

    private int help(int limitW, int[]weight, int[]value, int index, int curWeight, int curValue){
        if(index>=weight.length){
            return curValue;
        }
        int val = 0;
        if(curWeight+weight[index]<=limitW){
            val = help(limitW,weight,value,index+1,curWeight+weight[index],curValue+value[index]);
            val = Math.max(help(limitW,weight,value,index+1,curWeight,curValue),val);
        }else{
            val = Math.max(val,help(limitW,weight,value,index+1,curWeight,curValue));
        }
        return val;

    }

    public static void main(String[]args){
        int val[] = new int[]{60, 120,100};
        int wt[] = new int[]{10, 30,20};
        int W = 30;
        KnapsackProblem solution = new KnapsackProblem();
        System.out.println(solution.getSlution(W,wt,val,0));
    }
}
