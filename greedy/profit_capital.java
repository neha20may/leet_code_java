package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class profit_capital {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] products = new int[n][2];
        for (int i = 0; i < n; i++) {
            products[i][0] = profits[i];
            products[i][1] = capital[i];
        }
        Comparator<int[]> c = (a, b) -> {
            return a[1] - b[1];
        };
        Arrays.sort(products, c);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int totalProfit=w;
        while(k>0){
            for(int i=0; i<n;i++){
                if(products[i][1] <=totalProfit){
                    pq.add(products[i][0]);
                }
            }
            if(pq.isEmpty()){
                return totalProfit;
            }
            totalProfit+=pq.remove();
            k--;
        }
        return totalProfit;
      }

    }
