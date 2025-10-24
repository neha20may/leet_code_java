import java.util.Arrays;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int m= strs.length;
        int n = getMin(strs);
        char [][]mat= new char [m][n];
        for(int i=0; i< strs.length; i++){
            mat[i]=strs[i].toCharArray();

        }
        char[]result= new char[n];
        int j=0;
        for(;j<n;j++){
            char c= columnScan(mat, m, j);
            if(c!=' '){
                result[j]=c;
            }else{
                break;
            }
        }
        String res= new String(result, 0, j);
        return res;

    }

    private int getMin(String[] s) {
        int min= Integer.MAX_VALUE;
        for(int i=0; i< s.length; i++)
            if(min> s[i].length()){
                min=s[i].length();
            }
        return min;
    }

    char columnScan(char [][]mat, int m, int j) {
        char c= ' ';
        if (j < mat[0].length){
            c= mat[0][j];
        }else{
            return ' ';
        }
        for (int i = 1; i < m; i++) {
            if (j < mat[i].length) {
                char c1 = mat[i][j];
                if (c == c1) {
                    continue;
                } else {
                    return ' ';
                }
            }else{
                return ' ';
            }

        }
        return c;
    }

    int getMax(String [] s){
        int max= 0;
        for(int i=0; i< s.length; i++)
            if(max< s[i].length()){
                max= s[i].length();
            }
        return max;
    }
}
public class longestPrefix {
    public static void main(String[] args) {
        Solution s= new Solution();
        String [] strs= {"flow", "flo", "flown"};
        String result= s.longestCommonPrefix(strs);
        System.out.println(result);

    }
}
