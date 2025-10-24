import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.reverse;

public class LC_array {

    public static String addBinary(String a, String b) {
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        char[] c = sum(a1, b1);
        System.out.println(c);
        return Arrays.toString(c);
    }

    private static char[] sum(char[] a1, char[] b1) {
        int n1 = a1.length - 1;
        int n2 = b1.length - 1;
        int i = n1;
        int j = n2;
        char[] result = (n1 > n2) ? new char[n1 + 2] : new char[n2 + 2];
        int n = (n1 > n2) ? n1 + 1 : n2 + 1;
        int N = n;
        Character carryOver = '0';
        while (i != -1 && j != -1) {
            char a = a1[i];
            char b = b1[j];
            Pair<Character, Character> sum = add_binary_char(a, b, carryOver);
            result[n--] = sum.getKey();
            carryOver = sum.getValue();
            i--;
            j--;
        }
        if (i == -1 && j != -1) {
            while (j != -1) {
                if (carryOver != null) {
                    Pair<Character, Character> sum = add_binary_char('0', b1[j], carryOver);
                    result[n--] = sum.getKey();
                    carryOver = sum.getValue();
                    j--;
                } else {
                    Pair<Character, Character> sum = add_binary_char('0', b1[j], '0');
                    result[n--] = sum.getKey();
                    carryOver = sum.getValue();
                    j--;
                }
            }
        }
        if (j == -1 && i != -1) {
            while (i != -1) {
                if (carryOver != null) {
                    Pair<Character, Character> sum = add_binary_char(a1[i], '0', carryOver);
                    result[n--] = sum.getKey();
                    carryOver = sum.getValue();
                    i--;
                } else {
                    Pair<Character, Character> sum = add_binary_char(a1[i], '0', '0');
                    result[n--] = sum.getKey();
                    carryOver = sum.getValue();
                    i--;
                }
            }
        }
        if (carryOver == '1') {
            result[n--] = carryOver;
        }
//        String r1= Arrays.toString(result);
//        return r1;
        return  result;
    }

    private static char[] truncate(char[] c, int n, int N) {
        int realN= (n==0)?n: N-n+1;
        char []result= new char[N];
        int i=0;
        while(n<=N){
            result[i++]= c[n++];
        }
        return result;
    }


    private static Pair<Character, Character> add_binary_char(char a, char b, Character carryOver) {
        if (a == '0' && b == '0') {
            return (carryOver == '1') ? new Pair<Character, Character>('1', '0') : new Pair<Character, Character>('0', '0');
        } else if (a == '1' && b == '0') {
            return (carryOver == '1') ? new Pair<Character, Character>('0', '1') : new Pair<Character, Character>('1', '0');
        } else if (a == '0' && b == '1') {
            return (carryOver == '1') ? new Pair<Character, Character>('0', '1') : new Pair<Character, Character>('1', '0');
        } else {
            return (carryOver == '1') ? new Pair<Character, Character>('1', '1') : new Pair<Character, Character>('0', '1');
        }
    }

    public static void main(String[] args) {
        String a = "101";
        String b = "1";
        System.out.println(addBinary(a, b));
    }

}
