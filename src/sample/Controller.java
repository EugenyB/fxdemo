package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField winterField;
    @FXML
    TextField springField;
    @FXML
    TextField summerField;
    @FXML
    TextField fallField;
    @FXML
    Pane pane;
    @FXML
    Canvas canvas;

    TextField[] fields;
    double[] values;
    Diagram diagram;

    public void draw(ActionEvent actionEvent) {
        values = new double[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                TextField field = fields[i];
                field.requestFocus();
                values[i] = Double.parseDouble(field.getText());
            }
            pane.requestFocus();
            draw();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка заполнения данных");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20, 20);
        Diagram diagram = new Diagram(values);
        diagram.draw(canvas);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        canvas.widthProperty().addListener(e -> draw());
        canvas.heightProperty().addListener(e -> draw());
        fields = new TextField[]{winterField, springField, summerField, fallField};
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }
}
