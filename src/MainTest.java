
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.EmptyGraphGenerator;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.RandomWalkVertexIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.jgrapht.util.SupplierUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;


public class MainTest {

    public static void main(String[] args) throws IOException {


        //Generate Directed Weighted Graph

        System.out.println("Please isert the number of vertices: ");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        String input = reader.readLine();
        int SIZE = Integer.parseInt(input);





        Supplier<Integer> vSupplier = new Supplier<Integer>() {

            private int id = 0;

            @Override
            public Integer get() {
                return id++;
            }
        };

        Supplier<Integer> vSupplier2 = new Supplier<Integer>() {

            private int id = 0;

            @Override
            public Integer get() {
                return id++;
            }
        };


        // weight = cost of the edge
        SimpleDirectedWeightedGraph exGraph =
                new SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge>(vSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
        // weight = 1 / cost of the edge
        SimpleDirectedWeightedGraph exGraph2 =
                new SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge>(vSupplier2, SupplierUtil.createDefaultWeightedEdgeSupplier());


        EmptyGraphGenerator<Integer, DefaultWeightedEdge> gen = new EmptyGraphGenerator<>(SIZE);
        EmptyGraphGenerator<Integer, DefaultWeightedEdge> gen2 = new EmptyGraphGenerator<>(SIZE);

        gen.generateGraph(exGraph);
        gen2.generateGraph(exGraph2);

        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (rand.nextBoolean() && i !=j) {
                    exGraph.addEdge(i, j);
                    exGraph2.addEdge(i,j);
                    l1.add(i);
                    l2.add(j);
                    double d = Math.round(rand.nextDouble()*100)+1;
                    exGraph.setEdgeWeight(i,j,d);
                    exGraph2.setEdgeWeight(i,j,1/d);
                    System.out.println("S -- Weight from " +i+ " To "+ j +" is = "+ d);
                    System.out.println("R -- Weight from " +i+ " To "+ j +" is = "+ 1/d);
                }
            }
        }

        DijkstraShortestPath<Integer, DefaultWeightedEdge> alg = new DijkstraShortestPath<>(exGraph);
        DijkstraShortestPath<Integer,DefaultWeightedEdge> alg2 = new DijkstraShortestPath<>(exGraph2);

        double d = alg.getPathWeight(0,SIZE-1);
        GraphPath gp = alg.getPath(0,SIZE-1);

        double dd = alg2.getPathWeight(0, SIZE-1);
        GraphPath gp2 = alg2.getPath(0, SIZE-1);


        System.out.println(gp.getVertexList().toString());
        System.out.println("Simple shortest path weight = " + d);

        System.out.println(gp2.getVertexList().toString());
        System.out.println("reciprocal shortest path weight = " + dd);





//        Iterator<Integer> iter = new DepthFirstIterator<>(exGraph);
//        while (iter.hasNext()) {
//            Integer vertex = iter.next();
//            exGraph.incomingEdgesOf(iter.next());
//            System.out
//                    .println(
//                            "Vertex " + vertex + " has incomming edges from: " +
//                                    exGraph.incomingEdgesOf(vertex));
//
//
//        }


//        //Generate empty directed graph
//        int SIZE = 5;
//        Supplier<String> vSupplier = new Supplier<String>() {
//
//            private int id = 0;
//
//            @Override
//            public String get() {
//                return "v" + id++;
//            }
//        };
//
//        Graph<String, DefaultEdge> exGraph =
//                new SimpleDirectedGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
//
//        EmptyGraphGenerator<String, DefaultEdge> gen = new EmptyGraphGenerator<>(SIZE);
//
//        gen.generateGraph(exGraph);
//
//
//        Random rand = new Random();
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = i + 1; j < SIZE; j++) {
//                if (rand.nextBoolean()) {
//                    exGraph.addEdge("v" + i, "v" + j);
//                }
//            }
//        }
//
//        for (int i = SIZE-1; i > 0; i--)
//        {
//            for (int j = i-1; i > 0; i--)
//            {
//                if (rand.nextBoolean()) {
//                    exGraph.addEdge("v" + i, "v" + j);
//                }
//            }
//        }
//
//
//        Iterator<String> iter = new DepthFirstIterator<>(exGraph);
//        while (iter.hasNext()) {
//            String vertex = iter.next();
//            exGraph.incomingEdgesOf(iter.next());
//            System.out
//                    .println(
//                            "Vertex " + vertex + " has incomming edges from: " +
//                                    exGraph.incomingEdgesOf(vertex));
//
//
//        }


//        //Generate empty simple Graph
//        int SIZE = 5;
//
//        Supplier<String> vSupplier = new Supplier<String>() {
//
//            private int id =0;
//
//            @Override
//            public String get() {
//                return "v" + id++;
//            }
//        };
//
//        Graph<String, DefaultEdge> exGraph =
//               new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
//
//        EmptyGraphGenerator<String, DefaultEdge> gen = new EmptyGraphGenerator<>(SIZE);
//
//        gen.generateGraph(exGraph);
//
//
//        Random rand = new Random();
//        for (int i = 0; i < SIZE; i++)
//        {
//            for (int j = i+1; j < SIZE; j++)
//            {
//                if(rand.nextBoolean())
//                {
//                    exGraph.addEdge("v"+i, "v"+j);
//                }
//            }
//        }
//
//        Iterator<String> iter = new DepthFirstIterator<>(exGraph);
//        while (iter.hasNext()) {
//            String vertex = iter.next();
//            System.out
//                    .println(
//                            "Vertex " + vertex + " is connected to: "
//                                    + exGraph.edgesOf(vertex).toString());
//
//
//        }
//

        //Generate graph
//        final int SIZE = 100;
//
//        Supplier<String> vSupplier = new Supplier<String>() {
//
//            private int id = 0;
//
//
//            @Override
//            public String get() {
//                return "v" + id++;
//            }
//        };
//
//        Graph<String, DefaultEdge> completeGraph =
//                new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
//
//        CompleteGraphGenerator<String, DefaultEdge> completeGenerator =
//                new CompleteGraphGenerator<>(SIZE);
//
//        completeGenerator.generateGraph(completeGraph);
//
//        Iterator<String> iter = new DepthFirstIterator<>(completeGraph);
//        while (iter.hasNext()) {
//            String vertex = iter.next();
//            System.out
//                    .println(
//                            "Vertex " + vertex + " is connected to: "
//                                    + completeGraph.edgesOf(vertex).toString());
//
//
//        }

    }
}
