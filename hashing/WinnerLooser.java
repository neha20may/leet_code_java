package hashing;

import java.util.*;
import java.util.stream.Collectors;
// phew!! lots of boxong and unboxing and stuff
public class WinnerLooser {
    public List<Integer> getOneLostPlayer(Map<Integer, Integer> looserFreq) {

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer>entry: looserFreq.entrySet()){
            if(entry.getValue()==1){
                result.add(entry.getKey());
            }
        }
        return result;

    }

    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> answer = new ArrayList<>();

        Arrays.stream(matches)
                .map(x -> x[0])
                .toArray();
/**
 Integer::intValue   →   Integer [object]  →  int [primitive]
 Integer::new        →   int [primitive]   →  Integer [object]

 */
        int[] winners = Arrays.stream(
                        Arrays.stream(matches)
                                .map(x -> x[0])
                                .toArray(Integer[]::new))
                .mapToInt(Integer::intValue)
                .toArray();
        int[] loosers = Arrays.stream(
                        Arrays.stream(matches)
                                .map(x -> x[1])
                                .toArray(Integer[]::new))
                .mapToInt(Integer::intValue)
                .toArray();
        Map<Integer, Integer> looserFreq = Arrays.stream(matches)
                .map(x -> {
                    return Map.entry(x[1], 1);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
      Set<Integer> winnerBoxed= Arrays.stream(winners).boxed().collect(Collectors.toSet());
      Set<Integer> looserBoxed= Arrays.stream(loosers).boxed().collect(Collectors.toSet());
      winnerBoxed.addAll(looserBoxed);
      Set<Integer> totalPlayers= winnerBoxed;
      totalPlayers.removeAll(looserBoxed);
      Set<Integer> allWinners = totalPlayers;
      Integer [] allWinnerArray= allWinners.stream().toArray(Integer[]::new);

        answer.add(new ArrayList<>(allWinners));
        answer.add( getOneLostPlayer(looserFreq));
        return answer;

    }
}
class Solution {

    public List<List<Integer>> findWinners(int[][] matches) {
        List<Integer> winners= Arrays.stream(matches) //Stream<in[]>
                .map(x->x[0]) //Stream<Integer> map converts to object
                .collect(Collectors.toList());
        List<Integer> loosers= Arrays.stream(matches) //Stream<in[]>
                .map(x->x[1]) //Stream<Integer> map converts to object
                .collect(Collectors.toList());

        List<Integer> notLostAnyMatch= new ArrayList(winners);
        notLostAnyMatch.removeAll(loosers);

        Map<Integer, Integer> looserFreq= new HashMap();
        loosers.forEach(x->
        {
            looserFreq.put(x, looserFreq.getOrDefault(x,0)+1);
        });
        List<Integer> oneLost= new ArrayList();

        looserFreq.forEach((x,y)->{
            if( y ==1){
                oneLost.add(x);
            }
        });
        List<List<Integer>> result= new ArrayList();
        result.add(notLostAnyMatch);
        result.add(oneLost);

        Collections.sort(notLostAnyMatch, (a,b)->{return b-a;});

        return result;


    }
    public void testSynatx(){
        String text="abc";
        char[] arr= text.toCharArray();
        Map<Character, Integer> map = new HashMap();
        for(int i=0; i<arr.length; i++){
            Character c= arr[i];
            map.put(c, map.getOrDefault(c,0)+1);
        }

        int b=0; int a=0;int l= 0; int o=0; int n=0;
        b= map.getOrDefault('b',0);
        a=map.getOrDefault('a',0);
        l=map.getOrDefault('l',0);
        o=map.getOrDefault('o',0);
        n=map.getOrDefault('n',0);
        int count= Math.min(Math.min(Math.min(b,a), l),n);
        int count2= Math.min(count, l/2);

//        Arrays.stream(arr)
//                .map(x->x)
//                .toArray();
//        return Math.min(count, count2);
        map.entrySet();
    }
}
