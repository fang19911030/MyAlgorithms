package MyAlgorithms;

import java.util.LinkedList;

/**
 * Created by fang on 5/20/17.
 */
public class OpenAddressingHash {
    private int SIZE = 1000;

    private LinkedList<String>[] hashtable = (LinkedList<String>[]) new LinkedList[SIZE];

    public void add(String value){
        int hash = Math.abs(hash(value))%1000;                         //need improvement
        if(hashtable[hash]==null){
            hashtable[hash]=new LinkedList<>();
        }
        LinkedList<String> bucket = hashtable[hash];
        bucket.add(value);
    }

    private int hash(String value){
        int hash = 7;
        for(int i=0;i<value.length();i++){
            hash = hash*31+(value.charAt(i)-'a');
        }
        return hash;
    }


    public static void main(String[] args){
        OpenAddressingHash table = new OpenAddressingHash();
        String[] strs={"aboijfd","aoifjfoijb"};
        for(String tmp:strs){
            table.add(tmp);
        }
    }
}
