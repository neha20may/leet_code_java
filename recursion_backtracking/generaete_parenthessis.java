package recursion_backtracking;

import java.util.*;

/**
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 */
public class generaete_parenthessis {
    static class Solution {
        public List<String> generateParenthesis(int n) {
            /**
             * rules when open left and close left are same - start with (
             * when open left =0; use all left close
             */
            int openLeft = n;
            int closeLeft = n;
            List<String> ans = new ArrayList<>();
            List<Character> buffer = new ArrayList<>();
            call(openLeft, closeLeft, ans, buffer);
            for (String s : ans) {
                System.out.println(s);
            }
            return ans;

        }

        private void call(int openLeft, int closeLeft, List<String> ans, List<Character> current) {
            System.out.println("start open left = "+openLeft +" close left = "+closeLeft+" current "+current);
            if (openLeft == closeLeft && openLeft == 0) {
                ans.add(getString(current));
                System.out.println("end open left = "+openLeft +" close left = "+closeLeft+ "----- ans return ---");
                return;
            }
            if (openLeft == closeLeft && openLeft != 0) {
                current.add('(');
                call(openLeft - 1, closeLeft, ans, current);
                current.remove(Character.valueOf('('));
                System.out.println("end open left = "+openLeft +" close left = "+closeLeft);
                return;
            }
            if (openLeft == 0 && closeLeft > 0) {
                System.out.println("add all ) choice ; current passed "+current);
                int removeCount = closeLeft;
                while (removeCount != 0) {
                    current.add(')');
                    removeCount -= 1;
                }
                call(openLeft, 0, ans, current);
                removeCount = closeLeft;
//                while (removeCount != 0) {
//                    current.remove(Character.valueOf(')'));
//                    removeCount -= 1;
//                }
                //remove from last
                current.subList(current.size() - removeCount, current.size()).clear();
                System.out.println("end open left = "+openLeft +" close left = "+closeLeft+" current "+current);
                return;
            }

            // now two choices
            System.out.println("start open left = "+openLeft +" close left = "+closeLeft +" add ( choice " + "current ="+current);
            current.add('(');
            call(openLeft - 1, closeLeft, ans, current);
//            current.remove(Character.valueOf('('));
            current.subList(current.size() - 1, current.size()).clear();

            System.out.println("start open left = "+openLeft +" close left = "+closeLeft +" add ) choice "+ "current ="+current);
            current.add(')');
            call(openLeft, closeLeft - 1, ans, current);
//            current.remove(Character.valueOf(')'));
            current.subList(current.size() - 1, current.size()).clear();
            System.out.println("end open left = "+openLeft +" close left = "+closeLeft+" current "+current);
        }

        String getString(List<Character> current) {
            StringBuilder sb = new StringBuilder();
            for (Character c : current) {
                sb.append(c);
            }
            return sb.toString();
        }

    }

    static void main() {
        Solution s = new Solution();
        s.generateParenthesis(3);

    }
}
