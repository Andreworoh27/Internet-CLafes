package component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonBuilder {
    private Button button;
    private String label = "Button";
    private String bgColor = "grey";
    private String fontColor = "white";
    private String fontSize = "22";

    public ButtonBuilder() {
        button = new Button(label);
        button.setStyle("-fx-background-color: "+bgColor+"; -fx-text-fill: "+fontColor+"; -fx-font: "+fontSize+" arial;");
    }

    public ButtonBuilder setText(String label) {
    	this.label = label;
        this.button.setText(label);
        return this;
    }

    public ButtonBuilder setColor(String bgColor) {
    	this.bgColor = bgColor;
        button.setStyle("-fx-background-color: "+bgColor+"; -fx-text-fill: "+fontColor+"; -fx-font: "+fontSize+" arial;");
        return this;
    }

    public ButtonBuilder setFontSize(String fontSize) {
    	this.fontSize = fontSize;
        button.setStyle("-fx-background-color: "+bgColor+"; -fx-text-fill: "+fontColor+"; -fx-font: "+fontSize+" arial;");
        return this;
    }
    
    public ButtonBuilder setFontColor(String fontColor) {
    	this.fontColor = fontColor;
        button.setStyle("-fx-background-color: "+bgColor+"; -fx-text-fill: "+fontColor+"; -fx-font: "+fontSize+" arial;");
        return this;
    }

    public ButtonBuilder setPadding(int padding) {
        this.button.setPadding(new Insets(padding));
        return this;
    }
    
    public Button build() {
    	Button result = button;
        reset();
        return result;
    }

    private void reset() {
    	label = "Button";
        bgColor = "grey";
        fontColor = "white";
        fontSize = "22";
        button = new Button(label);
        button.setStyle("-fx-background-color: "+bgColor+"; -fx-text-fill: "+fontColor+"; -fx-font: "+fontSize+" arial;");
    }
}