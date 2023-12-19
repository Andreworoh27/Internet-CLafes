package component;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;

/*
 * Builder to built text field
 * */

public class TextFieldBuilder {
	
    private TextField textField;
    private String promptText = "Enter text";
    private int prefWidth = 200;

    public TextFieldBuilder() {
        textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefWidth(prefWidth);
    }

    public TextFieldBuilder setPromptText(String promptText) {
        this.promptText = promptText;
        textField.setPromptText(promptText);
        return this;
    }

    public TextFieldBuilder setPrefWidth(int prefWidth) {
        this.prefWidth = prefWidth;
        textField.setPrefWidth(prefWidth);
        return this;
    }

    public TextFieldBuilder setPadding(Insets insets) {
        this.textField.setPadding(insets);
        return this;
    }

    public TextField build() {
    	TextField result = textField;
    	reset();
    	return result;
    }

    public void reset() {
    	promptText = "Enter text";
    	prefWidth = 200;
        this.textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefWidth(prefWidth);
    }

}
