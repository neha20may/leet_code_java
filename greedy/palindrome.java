package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class palindrome {
    public int longestPalindrome(String s) {
        char[] arr= s.toCharArray();
        HashMap<Character, Integer> freq= new HashMap();
        for(char c: arr){
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        // Comparator<Map.Entry<Character, Integer>> c= (a, b)->{
        //     return a.getValue()- b.getValue();
        // };
        // PriorityQueue<Map.Entry<Character, Integer>> pq= new PriorityQueue<>(freq.entrySet(), c);
        List<Map.Entry<Character, Integer>> list= new ArrayList(freq.entrySet());
        int palLength= 0;
        Boolean oddTaken  = false;
        for(Map.Entry<Character, Integer> entry: list){
            Character ch= entry.getKey();
            Integer count=  entry.getValue();
            if(count ==1 && !oddTaken){
                palLength=palLength+1;
                oddTaken= true;
            }else if(count>1){
                int palusefulcount= count - count%2;
                entry.setValue(count- palusefulcount);
                palLength = palLength+ palusefulcount;

            }
        }
        return palLength;
    }
}
