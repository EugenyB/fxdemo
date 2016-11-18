package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by eugeny on 18.11.2016.
 */
public class Diagram {
    private double[] values;
    public final static String[] names = {"Зима","Весна","Лето","Осень"};

    public Diagram(double[] values) {
        this.values = values;
    }

    public void draw(Canvas canvas) {
        if (values==null || values.length==0) {
            return;
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(50, 50, canvas.getWidth()-100, canvas.getHeight()-100);
    }
}
