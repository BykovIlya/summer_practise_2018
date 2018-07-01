package sample;
/**
 * Ford-Bellman algorithm
 * @author Bykov
 * @author Lavrenkova
 * @author Kuharev
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 *	A class for storing the graph and displaying actions on it.
 */
public class Graph implements GraphInterface {
    Graph() {
        negativeCircle = 0;
    }

    /**
     * Class for storing graph edges.
     */
    public class ElementGraphWay {
        int from;
        int to;
        int l;
    }

    /**
	 * 	A class for storing the coordinates of graph nodes.
     */
    public class WaysPoint {
        int x;
        int y;
        int name;
    }

    private final Random random = new Random();
    Vector<ElementGraphWay> list = new Vector<>();
    Vector<Integer> ways = new Vector<>();
    Vector<Integer> road = new Vector<>();
    Vector<WaysPoint> visual = new Vector<>();
    int n;	// number of vertex
    int m = 0; // number of edges
    int v;	// vertxe - beginner
    int V = -1;
    private int negativeCircle;

    /**
     * Reading the graph from the file
     */
    public void inputFile() {
        negativeCircle = 0;
        list.clear();
        visual.clear();
        Scanner sc;
        try {
            sc = new Scanner(new File("in.txt"));
            try {
                if (sc.hasNextInt()) { 
                    n = sc.nextInt();
                } else System.out.println("There is not enough data in the file");
                if (sc.hasNextInt()) {
                    m = sc.nextInt();
                } else System.out.println("There is not enough data in the file");
                XY();
                for (int j = 0; j < m; j++) {
                    ElementGraphWay Q = new ElementGraphWay();
                    if (sc.hasNextInt()) {
                        Q.from = sc.nextInt();
                        Q.from--;
                    }
                    if (sc.hasNextInt()) {
                        Q.to = sc.nextInt();
                        Q.to--;
                    }
                    if (sc.hasNextInt()) {
                        Q.l = sc.nextInt();
                    }
                    list.add(Q);

                }
            } catch (Exception ex) {
                System.out.println("File is empty!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }

    }

    /**
     * 
	 *	Function of generating a random graph.
     */
    public void inputGeneration() {
        negativeCircle = 0;
        list.clear();
        visual.clear();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ElementGraphWay Q = new ElementGraphWay();
                Q.from = i;
                int q;// way to
                do {
                    q = random.nextInt(n) + 1;
                    --q;
                } while (q == i);
                Q.to = q;
                Q.l = random.nextInt(100);
                list.add(Q);
            }
        }
        m = n * m;
        XY();
    }

    /**
     * The function of finding the shortest paths from a given vertex in a graph.
     *
     * @param P controller, in which the results will be displayed.
     */
    public void searchAlgorithm(Controller P) {
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ways.add(inf);
            road.add(-1);
        }
        v--;// vertex from
        ways.set(v, 0);
        P.graphAlg.clear();
        P.graphAlg.appendText((v + 1) + "|\t");
        for (int k = 1; k <= n; k++) {
            P.graphAlg.appendText(k + "|\t");
        }
        P.graphAlg.appendText("\n\t");
        for (int k = 0; k < n; k++) {
            if (ways.elementAt(k) == inf)
                P.graphAlg.appendText("∞" + "\t");
            else {
                if (ways.elementAt(k) > 9)
                    P.graphAlg.appendText(ways.elementAt(k) + "\t");
                else P.graphAlg.appendText(ways.elementAt(k) + "\t");
            }
        }
        P.graphAlg.appendText("\n");
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < m; ++j) {
                if ((ways.elementAt(list.elementAt(j).from) < inf) & ((ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l) < ways.elementAt(list.elementAt(j).to))) {
                    if (i == n) {
                        negativeCircle = 1;
                        P.graphAlg.clear();
                        P.result.clear();
                        P.graphAlg.appendText("The graph has negative cycles");
                        P.result.appendText("The graph has negative cycles");
                        return;
                    } else {
                        ways.set(list.elementAt(j).to, (ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l));
                        road.set(list.elementAt(j).to, list.elementAt(j).from);
                        P.graphAlg.appendText("\t");
                        for (int k = 0; k < n; k++) {
                            if (ways.elementAt(k) == inf)
                                P.graphAlg.appendText("∞"+"\t");
                            else {
                                if (ways.elementAt(k) > 99)
                                    P.graphAlg.appendText(ways.elementAt(k) + "\t");
                                else P.graphAlg.appendText(ways.elementAt(k) + "\t");
                            }
                        }
                        P.graphAlg.appendText("\n");

                    }
                }
            }

        }
    }



    /**
     * 
	 *	The function of derivation of shortest paths from a given vertex in a graph.
     *
     * @param P controller, in which the results will be displayed.
     */
    public void outputWays(Controller P) {
        if (negativeCircle == 0) {
            P.result.clear();
            Vector<Integer> path = new Vector<>();
            for (int j = 0; j < n; j++) {
                if (j != (v)) {
                    if (ways.elementAt(j) == Integer.MAX_VALUE) {
                        P.result.appendText("Way from the top " + (v + 1) + " to the top " + (j + 1) + ": NO\n");
                    } else {
                        path.clear();
                        for (int cur = j; cur != -1; cur = road.elementAt(cur))
                            path.add(cur);
                        P.result.appendText("Way from the top " + (v + 1) + " to the top " + (j + 1) + ": " + ways.elementAt(j) + "\nShortest way: ");
                        for (int i = path.size() - 1; i >= 1; i--) {
                            int l = (path.elementAt(i) + 1);
                            P.result.appendText(l + "->");
                        }
                        P.result.appendText((path.elementAt(0) + 1) + "\n");
                        path.clear();
                    }
                }
            }
        }
    }

    /**
     * The function of assigning coordinates to the nodes of the graph.
     */
    private void XY() {
        double fi = 360 / n;
        for (int i = 0; i < n; i++) {
            WaysPoint q = new WaysPoint();
            int x = 130;
            int vertexX = 120;
            q.x = x + (int) (long) (vertexX * Math.cos(i * fi * Math.PI / 180));
            int vertexY = 105;
            int y = 105;
            q.y = y + (int) (long) (vertexY * Math.sin(i * fi * Math.PI / 180));
            q.name = i + 1;
            visual.add(q);
        }
    }

}
