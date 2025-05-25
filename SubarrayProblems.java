import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Integer.max;

public class SubarrayProblems {
    public static void main(String[] args) {
        Integer [] a={-2,-3,4,-1, -2,1,5,-1};
//        System.out.println(kadane(a));
        a= new Integer[]{1, 2, 3, 5, 2};
        int target=8;
//        System.out.println(findSubarraySum(a, target));
        a=new Integer[]{2,4,-2,1,-3,5,-3};
        target=5;
//        System.out.println(findSubarraySum2(a, target));

//find the longest substring with unique characters.
        String b="whatwhywhere";
        System.out.println(findSubarrayCount(b.toCharArray()));
    }
    //Given a String, find the longest substring with unique characters.

    private static Pair findSubarraySum(Integer[] a, int target) {
        // 2pointers = postotve numbers ;
        //prefix sum - positive and negative numbers
        int i=0;
        int j= 0;
        int window_sum=0;
        while(i<=j){
            if (window_sum >target){
                window_sum= window_sum-a[i];
                i++;
            }else if (window_sum<target){
                window_sum= window_sum+a[j];
                j++;
            }else{
                return new Pair(i, j-1);
            }
            System.out.println("i="+i+"j="+j+" sum="+window_sum);

        }
        return null;
    }
    private static int findSubarrayCount(char [] a) {
        //"whatq    `re"
        //w =1
        //h= 2
        //a =3
        //t=4 max= 4;
        //w= ? move forward from i side; whatver was starting from i has been exhaustedin terms of uniqueness i++; count remains same

        // h i++; count remains same
        //y= 5 max =5
        //
        int i=0;
        int j= 0;
        int max=0;
        HashMap<Character, Integer> set= new HashMap<>();
        while(i<=j){
            System.out.println("i="+i+" ; j="+j+" a[j]="+a[j]);
           if(set.containsKey(a[j])){
               System.out.println("map contains key="+a[j]);
                i=increaseItillchar(a, a[j], i, set);
           }
           else{
              set.put(a[j], j);
               if(max<=j-i+1){
                   System.out.println("max="+(j-i+1)+"i="+i+"j="+j);
                   max= j-i+1;
               }
               j++;
           }
        }
        return max;
    }

    private static int increaseItillchar(char[] a, char c, int i, HashMap<Character, Integer> set) {
        int prevI=set.get(c);
        i= prevI+1;
        return i;
    }

    private static Pair findSubarraySum2(Integer[] a, int target) {
        //prefix sum
        //positive and negative numbers that sum to 0
        //map
        // prefix sum
        //1, 2, -2, 5= 1, 3, 1, 6
        for(int i=0;i<a.length; i++){
            a[i]=a[i]-target;
        }
        System.out.println(Arrays.toString(a));
        Integer [] prefixSum= new Integer[a.length];
        prefixSum[0]= a[0];
        HashMap<Integer, Integer> map= new HashMap<>();
        for (int i=1; i<a.length; i++){
            prefixSum[i]= prefixSum[i-1]+a[i];
            if(map.containsKey(prefixSum[i])){
                return new Pair(map.get(prefixSum[i])+1, i);
            }
            map.put(prefixSum[i], i);
        }
        return null;
    }

    private static int kadane(Integer[] a) {
        //assume :
        // S[k]= subarray sum till k you know; that is delta you know till k point
        //subarray at k+1:
            //S[k+1]= S[k] + a[k] if a[]>0
            //else S[k+1] is simply a[k]
        // S[0] = a[0]
        int max= 0;
        int n= a.length;
        Integer [] s= new Integer[n];
        s[0]=a[0];
        for(int i=1; i<a.length-1; i++){
            System.out.println("i="+i);
//            if (a[i]>0){
//                s[i]= s[i-1]+a[i];
//            }else{
//                s[i]=a[i];
//            }
            s[i]= max(s[i-1]+a[i], a[i]);
            if(max<s[i]){
                max= s[i];
            }
            System.out.println(max);
            System.out.println(Arrays.toString(s));
        }
        return max;
    }
}
