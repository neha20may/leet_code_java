package interval;

import java.util.*;

public class meetingroom2 {
    class Solution {
        public int minMeetingRooms(int[][] intervals) {
            List<event> events= new ArrayList();
            for(int[] interval: intervals){
                events.add(new event(interval[0],1));
                events.add(new event(interval[1],-1));
            }
            //sort
            Collections.sort(events, (a,b)->{return a.time- b.time;});
            int maxroom= Integer.MIN_VALUE;
            int cur=0;
            for(event e: events){
                cur= cur+ e.value;
                if(cur> maxroom){
                    maxroom= cur;
                }

            }
            return maxroom;
        }
        class event{
            public int time;
            public int value;
            public event (int a, int b){
                this.time =a;
                this.value=b;
            }
        }
    }
}
