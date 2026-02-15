package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class profit_capital {
    class Solution {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            int n= profits.length;
            int[][] pc= new int[n][2];
            for(int i=0; i< n; i++){
                pc[i][0]=profits[i];
                pc[i][1]= capital[i];
            }
            Arrays.sort(pc, (a, b)->{return a[0]-a[1];});
            int totalProfits=w;
            Comparator c= (a, b)->{
                a-b;
            };
            PriorityQueue<int [][]> q= new PriorityQueue(c);
            q.addAll(pc);


        }
    }

}
