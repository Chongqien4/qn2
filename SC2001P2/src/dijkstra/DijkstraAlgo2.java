package dijkstra;

import java.util.*;  
import java.lang.*;


class Edge {
	int vert,weight;
	
	public Edge(int vert, int weight) {
		this.vert = vert;
		this.weight = weight;
	}
	
	public int getVertex() {
		return vert;
	}
	
	public int getWeight() {
		return weight;
	}
	
}

class Graph {
	int numOfVert;
	ArrayList<Edge>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int numOfVert) {
		this.numOfVert = numOfVert;
		adjList = new ArrayList[numOfVert];
		for(int i = 0;i<numOfVert;i++) {
			adjList[i] = new ArrayList<>();
		}
	}
	
	public void addEdge(int src, int dest, int weight) {
		adjList[src].add(new Edge(dest,weight));
	}
	
}
    
class Node implements Comparable<Node>{
	int vert;
	int dist;
	
	public Node(int vert, int dist) {
		this.vert = vert;
		this.dist = dist;
	}
	
	
	@Override
	public int compareTo(Node other) {
		return Integer.compare(this.dist, other.dist);
	}
	
}

public class DijkstraAlgo2{
	
	public static int[] dijkstra(Graph graph , int startVert, int[] compareCount) {
		int numOfVert = graph.numOfVert;
		int[] dist = new int[numOfVert];
		boolean[] visited = new boolean[numOfVert];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startVert] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(startVert,0));
		
		while(!pq.isEmpty()) {
			Node currentNode = pq.poll();
			int u = currentNode.vert;
			
			if (visited[u])continue;
			
			visited[u]= true;
			
			for(int i=0;i<graph.adjList[u].size();i++) {
				Edge neighbor = graph.adjList[u].get(i);
				int v = neighbor.getVertex();
				int weight = neighbor.getWeight();
				
				
				if(!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
					pq.offer(new Node(v,dist[v]));
					compareCount[0]++;
				}
			}
			
		}
		
		return dist;
		
	}
	
	
	public static void main(String[] args) {
		int numOfVert =5;
		Graph graph = new Graph(numOfVert);
       
		//graph.addEdge(numOfVert, numOfVert, numOfVert);
		graph.addEdge(0, 1, 10);// 0 -> 1  weight 10
        graph.addEdge(0, 4, 5);// 0 -> 4 weight 5
        graph.addEdge(1, 2, 1); // 1 -> 2 weight 1
        graph.addEdge(1, 4, 2); // 1 -> 4 weight 2
        graph.addEdge(2, 3, 4); // 2->3 weight 4
        graph.addEdge(3, 2, 6); // 3->2 weight 6
        graph.addEdge(4, 1, 3); // 4->1 weight 3
        graph.addEdge(4, 2, 9); // 4->@ weight 9
        graph.addEdge(4, 3, 2); // 4->3 weight 2
        // Comparison count to measure algorithm performance
        int[] compareCount = new int[1];
        Arrays.fill(compareCount, 0);

        // Perform Dijkstra's algorithm from vertex 0
        int[] distances = dijkstra(graph, 0, compareCount);

        // Output the results
        System.out.println("Shortest distances from vertex 0:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + ": " + distances[i]);
        }

        System.out.println("Number of comparisons: " + compareCount[0]);
    }
	
}

