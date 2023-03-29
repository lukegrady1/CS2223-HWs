import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Dijkstra {
    public static final String R = "\u001B[91m";
    public static final String RESET = "\u001B[0m";
    public static final int P = 10;

    // Helper class to store a node's distance and its index
    static class Node implements Comparable<Node> {
        int dist;
        int index;

        public Node(int dist, int index) {
            this.dist = dist;
            this.index = index;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    static void dijkstra(int[][] graph, int src, int f) {
        // Create a priority queue to store the distances of the nodes from the source
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Initialize distances of all nodes as infinity
        int[] d = new int[P];
        Arrays.fill(d, Integer.MAX_VALUE);

        // Set the distance of the source node as 0
        d[src] = 0;

        // Add the source node to the priority queue
        pq.add(new Node(0, src));

        // Repeat until the priority queue is empty
        while (!pq.isEmpty()) {
            // Extract the node with the minimum distance
            Node curr = pq.poll();

            // If the current distance is greater than the stored distance, skip it
            if (curr.dist > d[curr.index]) continue;

            // Iterate over the neighbors of the current node
            for (int neighbor = 0; neighbor < P; neighbor++) {
                // If the graph does not have an edge between the current node and the neighbor, skip it
                if (graph[curr.index][neighbor] == 0) continue;

                // Calculate the new distance of the neighbor
                int newDist = d[curr.index] + graph[curr.index][neighbor];

                // If the new distance is less than the stored distance of the neighbor, update it
                if (newDist < d[neighbor]) {
                    d[neighbor] = newDist;
                    pq.add(new Node(newDist, neighbor));
                }
            }
        }

        // Print the constructed distance array
        print(d, f);
    }

    static void print(int[] d, int fin) {
        System.out.println("The red represents distance from source: ");
        for (int i = 0; i < P; i++) {
            if (i == fin) {
                System.out.println(R + i + "  " + d[i] + RESET);
            } else System.out.println(i + "  " + d[i]);
        }
    }

    public static void main(String[] args) {
        int graph[][] = new int[][]{
                {0, 53, 10, 12, 0, 0, 0, 0, 0, 0},
                {53, 0, 33, 0, 2, 0, 101, 0, 0, 0},
                {10, 33, 0, 9, 30, 18, 0, 0, 0, 0},
                {12, 0, 9, 0, 0, 17, 0, 0, 6, 0},
                {0, 2, 30, 0, 0, 14, 123, 122, 0, 0},
                {0, 0, 18, 17, 14, 0, 0, 137, 7, 0},
                {0, 101, 0, 0, 123, 0, 0, 8, 0, 71},
                {0, 0, 0, 0, 122, 137, 8, 0, 145, 66},
                {0, 0, 0, 6, 0, 7, 0, 145, 0, 212},
                {0, 0, 0, 0, 0, 0, 71, 66, 212, 0}
        };

        System.out.println("Start node: ");
        Scanner scan = new Scanner(System.in);
        int source = Integer.parseInt(scan.nextLine());
        System.out.println("Final node ");
        int f = Integer.parseInt(scan.nextLine());
        dijkstra(graph, source, f);
    }
}