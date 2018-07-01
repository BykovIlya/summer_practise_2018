package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.Vector;

/**
 * Class for controlling the elements of the child panel
 */
public class GraphController extends Controller {
    private int counter = 0;
    @FXML
    private Pane pane1;
    private boolean fordInWork = false;
    private int relaxCounter = 0;
    private boolean last = false;
    private boolean cycle = false;
    private final int inf;

    @FXML
    public static Button nextButton;

    @FXML
    public static Button nextButton1;

    @FXML
    public static Button buildBut;

    public GraphController() {
        inf = Integer.MAX_VALUE;
    }

    /**
     * Обработка нажатия клавиши "Построить".
     * Изображает заданный граф.
     */
    @FXML
    public void graphButton() {
        imageVertex();
        for (int i = 0; i < P.m; i++) {
            Line q = new Line(P.visual.elementAt(P.list.elementAt(i).from).x, P.visual.elementAt(P.list.elementAt(i).from).y - 9, P.visual.elementAt(P.list.elementAt(i).to).x, P.visual.elementAt(P.list.elementAt(i).to).y - 9);
            q.setStrokeWidth(0.5);
            q.setFill(Color.LIGHTGREY);
            pane1.getChildren().add(q);
            this.arrow(P.visual.elementAt(P.list.elementAt(i).from).x, P.visual.elementAt(P.list.elementAt(i).to).x, P.visual.elementAt(P.list.elementAt(i).from).y, P.visual.elementAt(P.list.elementAt(i).to).y, Color.BLACK);
        }
        if (P.V != -1) {
            if (P.ways.elementAt(P.V) == Integer.MAX_VALUE) {

                Label label = new Label("The way from the top " + Integer.toString(P.v + 1) + " to the top " + Integer.toString(P.V + 1) + ": NO\n");
                label.setTextFill(Color.RED);
                label.setLayoutX(0);
                label.setLayoutY(0);
                pane1.getChildren().add(label);
            } else {

                Vector<Integer> path = new Vector<>();
                for (int cur = P.V; cur != -1; cur = P.road.elementAt(cur))
                    path.add(cur);

                addRed(path);
                path.clear();
            }
        }

    }

    private void addRed(Vector<Integer> path) {
        for (int i = path.size() - 1; i >= 1; i--) {

            int l = (path.elementAt(i));
            int k = (path.elementAt(i - 1));

            Label label1 = new Label(Integer.toString(P.ways.elementAt(P.V)));
            label1.setTextFill(Color.RED);
            label1.setLayoutX((P.visual.elementAt(P.V)).x + 10);
            label1.setLayoutY((P.visual.elementAt(P.V).y - 12));
            pane1.getChildren().add(label1);

            Line q = new Line(P.visual.elementAt(l).x, P.visual.elementAt(l).y - 9, P.visual.elementAt(k).x, P.visual.elementAt(k).y - 9);
            q.setStroke(Color.RED);
            q.setStrokeWidth(1);
            pane1.getChildren().add(q);


            this.arrow(P.visual.elementAt(l).x, P.visual.elementAt(k).x, P.visual.elementAt(l).y, P.visual.elementAt(k).y, Color.RED);
        }
    }

    /**
     * Function of drawing the direction of movement along the edge.
     *
     * @param x     coordinates
     * @param x1    coordinates
     * @param y     coordinates
     * @param y1    coordinates
     * @param color color of the line
     */
    private void arrow(int x, int x1, int y, int y1, Color color) {
        double beta = Math.atan2((y) - (y1 - 9), x1 - x);
        double alfa = Math.PI / 10;
        int r1 = 10;

        int x2 = (int) Math.round(x1 - r1 * Math.cos(beta + alfa));
        int y2 = (int) Math.round((y1 - 9) + r1 * Math.sin(beta + alfa));
//g2d.drawLine(x1,y1,x2,y2);
        int x3 = (int) Math.round(x1 - r1 * Math.cos(beta - alfa));
        int y3 = (int) Math.round((y1 - 9) + r1 * Math.sin(beta - alfa));
//g2d.drawLine(x1,y1,x2,y2);
        Line q1 = new Line(x1, y1 - 9, x2, y2);
        Line q2 = new Line(x1, y1 - 9, x3, y3);
        q1.setStroke(color);
        pane1.getChildren().add(q1);
        q2.setStroke(color);
        pane1.getChildren().add(q2);
    }

    /**
     * The function of step-by-step search of shortest paths from a given vertex in a graph.
     *  Handling the keystroke "next step".
     */

