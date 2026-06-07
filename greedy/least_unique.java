package greedy;

import java.util.*;

public class least_unique {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            //least b=number of unique inetegers= most repeatative elemtsn remains = leeast repeattiave goes down!
            Map<Integer,Integer> freq= new HashMap();
            for(int x: arr){
                freq.put(x, freq.getOrDefault(x, 0)+1);
            }
            int n= freq.size();
            int [][] pairs= new int[n][];
            int i=0;
            for(Map.Entry<Integer,Integer> entry : freq.entrySet()){
                int key= entry.getKey();
                int value= entry.getValue();
                pairs[i][0]= key;
                pairs[i][1]= value;
                i++;
            }
            Comparator<int[]> c= (int[] a, int []b)->{return a[1]-b[1];};
            PriorityQueue<int[]> pq= new PriorityQueue<>(c);

            for(int [] r: pairs){
                pq.add(r);
            }
            while(k>0){
                int[] row= pq.remove();
                int el= row[0];
                int val= row[1];
                val= val-1;
                if(val>0){
                    pq.add(new int[]{el, val});
                }
                k--;
            }
            List<int[]> ans= new ArrayList<>(pq);
            return ans.size();
        }
}
