import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DuplicateInArray {
    public static void main(String[] args) {
        Integer[] A = {6, 3, 5, 2, 1, 7, 7};
        /**
         * count each i= 0 n-1 and keep making set or map
         * map[a[i]++;
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);

            } else {
                map.put(A[i], 1);
            }
        }
        map.forEach((k, v) -> {
            if (v > 1) {System.out.println(k + " : " + v);}
        });


    }
}
