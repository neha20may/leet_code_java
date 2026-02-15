package greedy;

import java.util.*;

public class VideoStitching {
    class interval {
        public int start;
        public int end;
        public int span;

        public interval(int a, int b) {
            start = a;
            end = b;
            span = b - a;
        }
    }

    static class Solution {
        public int videoStitching(int[][] clips, int time) {
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });
            Map<Integer, Integer> map = new TreeMap<>();
            for (int[] c : clips) {
                int start = c[0];
                int end = c[1];
                if (map.containsKey(start)) {
                    if (map.get(start) < end) {
                        map.put(start, end);
                    }
                } else {
                    map.put(start, end);
                }
            }
            System.out.println(map);
            /**
             * so start with start and then whatver is til < curEnd - allt hise are canddateds and we need to pick one with max end
             *
             */
            int curStart = -1;
            int curEnd = -1;
            int ans = 0;
            List<int[]> ansPairs = new ArrayList<>();
            List<int[]> cand = new ArrayList<>();
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                int start = e.getKey();
                int end = e.getValue();
                if (start == curEnd + 1) {
                    ansPairs.add(new int[]{curStart, curEnd});
                    ans = ans + 1;
                    curStart = start;
                    curEnd = end;
                } else {
                    if (start <= curEnd) {
                        cand.add(new int[]{start, end});
                    } else {
                        //now check if any canidates fit in the exmpansion
                        Collections.sort(cand, (a, b) -> {
                            return b[1] - a[1];
                        });
                        int[] expansionCan = cand.get(0);
                        if (curEnd < expansionCan[1]) {
                            curEnd = expansionCan[1];
ans= ans+1;            }
                        cand.clear();
                        //now check this start and end
                        if (start <= curEnd) {
                            //cool
                        } else {
                            return -1;//gap
                        }
                    }
                }

            }
            if(!cand.isEmpty()){
                Collections.sort(cand, (a, b) -> {
                    return b[1] - a[1];
                });
                int[] expansionCan = cand.get(0);
                if (curEnd < expansionCan[1]) {
                    curEnd = expansionCan[1];
ans=ans+1;      }
                cand.clear();
                //now check this start and end
                if(curEnd== time){
return ans;   }else{
return -1;    }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        Solution s= new Solution();
        int [][] clips = {new int[]{0, 2},
                new int[]{4, 6},
                new int[]{8, 10},
                new int[]{1, 9},
                new int[]{1, 5},
                new int[]{5, 9}
        };
        int time= 10;
        s.videoStitching(clips, time);
    }
}

