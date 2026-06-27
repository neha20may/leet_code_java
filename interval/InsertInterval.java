package interval;

import java.util.*;

public class InsertInterval {
    static class Solution {
        static public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length;
            Integer[] s = new Integer[n];
            int c = 0;
            for (int[] i : intervals) {
                s[c++] = i[0];
            }
//            int indexToInsert = findIndex(s, newInterval);
            int indexToInsert = Collections.binarySearch(Arrays.asList(s), newInterval[0]);
            if(indexToInsert<0){
                indexToInsert= -indexToInsert -1 ;
            }
            if(indexToInsert >0){
                indexToInsert= indexToInsert-1;
            }
            int[] first = intervals[indexToInsert];

            if (first[1] >= newInterval[0]) {
                first[1] = Math.max(first[1], newInterval[1]);
            }
            List<int[]> result = new ArrayList();
            for(int i=0; i< indexToInsert; i++){
                result.add(intervals[i]);
            }
            //check if first started to overlap with rest of the intervals
            for (int i = indexToInsert; i < n-1; i++) {
                int[] interval = intervals[i];
                int [] nextInterval = intervals[i+1];
                if (interval[1] >= nextInterval[0]) {
                    interval[1] = Math.max(interval[1], nextInterval[1]);
                    result.add(interval);
                    i++;
                }else{
                    result.add(interval);
                }
            }
            int [] lastInterval = intervals[n-1];
            //is it part of result - got consumed by prev interval or left out?
            int [] lastAddedInterval = result.get(result.size()-1);
            int[] lastInt= intervals[n-1];
            if (lastAddedInterval[1]<lastInt[1]){
                result.add(lastInt);
            }

            return result.toArray(new int[0][]);

        }

        static int findIndex(int[] startTimes, int[] newInterval) {
            // binary search
            int low = 0;
            int high = startTimes.length - 1;
            int start = newInterval[0];
            int ans = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (startTimes[mid] < start) {
                    low = mid + 1;
                } else if (startTimes[mid] > start) {
                    high = mid - 1;
                } else {
                    ans = mid;
                    break;
                }

            }
        return ans;
        }
    }

    public static void main(String[] args) {
//        [[1,2],[3,5],[6,7],[8,10],[12,16]]
//        [4,8]
        int[][] intervals = {{1,2}, {3,5},{6,7}, {8,10}, {12,16}};
        int[] newInterval = {4, 8};
        int[][] result = Solution.insert(intervals, newInterval);
        System.out.println(Arrays.toString(result));
    }
}