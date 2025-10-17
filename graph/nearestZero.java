package graph;

import java.sql.SQLOutput;
import java.util.*;

public class nearestZero {
    public static void main(String[] args) {
        int [][]mat={{0,0,0},{0,1,0},{1,1,1}};
        Solution solution = new Solution();
        int [][]res= solution.updateMatrix(mat);
        System.out.println(res);
    }
}
class Solution {
    enum State{
        unvisited, visiting, visited;
    }

    class Graph{
        List<Node> nodes;
        Graph(List<Node> nodes){
            this.nodes= nodes;
        }
    }

    class Node{
        int i,j;
        int data;
        int dist;
        List<Node> ngh;
        State state;
        Node(int data, int i, int j){
            this.data=data;
            this.state = State.unvisited;
            ngh= new ArrayList();
            this.dist=0;
            this.i= i;
            this.j=j;
        }
        public String toString(){
            return "Node:: i="+i+" j="+j+" val="+data+" dist= "+dist +" state="+state;
        }
    }

    int [][] dir ={{-1,0},{1,0},{0,-1},{0,1}};

    Boolean valid(int i, int j, int m, int n){
        return i>=0 && i< m && j>=0 && j< n;
    }
    public void addNgh(Node node, int i, int j, int m , int n, Map<Integer, Map<Integer, Node>> map){
        for(int []d: dir){
            int x= i+d[0];
            int y= j+d[1];
            if(valid(x, y, m, n)){
                node.ngh.add(map.get(x).get(y));
            }
        }
    }
    public void bfs(List<Node> start){
        Queue<Node> q= new ArrayDeque();
        for(Node node: start){
            node.state= State.visiting;
            q.add(node);
        }

        while(!q.isEmpty()){
            Node node= q.poll();
            System.out.println(node);
            for(Node ne: node.ngh){
                System.out.println("neigth="+ne);
                if(ne.state == State.unvisited){
                    System.out.println("unvisited neigth="+ne);
                    if(ne.data==1) {
                        ne.dist = node.dist+1;
                    }else{
                        //nothing
                    }
                    ne.state= State.visiting;
                    q.add(ne);
                }
            }
            node.state= State.visited;
        }

    }
    public int [][] getDist(Map<Integer, Map<Integer, Node>> map, int m, int n){
        int [][]res= new int[m][n];
        for(int i=0;i<m; i++){
            for(int j=0; j<n; j++){
                Node node= map.get(i).get(j);
                res[i][j]= node.dist;
            }
        }
        return res;
    }
    public int[][] updateMatrix(int[][] mat) {
        Map<Integer, Map<Integer, Node>> map = new HashMap();
        List<Node> nodes= new ArrayList();
        List<Node> startNodes= new ArrayList();

        int m= mat.length;
        int n= mat[0].length;

        for(int i=0;i<m; i++){
            Map<Integer, Node> map2= new HashMap();
            for(int j=0; j<n; j++){
                System.out.println(" i= "+i+" j="+j+" mat[i][j]="+mat[i][j]);
                Node node= new Node(mat[i][j], i, j);
                map2.put(j, node);
                nodes.add(node);
                if(mat[i][j]==0){
                    startNodes.add(node);
                }
            }
            map.put(i, map2);
        }
        for(int i=0;i<m; i++){
            for(int j=0; j<n; j++){
                Node node= map.get(i).get(j);
                addNgh(node, i, j, m, n, map);
            }
        }
        Graph g= new Graph(nodes);
        bfs(startNodes);
        int [][]result = getDist(map, m, n);
        return result;
    }
}
