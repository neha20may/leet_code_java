package interval;

import java.util.*;

public class MeetingRoom1{
    static class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            Arrays.sort(intervals, (a, b)->{ return a[0]-b[0];});


            int prevEnd= Integer.MIN_VALUE;
            Boolean ans= true;
            for(int[] i: intervals){
                int start = i[0];
                int end= i[1];

                if(start < prevEnd){
                    ans= false;
                    break;
                }
                prevEnd = end;
            }
            return ans;

        }
    }

    public static void main(String[] args) {
        Solution s= new Solution();
//        [[0,30],[5,10],[15,20]]
        int[][] meetings={{0,30},{5,10},{15,20}};
        Boolean ans= s.canAttendMeetings(meetings);
        System.out.println(ans);
    }

}
