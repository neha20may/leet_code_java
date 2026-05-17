package recursion_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class maze_destination {
    static class Solution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

            List<List<Integer>> ans = new ArrayList();
            int n = graph.length;
            System.out.println("nodes ingraph=" + n);
            List<Integer> currentPath = new ArrayList();
            currentPath.add(0);
            call(graph, ans, 0, n, currentPath);
            return ans;

        }

        void call(int[][] g, List<List<Integer>> ans, int i, int n, List<Integer> current) {
            //base
            System.out.println("i node =" + i);
            if (i == n - 1) {
                //reached last -- this is ans;
                System.out.println("final node reached");
                System.out.println("current " + current);
                ans.add(new ArrayList(current));
                return;
            } else if (i >= n) {
                return;
            }
            int[] ngh = g[i];
            System.out.println("i node neighbors=" + Arrays.toString(ngh));
            for (int ng : ngh) {
                current.add(ng);
                call(g, ans, ng, n, current);
                current.remove(ng);
            }


        }
    }

    static void main() {
        Solution s= new Solution();
        int [][] g= {{1,2},{3},{3},{}};
        s.allPathsSourceTarget(g);
        int []a= new int[]{1,2,3};
        int []b= {1,2,3};

        Arrays.asList(new int[]{1,2,3});

    }
}
