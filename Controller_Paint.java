package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.xml.bind.JAXB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller_Paint {

	ListA5Shape A5shapelist = new ListA5Shape();
	
    private enum PenSize {

        SMALL(2),
        MEDIUM(6),
        LARGE(10),
        XLARGE(14);

        final int radius;

        PenSize(int radius) {
            this.radius = radius;
        }

    }

    private enum DrawColor {

        BLACK(Color.BLACK),
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        BROWN(Color.BROWN);

        final Color color;

        DrawColor(Color color) {
            this.color = color;
        }
    }

    private PenSize penSize = PenSize.MEDIUM;
    private DrawColor drawColor = DrawColor.BLACK;

    @FXML
    private Slider slider;
    
    @FXML
    private TextField tf;

    @FXML
    private RadioButton rbBlack;

    @FXML
    private ToggleGroup tgColor;

    @FXML
    private RadioButton rbGreen;

    @FXML
    private RadioButton rbRed;

    @FXML
    private RadioButton rbBlue;

    @FXML
    private RadioButton rbBrown;

    @FXML
    private RadioButton rbSmall;

    @FXML
    private ToggleGroup tgPenSize;

    @FXML
    private RadioButton rbMed;

    @FXML
    private RadioButton rbLarge;

    @FXML
    private RadioButton rbXLarge;

    @FXML
    private Button btnXML;

    @FXML
    private Button btnUndo;

    @FXML
    private Button btnClear;

    @FXML
    private Pane panelDraw;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	
        panelDraw.getChildren().add(new Circle(event.getX(), event.getY(), penSize.radius, drawColor.color));
        A5shapelist.shapelist.add(new A5Shape(event.getX(), event.getY(), penSize.radius));
    }

    @FXML
    void colorChangeEventHandler(ActionEvent event) {
        if (rbBlack.isSelected())
            drawColor = DrawColor.BLACK;
        else if (rbGreen.isSelected())
            drawColor = DrawColor.GREEN;
        else if (rbRed.isSelected())
            drawColor = DrawColor.RED;
        else if (rbBlue.isSelected())
            drawColor = DrawColor.BLUE;
        else
            drawColor = DrawColor.BROWN;
    }

    @FXML
    void penSizeChangeEventHandler(ActionEvent event) {

        if (rbSmall.isSelected())
            penSize = PenSize.SMALL;
        else if (rbMed.isSelected())
            penSize = PenSize.MEDIUM;
        else if (rbLarge.isSelected())
            penSize = PenSize.LARGE;
        else
            penSize = PenSize.XLARGE;
    }

    @FXML
    void XMLButtonEventHandler(ActionEvent event) throws IOException{
    	
    	FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("XML File", "*.xml"));
		fc.setInitialDirectory(new File("."));
		fc.setTitle("Save");
		File selectedFile = fc.showSaveDialog(null);
		Path xml = Paths.get(selectedFile.getName());
		OutputStream os = Files.newOutputStream(xml);  
    	JAXB.marshal(A5shapelist, os);
		os.close();
    }

    @FXML
    void undoButtonEventHandler(ActionEvent event) {

        panelDraw.getChildren().remove(panelDraw.getChildren().size() - 1);
        if (A5shapelist.shapelist.size() > 0) {
            A5shapelist.shapelist.remove(A5shapelist.shapelist.size() - 1); 
        }
    }

    @FXML
    void clearButtonEventHandler(ActionEvent event) {

        panelDraw.getChildren().clear();
        A5shapelist.shapelist.clear();
    }

    @FXML
    void initialize() {   

    	tf.setText(Double.toString(0));
        slider.setValue(0);
        slider.setBlockIncrement(1);
        
    	slider.setMin(0); 
        slider.setMax(255); 
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(3);
        
    	slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            
    		tf.setText(Double.toString(newValue.intValue()));
            String str1 = Integer.toHexString((255 - newValue.intValue()));
            if (str1.length() == 1) { 
            	str1 = String.format("0%s", str1); 
            }
            String str = String.format("-fx-background-color: #%s%sff", str1, str1);
            panelDraw.setStyle(str);
    	});
    	
    	assert rbBlack != null : "fx:id=\"rbBlack\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbBrown != null : "fx:id=\"rbBrown\" was not injected: check your FXML file 'Paint.fxml'.";
        assert tgColor != null : "fx:id=\"tgColor\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbGreen != null : "fx:id=\"rbGreen\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbRed != null : "fx:id=\"rbRed\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbBlue != null : "fx:id=\"rbBlue\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbSmall != null : "fx:id=\"rbSmall\" was not injected: check your FXML file 'Paint.fxml'.";
        assert tgPenSize != null : "fx:id=\"tgPenSize\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbMed != null : "fx:id=\"rbMed\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbLarge != null : "fx:id=\"rbLarge\" was not injected: check your FXML file 'Paint.fxml'.";
        assert rbXLarge != null : "fx:id=\"rbXLarge\" was not injected: check your FXML file 'Paint.fxml'.";
        assert btnXML != null : "fx:id=\"btnXML\" was not injected: check your FXML file 'Paint.fxml'.";
        assert btnUndo != null : "fx:id=\"btnUndo\" was not injected: check your FXML file 'Paint.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Paint.fxml'.";
        assert panelDraw != null : "fx:id=\"panelDraw\" was not injected: check your FXML file 'Paint.fxml'.";
    }
}
