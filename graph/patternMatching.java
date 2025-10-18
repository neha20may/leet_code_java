package graph;

import java.util.*;

public class patternMatching {
}

class SolutionPattern {
    public Map<String, List<String>> processDict(String[] dict) {
        Map<String, List<String>> processedDict = new HashMap();

        for (int i = 0; i < dict.length; i++) {
            String word = dict[i];
            char[] charWords = word.toCharArray();

            for (int j = 0; j < charWords.length; j++) {
                char c = charWords[i];
                charWords[i] = '*';
                String patternWord = word.substring(0, i) + charWords[i] + "" + word.substring(i + 1, charWords.length);
                processedDict.getOrDefault(patternWord, new ArrayList()).add(word);
            }
        }
        return processedDict;

    }

    public List<String> getNeighbor(String word, Map<String, List<String>> dict) {
        List<String> patternWords = new ArrayList();
        for (int j = 0; j < word.length(); j++) {
            char c = word.charAt(j);
            String patternWord = word.substring(0, j) + word.charAt(j) + "" + word.substring(j + 1, word.length());
            patternWords.add(patternWord);
        }
        List<String> res = new ArrayList();

        for (String patternWord : patternWords) {
            res.addAll(dict.getOrDefault(patternWord, new ArrayList<>()));
        }
        return res;
    }

    public void addNeighborToQueue(String node, Map<String, List<String>> processedDict,
                                   Deque<String> q, HashSet<String> seen) {
        for (String neighbor : getNeighbor(node, processedDict)) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                q.add(neighbor);
            }
        }
    }

    public int bfs(String start, String end, Map<String, List<String>> processedDict) {
        Deque<String> q = new ArrayDeque();
        HashSet<String> seen = new HashSet();

        // startNode.state= State.visiting;
        seen.add(start);
        q.add(start);
        int steps = 0;
        while (!q.isEmpty()) {
            int curQLength = q.size();
            steps = steps + 1;
            for (int i = 0; i < curQLength; i++) {
                String node = q.poll();
                if (node.equals(end)) {
                    return steps;
                }
                addNeighborToQueue(node, processedDict, q, seen);
            }

        }
        return -1;

    }

    public int minMutation(String startGene, String endGene, String[] bank) {

        Map<String, List<String>> processedDict = processDict(bank);

        return bfs(startGene, endGene, processedDict);

    }
}
