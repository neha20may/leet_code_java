import javax.crypto.spec.PSource;
import java.util.Arrays;

import static java.util.Collections.swap;

public class partition {
    public static void main(String[] args) {
//         [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]
        //pass 0 to begining or ending..
        Integer [] A={4,2,0,1,0,3,0, 5,6,7,0};
//        Integer [] B= shiftZeroLast(A);
//        Arrays.asList(B).forEach(x-> System.out.print(x+" , "));
//        System.out.println(Arrays.toString(B));
        //dutch national problem
        Integer []c={1,4,5,4,4,6,2,3};
        solveDutch2(c,4);
        
        Integer [] d={1,0,1,2,1,0,1,2};
        sortMarbles(d);
        System.out.println(Arrays.toString(d));
    }

    private static void sortMarbles(Integer[] d) {
        solveDutch(d,1);
    }

    private static void solveDutch(Integer[] c, int pivot) {
        Integer lessBoundary =-1;
        Integer greaterBoundary =c.length;
        Integer i=0; Integer j=c.length-1;
        while(lessBoundary<=greaterBoundary && j>=lessBoundary){
            if (c[i]<pivot){
                swap(c, ++lessBoundary, i++);
            }
            if (c[j]>pivot){
                swap(c, --greaterBoundary, j--);
            }
            i++;
            j--;
        }
    }
    private static Integer [] solveDutch2(Integer[] c, int pivot) {
        Integer lessBoundary =-1;
        Integer greaterBoundary =c.length;
        Integer i=0;
        while(lessBoundary<=greaterBoundary && i<greaterBoundary){
            if(c[i]<pivot){
                swap(c, ++lessBoundary, i++);
            }else if(c[i]>pivot){
                swap(c, --greaterBoundary, i); //this is ......boundary 0 ......0(i).... case
                /// process this element again - assume it was here in this condition only; we don't know what unprocessed element came from right side
            }else{
                i++;
            }
        }
        return c;
    }

    private static Integer[] shiftZero(Integer[] a) {
        Integer boundary =-1;
        Integer i= 0;
        while(i<a.length){
            if(a[i]==0){
                swap(a, ++boundary, i++);
            }
            i++;
        }
    return a;
    }
    private static  Integer [] shiftZeroLast(Integer [] a){
        Integer boundary = a.length;
        Integer j= a.length-1;
        while(j>=0){
            if(a[j]==0){
                swap(a, --boundary, j--);
            }
            j--;
        }
        return a;
    }

    private static void swap(Integer[] a, Integer i, Integer j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
