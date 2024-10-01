package dijkstra;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DijkstraAlgo2ToCSV extends DijkstraAlgo2{

    public static void main(String[] args) {
        try {
            // Open a FileWriter to write the output to a CSV file
            FileWriter csvWriter = new FileWriter("dijkstra_results.csv");
            // Write the header row
            csvWriter.append("Vertices,Edges,Time (ms),Comparisons\n");

            // Generate different graph sizes for testing
            int[] vertexSizes = {100, 200, 500, 1000, 2000,5000,10000};  // Varying numbers of vertices
            int edgesPerVertex = 3;  // For each vertex, we will generate 10 edges

            for (int numOfVert : vertexSizes) {
                // Create a new graph with numVertices
                Graph graph = new Graph(numOfVert);
                Random rand = new Random();

                // Add random edges between vertices
                int totalEdges = 0;
                for (int i = 0; i < numOfVert; i++) {
                    for (int j = 0; j < edgesPerVertex; j++) {
                        int neighbor = rand.nextInt(numOfVert);
                        int weight = rand.nextInt(100) + 1;  // Random weights between 1 and 100
                        graph.addEdge(i, neighbor, weight);
                        totalEdges++;
                    }
                }

                // Comparison count to track performance
                int[] compareCount = new int[1];
                Arrays.fill(compareCount, 0);
            

                // Time the Dijkstra algorithm
                long startTime = System.nanoTime();
                int[] dist = dijkstra(graph, 0, compareCount);
                long endTime = System.nanoTime();

                // Calculate the time taken in milliseconds
                double timeTaken = (endTime - startTime) / 1e6;

                // Write the results (vertices, edges, time, comparisons) to the CSV file
                csvWriter.append(numOfVert + "," + totalEdges + "," + timeTaken + "," + compareCount[0] + "\n");

                System.out.println("Completed test for " + numOfVert + " vertices, " + totalEdges + " edges.");
            }

            // Close the CSV writer
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Results written to dijkstra_results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

	
	


