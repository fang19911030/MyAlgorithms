package MyAlgorithms.DynamicProgramming;

/**
 * Created by fang on 5/26/17.
 */
public class LongestPalindrome {
    public int getLongest(String s){
        int[][] table = new int[s.length()][s.length()];
        for(int i=0;i<table.length;i++){
            table[i][i]=1;
        }
//        for(int i=0;i<table.length-1;i++){
//            if(s.charAt(i) == s.charAt(i+1)){
//                table[i][i+1] =2;
//            }
//        }

        for(int k=2;k<=s.length();k++){
            for(int i=0;i<s.length()-k+1;i++){
                int j = i+k-1;
                if(s.charAt(i) == s.charAt(j)){
                    table[i][j]= table[i+1][j-1]+2;

                }else{
                    table[i][j] = Math.max(table[i+1][j],table[i][j-1]);
                }
            }
        }

        return table[0][table.length-1];


    }

    public static void main(String[] args){
        LongestPalindrome solution = new LongestPalindrome();
        System.out.println(solution.getLongest("bcharacterb"));
    }
}
