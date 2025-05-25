import java.util.Arrays;

import static java.util.Collections.swap;

public class replaceTwoTimes {
    public static void main(String[] args) {
        Integer[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer aSize = A.length;
        Integer[] B = new Integer[12];
        /**
         * replace each even number by twice of its
         * 1,2,3,4,5
         * 1, 2, 2, 3, 4, 4, 5
         * 1,2,3,4,5
         * another array
         * time = O(n), spaceO(n)
         * 5, 4, 4, 3, 2, 2, 1= reverse it O(n)
         * same array ?
         *1,2,3,4,5,
         *
         */
        for (int i = 0; i < aSize; i++) {
            B[i] = A[i];
        }

        Integer i = 8;//end element.
        Integer j = B.length - 1;//the size where the array can go
        while (j >= 0 && i >= 0) {
            if (B[i] % 2 == 0) {
                B[j] = B[i];
                j--;
                B[j] = B[i];
                j--;
                i--;
            } else {
                B[j] = B[i];
                j--;
                i--;
            }
        }
        Arrays.stream(B).forEach(System.out::println);

        //reverse the array
        i = 0;
        j = B.length - 1;
        while (i < j) {
            swap(B, i, j);
            i++;
            j--;
        }
        System.out.println("reversal");
        Arrays.asList(B).forEach(System.out::println);

    }
    static void swap(Integer [] B, int i, int j) {
        int temp= B[i];
        B[i] = B[j];
        B[j] = temp;
    }
}
