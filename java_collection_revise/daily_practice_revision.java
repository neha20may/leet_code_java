package java_collection_revise;

import java.util.Arrays;

public class daily_practice_revision {
    static void main() {
        int[] a={1,2,3};
        int mx= Arrays.stream(a).max().getAsInt();
        System.out.println(mx);
        int sum = Arrays.stream(a).sum();
    }
}
