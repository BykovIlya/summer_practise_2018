package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * A class for handling events of the elements of the main window.
 * */

public class Controller {
    private Alert alert;
    @FXML
    private TextField textField1;
    @FXML
    private TextArea amountVertex;
    @FXML
    private TextArea amountEdges;
    @FXML
    public TextArea result;
    @FXML
    private TextArea textGraph;
    @FXML
    private Label text3;
    @FXML
    private Label text4;
    @FXML
    private Label text5;
    @FXML
    private Label text6;
    @FXML
    private Label text7;
    @FXML
    private Button next;
    @FXML
    private Button fileGraph;
    @FXML
    private Button generateGraph;
    @FXML
    private Button begAlg;
    @FXML
    private Button back;
    @FXML
    private Button graphWay;
    @FXML
    private Label vertex;
    @FXML
    public TextArea graphAlg;
    @FXML
    private TextArea graphV;


    private static int k;


    static Graph P = new Graph();
    private GraphController graphWindow;
    private static boolean Setting = true;

    /**
     *
     *      Event processing function "Generating a random graph".
     *      Checks the correct filling of the fields "Number of vertices" and "Number of edges".
     *      Handles the "Generate Count" key.
     */
    @FXML
    public void generateGraph() {
        if (amountVertex.getText() == null || amountVertex.getText().length() == 0) {
            error("Enter the number of vertices");
            int x = Integer.parseInt(amountVertex.getText());
        } else {
            if (amountEdges.getText() == null || amountEdges.getText().length() == 0) {
                error("Enter the number of edges");
                int y = Integer.parseInt(amountEdges.getText());
            }
            try {
                int x = Integer.parseInt(amountVertex.getText());
                int y = Integer.parseInt(amountEdges.getText());
                if (x > 30 || x < 0 || y > 10 || y < 0) {
                    error("The graph can not be displayed");
                } else {
                    P.list.clear();
                    P.ways.clear();
                    P.road.clear();
                    k = 0;
                    P.n = x;
                    P.m = y;
                    P.V=-1;
                    textGraph.clear();
                    P.inputGeneration();
                    for (int i = 0; i < P.list.size(); i++) {
                        textGraph.appendText("Way from the top " + (P.list.elementAt(i).from + 1) + " to the top " + (P.list.elementAt(i).to + 1) + ": " + P.list.elementAt(i).l + "\n");
                    }
                    Stage stageWindow = new Stage();
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                error("An incorrect value was entered in one of the fields");
            }
        }
    }


    /**
     * Function for outputting errors when filling in fields.
     *
     * @param s A string that tells you what error is committed when filling out the fields.
     */
    private void error(String s) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    /**
     * The function of starting the algorithm for finding the most granular paths of the graph.
     */
    private void beginAlgorithm() {
        k = 1;
        P.searchAlgorithm(this);
        P.outputWays(this);
    }

    /**
     * The function of processing the keystroke "Start the algorithm".
     */
    @FXML
    public void workBegin() {
        if (P.n == 0) {
            error("Graph not generated");
        } else {
            if (textField1.getText() == null || textField1.getText().length() == 0) {
                error("Enter the vertex");
            } else {
                try {
                    int m = Integer.parseInt(textField1.getText());
                    P.v = m;
                    if (P.v > P.n || P.v <= 0)
                        error("The vertex is not set correctly");
                    else beginAlgorithm();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    error("Incorrect value of the vertex");
                }
            }
        }
    }


    /**
     *
     * The function of processing the keystroke "Read graph from file".
     *
     * @throws IOException
     */
    @FXML
    public void fileGeneration ()  {
        k = 0;
        P.list.clear();
        P.ways.clear();
        P.road.clear();
        P.V=-1;
        textGraph.clear();
        P.inputFile();
        for (int i = 0; i < P.list.size(); i++) {
            textGraph.appendText("The way from the top " + (P.list.elementAt(i).from + 1) + " to the top " + (P.list.elementAt(i).to + 1) + ": " + P.list.elementAt(i).l + "\n");
        }
        Stage stageWindow = new Stage();
    }

    /**
     * Function of calling the child window to draw the graph.
     */
    private void FXMLDocumentController(Stage stageWindow) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI_prototype.fxml"));
        stageWindow.setTitle("Graph");
        Scene scene = new Scene(root);
        stageWindow.setScene(scene);
        stageWindow.show();
    }

    /**
     * The function to display the components of the main panel when you press the "Visualize the algorithm" button.
     */
    @FXML
    public void nextMenu() {
        if (k == 0)
            error("Algorithm did not start its work");
        else {
            next.setVisible(false);
            text3.setVisible(false);
            text4.setVisible(false);
            text5.setVisible(false);
            text6.setVisible(false);
            text7.setVisible(true);
            amountEdges.setVisible(false);
            amountVertex.setVisible(false);
            textField1.setVisible(false);
            begAlg.setVisible(false);
            generateGraph.setVisible(false);
            fileGraph.setVisible(false);
            back.setVisible(true);
            graphWay.setVisible(true);
            vertex.setVisible(true);
            graphAlg.setVisible(true);
            graphV.setVisible(true);
            Setting = false;
        }
    }

    /**
     * The function returns to the previous menu when the algorithm is visualized.
     */
    @FXML
    public void backMenu() {
        next.setVisible(true);
        text3.setVisible(true);
        text4.setVisible(true);
        text5.setVisible(true);
        text6.setVisible(true);
        amountEdges.setVisible(true);
        amountVertex.setVisible(true);
        textField1.setVisible(true);
        begAlg.setVisible(true);
        generateGraph.setVisible(true);
        fileGraph.setVisible(true);
        back.setVisible(false);
        graphWay.setVisible(false);
        vertex.setVisible(false);
        graphAlg.setVisible(false);
        graphV.setVisible(false);
        Setting = true;
    }

    /**
     * Function of output of the shortest path to a given vertex.
     */
    @FXML
    public void getWay() {
        if (graphV.getText() == null || graphV.getText().length() == 0) {
            error("Enter the number of vertices");
        } else {

            try {
                int x = Integer.parseInt(graphV.getText());
                x--;
                P.V = x;
                if (x + 1 > P.n || x + 1 < 0 || x == (P.v)) {//?

                    error("The graph can not be displayed");
                } else {
                    Stage stageWindow = new Stage();
                    try {
                        FXMLDocumentController(stageWindow);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                error("Incorrect value of the final vertex is entered.");
            }
        }
    }
}