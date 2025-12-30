package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class packets_cunt {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        /**
         boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
         Output: 91

         [[5,10], [3,9], [4,7], [2,5]],
         50+27+14

         **/
        Comparator<int[]> c= (a, b)->{
            return b[1]-a[1];
        };
        Arrays.sort(boxTypes, c);
        int ans=0;
        for(int [] r: boxTypes){
            int boxTypeCount= r[0];
            int packets= r[1];
            if(boxTypeCount <= truckSize){
                truckSize= truckSize - boxTypeCount;
                ans= ans+ boxTypeCount*packets;
            }else{
                if(truckSize>0){
                    int fraction = truckSize;
                    int fracPackets= fraction*packets;
                    ans= ans+fracPackets;
                }else{
                    break;
                }
            }
        }
        return ans;
    }
}
