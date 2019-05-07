
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GraphImplementation implements Graph {

    private int[][] edges;
    private int numVertex;

    public static void main(String[] args) {
        GraphImplementation g = new GraphImplementation(5);
        g.printGraph();
        g.addEdge(1, 2);
        g.addEdge(1,3);
        g.addEdge(0,4);
        g.addEdge(2,3);
        g.addEdge(0,1);
        g.addEdge(2,4);
        g.printGraph();
        int[] array = g.createIncidentArray();
        g.topologicalSort();
    }

    public GraphImplementation(int vertices) {
        initEdges(vertices);
        numVertex = vertices;
    }

    public void addEdge(int src, int tar) throws IllegalArgumentException {
        if (src == tar) {
            throw new IllegalArgumentException("src and tar are the same value");
        } else {
            edges[src][tar] = 1;
        }
    }

    public int[] neighbors(int vertex) {
        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            if (edges[vertex][i] == 1) {
                t.add(i);
            }
        }
        int[] temp = new int[t.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = t.get(i);
        }
        return temp;
    }

    public List<Integer> topologicalSort() {
        List<Integer> temp = new LinkedList<>();
        int[] incArray = createIncidentArray();
        int i = 0;
        int finished = 0;
        while(finished < incArray.length){
            if(incArray[i] == 0){
                for(int j = 0; j < numVertex; j++){
                    if(edges[i][j] == 1){
                        incArray[j]--;
                    }
                }
                incArray[i] = -1;
                finished++;
                temp.add(i);
                i = 0;
            } else {
                i++;
            }
            if(i == incArray.length){
                return temp;
            }
        }
        return temp;
    }

    public int[] createIncidentArray(){
        int[] temp = new int[edges.length];
        for(int i = 0; i < edges.length; i++){
            temp[i] = 0;
            for(int j = 0; j < edges.length; j++){
                if(edges[j][i] > 0){
                    temp[i]++;
                }
            }
        }
        String s = "";
        for(int i = 0; i < edges.length; i++){
            s += temp[i] + ", ";
        }
        System.out.println(s);
        return temp;
    }

    private void initEdges(int vertices) {
        edges = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                edges[i][j] = 0;
            }
        }
    }

    private void printGraph() {
        String s = "";
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                s += edges[i][j];
                count++;
                if (count == edges.length) {
                    s += "\n";
                    count = 0;
                } else {
                    s += ", ";
                }
            }
        }
        System.out.println(s);
    }
}
