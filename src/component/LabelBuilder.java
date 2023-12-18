package component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelBuilder {
	
    private Label label;
    private String text = "Label";
    private String textColor = "black";
    private String fontSize = "12";

    public LabelBuilder() {
        label = new Label(text);
        label.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: " + fontSize + "px;");
    }

    public LabelBuilder setText(String text) {
        this.text = text;
        label.setText(text);
        return this;
    }

    public LabelBuilder setTextColor(String textColor) {
        this.textColor = textColor;
        label.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: " + fontSize + "px;");
        return this;
    }

    public LabelBuilder setFontSize(String fontSize) {
        this.fontSize = fontSize;
        label.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: " + fontSize + "px;");
        return this;
    }

    public LabelBuilder setPadding(Insets insets) {
        this.label.setPadding(insets);
        return this;
    }

    public Label build() {
        Label result = label;
        reset();
        return result;
    }

    private void reset() {
        text = "Label";
        textColor = "black";
        fontSize = "12";
        label = new Label(text);
        label.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: " + fontSize + "px;");
    }
}