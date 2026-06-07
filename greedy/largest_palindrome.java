package greedy;

import java.util.*;
import java.util.stream.Collectors;

public class largest_palindrome {

    public String largestPalindromic2(String num) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < num.length(); i++) {
            Character c = num.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        //sort map
        LinkedHashMap<Character, Integer> orderedCount = new LinkedHashMap<>();
        orderedCount = count.entrySet().stream().sorted((a, b) -> {
                    return b.getKey() - a.getKey();
                })
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue(), (x, y) -> x, LinkedHashMap::new));
        //now all nnumbers are in 7->2 6->1 4-> 3

        LinkedHashMap<Character, Integer> filteredCount = new LinkedHashMap<>();
        int ansLength = 0;
        Boolean isOneTaken = false;
        Character midC = null;
        LinkedHashMap<Character, Integer> filteredCount2 = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> e : orderedCount.entrySet()) {
            char c = e.getKey();
            int freq = e.getValue();
            if (freq % 2 == 0) {
                //even can take up complete
                filteredCount.put(c, freq);
                ansLength = ansLength + freq;
            } else {
                int extra = freq % 2; //this will always be 1 for odd numbers!
                freq = freq - extra;//rest fo the the number
                filteredCount.put(c, freq);
                ansLength = ansLength + freq;
                filteredCount2.put(c, extra);
            }

        }
        for(Map.Entry<Character, Integer>e: filteredCount2.entrySet()){
            char c= e.getKey();
            int fr= e.getValue();
            if(fr==1){
                midC= c;
                isOneTaken=true;
                ansLength= ansLength+1;
                break;
            }
        }
        System.out.println("final ans len= " + ansLength);
        char[] str = new char[ansLength];
        int fp = 0;
        int lp = ansLength - 1;
        if (ansLength % 2 != 0) {
            str[ansLength / 2] = midC.charValue();
        }
        for (Map.Entry<Character, Integer> e : filteredCount.entrySet()) {
            char c = e.getKey();
            int fr = e.getValue();
            if (fr == 1) {
                continue;
            }

            while (fr > 0) {
                str[fp] = c;
                str[lp] = c;
                fr = fr - 2;
                fp++;
                lp--;
            }
        }
        System.out.println("filled array=" + str);
        //strip 0s front and centre
        int i = 0;
        char c = '0';
        while (i < str.length && str[i] == c) {
            i++;
        }
        int j = str.length - 1;
        while (j > 0 && str[j] == c) {
            j--;
        }
        if (i > j) {
            return "0";
        }
        return new String(str, i, j - i + 1);

    }
//this was the hardest question in the greedy so far
}

class Main {
    public static void main(String[] args) {
        largest_palindrome obj = new largest_palindrome();
        String ans = obj.largestPalindromic2("473805954612393765848885");
        System.out.println("ans= " + ans);
    }
}
