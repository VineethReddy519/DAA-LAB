import java.util.*;

public class GraphColoring {
    private int[][] graph;
    private int[] colors;
    private int numVertices;
    private int numColors;

    public GraphColoring(int[][] graph, int numColors) {
        this.graph = graph;
        this.numVertices = graph.length;
        this.numColors = numColors;
        this.colors = new int[numVertices];
    }

    public boolean isSafe(int vertex, int color) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && color == colors[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean graphColoringUtil(int vertex) {
        if (vertex == numVertices) {
            return true; // All vertices are colored
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color;
                if (graphColoringUtil(vertex + 1)) {
                    return true;
                }
                colors[vertex] = 0; // Backtrack
            }
        }

        return false;
    }

    public boolean graphColoring() {
        if (!graphColoringUtil(0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        // Print the solution
        System.out.println("Solution exists and the coloring of vertices is:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + ": Color " + colors[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int numColors = 3; // Number of colors available

        GraphColoring graphColoring = new GraphColoring(graph, numColors);
        graphColoring.graphColoring();
    }
}

