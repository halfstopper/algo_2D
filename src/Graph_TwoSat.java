import java.util.*;
import java.util.LinkedList;

public class Graph_TwoSat
{
    private int V;   // No. of vertices (2X of variables)
    private LinkedList<Integer> adj[]; //Adjacency List
    private int K;
    //Constructor for Graph_TwoSat
    Graph_TwoSat(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Converting OR statement into Edges
    void OR(int v, int w){
        if ((v>0)&&(w>0)){
            addEdge(-1*v,w);
            addEdge(-1*w,v);
        }
        if ((v<0)&&(w>0)){
            addEdge(-1*v,w);
            addEdge(-1*w,v);
        }
        if ((v>0)&&(w<0)){
            addEdge(-1*v,w);
            addEdge(-1*w,v);
        }
        if ((v<0)&&(w<0)){

            addEdge(-1*v,w);
            addEdge(-1*w,v);
        }
    }
    //Function to add an edge into the graph
    void addEdge(int v, int w){
        //System.out.println(v+" "+w);
        // Linkedlist do not allow negative value.
        //Example: (variable: 1,2,3) -> (Edges: -1,-2,-3,1,2,3)
        //Convert become (0,1,2,3,4,5) [0,1,2] is from (1,2,3)  [3,4,5] is from (-1,-2,-3)
        if (v<0){//Negative node
            v = (-1)*v + V/2 -1;
        }
        else { v -= 1; }
        if (w<0){ //Negative
            w = (-1)*w + V/2 -1;
        }
        else { w -= 1; }
        adj[v].add(w);
    }
    // A recursive function to print DFS starting from v
    void DFSUtil(int v,boolean visited[], LinkedList<Integer> solution)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        // Convert Back to Negative vertices solution
        if (v >= (V/2)){
            K = (v+1-(V/2))*-1;
        }
        // Add +1 Each positive vertices solution
        else{
            K = v + 1;
        }
        //System.out.print(K + " ");
        //Print one vertice solution
        solution.add(K);

        int n;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited, solution);
        }
    }
    // Function that returns reverse (or transpose) of this graph
    Graph_TwoSat getTranspose()
    {
        Graph_TwoSat g = new Graph_TwoSat(V);
        for (int v = 0; v < V; v++)
        {
            // Recursive for all the vertices adjacent to this vertex
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }
    void fillOrder(int v, boolean visited[], Stack stack)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }
        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(new Integer(v));
    }
    // The main function that finds and prints all strongly
    // connected components
    LinkedList<LinkedList<Integer>> getSCCs()
    {
        Stack stack = new Stack();
        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;
        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);
        // Create a reversed graph
        Graph_TwoSat graph_1 = getTranspose();
        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;
        // Now process all vertices in order defined by Stack
        LinkedList<LinkedList<Integer>> solutions = new LinkedList<>();
        while (stack.empty() == false)
        {
            // Pop a vertex from stack
            int v = (int)stack.pop();
            // Print Strongly connected component of the popped vertex
            if (visited[v] == false)
            {
                LinkedList<Integer> solution = new LinkedList<>();
                graph_1.DFSUtil(v, visited, solution);
                //System.out.println(" ");
                solutions.add(solution);
                //The separation
            }
        }
        return solutions;
    }
}
