package recursion_backtracking;

import java.util.*;

public class n_k_generation {
    class OldSolution {
        /**
         * this solution didnt work ; canodates are required to generate int he rec call itself
         *
         * @param n
         * @param k
         * @return
         */
        public int[] numsSameConsecDiff(int n, int k) {
            Set<Pair> pairs = getPair(k);
            System.out.println(pairs);
            List<List<Integer>> ans = new ArrayList();
            for (Pair p : pairs) {
                if (k == 0 && p.x == 0) {
                    continue;
                }
                if (p.x == 0) {
                    call(ans, n, new ArrayList(), p, false);
                    continue;
                }
                if (p.x == p.y) {
                    call(ans, n, new ArrayList(), p, true);
                    continue;
                }
                call(ans, n, new ArrayList(), p, true);
                call(ans, n, new ArrayList(), p, false);

            }


            List<Integer> noAns = new ArrayList();
            for (List<Integer> no : ans) {
                noAns.add(getNumber(no));
            }
            int[] finalAns = new int[noAns.size()];
            for (int i = 0; i < noAns.size(); i++) {
                finalAns[i] = noAns.get(i);
            }
            return finalAns;

        }

        int getNumber(List<Integer> x) {
            int num = 0;
            for (int y : x) {
                num = num * 10 + y;
            }
            return num;
        }

        void call(List<List<Integer>> ans, int n, List<Integer> current, Pair p, Boolean isX) {
            //base?
            //
            // System.out.println(" call:: pair "+p +" current ="+current +" isX= "+isX);
            if (current.size() == n) {
                ans.add(current);
                return;
            }

            if (isX) {
                current.add(p.x);
                call(ans, n, current, p, !isX);
            } else {
                current.add(p.y);
                call(ans, n, current, p, !isX);
            }

        }

        Set<Pair> getPair(int k) {
            Set<Pair> allPairs = new HashSet();

//         Pair p = new Pair(k, 0);
//         allPairs.add(p);

            for (int i = 0; i <= 9; i++) {
                int x = i;
                int y = x + k;
                if (y < 10) {
                    allPairs.add(new Pair(x, y));
                }


            }
            return allPairs;
        }

        class Pair {
            public int x, y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return new String("x= " + this.x + " y= " + this.y);
            }
        }
    }

    static class Solution {
        public int[] numsSameConsecDiff(int n, int k) {
            Stack<Integer> buffer = new Stack<>();
            List<List<Integer>> ans = new ArrayList<>();
            for(int i=1; i<=9; i++){
                recurse(n, k, buffer, i, ans);
            }

            Set<List<Integer>> reduced= new HashSet<>(ans);
            int[] result= new int[reduced.size()];
            int i=0;
            for(List<Integer> nmbr: reduced){
                result[i++]= getNumber(nmbr);
            }
            System.out.println(reduced);
            return result;
        }
        int getNumber(List<Integer> x) {
            int num = 0;
            for (int y : x) {
                num = num * 10 + y;
            }
            return num;
        }

        private void recurse(int n, int k, Stack<Integer> buffer, int i, List<List<Integer>> ans) {
            //base
            if(buffer.size() == n){
                ans.add(new ArrayList<>(buffer));
                return;
            }

            //get candidates for this i
            buffer.push(i);
            if(i + k < 10){
                recurse(n, k, buffer, i+k, ans);
            }
            if(i-k >= 0){
                recurse(n, k, buffer, i-k, ans);
            }
            buffer.pop();
        }
    }

    static void main() {
        Solution s = new Solution();
        s.numsSameConsecDiff(2, 1);
    }
}
