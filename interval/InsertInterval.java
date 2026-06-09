package interval;

import java.util.*;

public class InsertInterval {
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length;
            int[] s = new int[n];
            int c = 0;
            for (int[] i : intervals) {
                s[c++] = i[0];
            }
            int indexToInsert = findIndex(s, newInterval);
            int[] first = intervals[indexToInsert];

            if (first[1] <= newInterval[0]) {
                first[1] = Math.max(first[1], newInterval[1]);
            }
            //check if first started to overlap with rest of the intervals
            for (int i = indexToInsert; i < n - 1; i++) {
                int[] interval = intervals[i];
                if (interval[i] <= interval[i + 1]) {
                    interval[i] = Math.max(interval[i], interval[i + 1]);
                }
            }
            return intervals;

        }

        int findIndex(int[] startTimes, int[] newInterval) {
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

        System.out.println(":hi");
    }
}