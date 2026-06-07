package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionPath {
    enum State{
        unvisited, visited, visiting;
    }
    class Graph{
        List<Node> nodes;
        Graph(List<Node> nodes){
            this.nodes= nodes;
        }
    }
    class Node{
        int data;
        List<Node> neigh;
        State state;
        public Node(int data){
            this.data= data;
            neigh= new ArrayList();
            state=State.unvisited;
        }
    }
    public void dfs(Node n){
        n.state= State.visiting;
        for(Node ne: n.neigh){
            if(ne.state== State.unvisited){
                dfs(ne);
            }
        }
        n.state= State.visited;
    }
    public int countComponents(int n, int[][] edges) {
        Map<Integer, Node> map = new HashMap();
        for(int i=0; i< n; i++){
            map.put(i, new Node(i));
        }
        for(int [] edge: edges){
            int x= edge[0];
            int y= edge[1];
            map.get(x).neigh.add(map.get(y));
            map.get(y).neigh.add(map.get(x));
        }
        List<Node> nodes=new ArrayList(map.values());
        Graph g= new Graph(nodes);
        int res=0;
        for(Node node: nodes){
            if(node.state == State.unvisited){
                res++;
                dfs(node);
            }
        }
        return res;
    }
}