    @FXML
    public void graphPaint() {
        fordInWork = true;
        imageVertex();
        for (int i = 0; i < P.m; i++) {
            Line q = new Line(P.visual.elementAt(P.list.elementAt(i).from).x, P.visual.elementAt(P.list.elementAt(i).from).y - 9, P.visual.elementAt(P.list.elementAt(i).to).x, P.visual.elementAt(P.list.elementAt(i).to).y - 9);
            q.setStrokeWidth(0.5);
            q.setFill(Color.BLACK);
            pane1.getChildren().add(q);
            this.arrow(P.visual.elementAt(P.list.elementAt(i).from).x, P.visual.elementAt(P.list.elementAt(i).to).x, P.visual.elementAt(P.list.elementAt(i).from).y, P.visual.elementAt(P.list.elementAt(i).to).y, Color.BLACK);
        }

        for (int i = 1; i < (P.n+1); i++) {
            P.ways.remove(1);
            P.ways.add(inf);
            P.road.add(-1);
        }
        P.ways.set(P.v, 0);
        P.ways.set(P.V, inf);

    }

    private void imageVertex() {
        for (int i = 0; i < P.n; i++) {
            Ellipse C = new Ellipse(P.visual.elementAt(i).x, P.visual.elementAt(i).y, 6, 9);
            C.setFill(Color.LIGHTSKYBLUE);
            pane1.getChildren().add(C);
            Label label = new Label(Integer.toString(P.visual.elementAt(i).name));
            label.setTextFill(Color.BLUEVIOLET);
            label.setLayoutX(P.visual.elementAt(i).x - 3);
            label.setLayoutY(P.visual.elementAt(i).y - 9);
            pane1.getChildren().add(label);
        }
    }

    @FXML
    public void nextStep() {
        if (fordInWork) {

            cycleFord();
            if (last) {
                if (P.ways.elementAt(P.V) == inf) {
                    Label label6 = new Label("Way from the top " + Integer.toString(P.v + 1) + " to the top " + Integer.toString(P.V + 1) + ": NO\n");
                    label6.setTextFill(Color.ORANGE);
                    label6.setLayoutX(0);
                    label6.setLayoutY(0);
                    pane1.getChildren().add(label6);
                    cycle = false;
                }
                Vector<Integer> path = new Vector<>();
                if (P.V != -1) {
                    for (int cur = P.V; cur != -1; cur = P.road.elementAt(cur)) {
                        path.add(cur);
                    }
                    addRed(path);
                    path.clear();
                }
                path.clear();
                P.ways.clear();
                P.road.clear();
                fordInWork = false;
                last = false;
                counter = 0;

            }
        }
    }

    private void cycleFord() {
        if (fordInWork && !cycle) {
            if (!last) {
                if (counter < P.m)
                    stepSearchAlgorithm();
                else {
                    relaxCounter = 0;
                    counter = 0;
                    cycle = true;
                }
            }
        }
        if (fordInWork && cycle) {
            last = true;
            if (counter < P.m) {
                stepSearchAlgorithm();
                cycle = true;
                last = false;
            } else {
                last = relaxCounter == 0;
                relaxCounter = 0;
                counter = 0;
                cycle = false;
            }
        }
    }

    private void stepSearchAlgorithm() {

        if (P.ways.elementAt(P.list.elementAt(counter).from) < inf) {
            if ((P.ways.elementAt(P.list.elementAt(counter).from) + P.list.elementAt(counter).l) < P.ways.elementAt(P.list.elementAt(counter).to)) {
                P.ways.set(P.list.elementAt(counter).to, (P.ways.elementAt(P.list.elementAt(counter).from) + P.list.elementAt(counter).l));
                P.road.set(P.list.elementAt(counter).to, P.list.elementAt(counter).from);

                Line q3 = new Line(P.visual.elementAt(P.list.elementAt(counter).from).x, P.visual.elementAt(P.list.elementAt(counter).from).y - 9, P.visual.elementAt(P.list.elementAt(counter).to).x, P.visual.elementAt(P.list.elementAt(counter).to).y - 9);
                q3.setStrokeWidth(1);
                q3.setStroke(Color.GREEN);
                pane1.getChildren().add(q3);
                last = false;
                relaxCounter++;
                cycle = false;
            } else {
                Line q1 = new Line(P.visual.elementAt(P.list.elementAt(counter).from).x, P.visual.elementAt(P.list.elementAt(counter).from).y - 9, P.visual.elementAt(P.list.elementAt(counter).to).x, P.visual.elementAt(P.list.elementAt(counter).to).y - 9);
                q1.setStrokeWidth(1);
                q1.setStroke(Color.BLUE);
                pane1.getChildren().add(q1);
                cycle = false;
            }
        }
        else {
            Line q3 = new Line(P.visual.elementAt(P.list.elementAt(counter).from).x, P.visual.elementAt(P.list.elementAt(counter).from).y - 9, P.visual.elementAt(P.list.elementAt(counter).to).x, P.visual.elementAt(P.list.elementAt(counter).to).y - 9);
            q3.setStrokeWidth(1);
            q3.setStroke(Color.ORANGE);
            pane1.getChildren().add(q3);
            cycle = false;
        }
        counter++;
    }
}
