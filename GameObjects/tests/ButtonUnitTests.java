package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import objects.Button;

class ButtonUnitTests {

    @Test
    public void testDefaultButton(){
        Button button1 = new Button();
        Button button2 = new Button();
        assert(button1.getLabel().equals("button01"));
        assert(button2.getLabel().equals("button02"));
        assert(button1.getIcon().equals("default_gamepiece_icon.jpg"));
        assert(button1.getColor().equals(Color.WHITE));
        assert(button1.getText().equals("type text here"));
        assert(button1.getEnabled() == true);
        assert(button1.getPressed() == false);
    }

    @Test
    public void testButtonEnabled(){
        Button button = new Button();
        button.setEnabled(false);
        assert(button.getEnabled() == false);
        button.toggleEnabled();
        assert(button.getEnabled() == true);
    }

    @Test
    public void testButtonPressed(){
        Button button = new Button();
        button.setPressed(true);
        assert(button.getPressed() == true);
        button.togglePressed();
        assert(button.getPressed() == false);
    }

    @Test
    public void testButtonText(){
        Button button = new Button();
        button.setText("STOP");
        assert(button.getText().equals("STOP"));
        button.setText("START");
        assert(button.getText().equals("START"));
    }

    //TODO:
    //Clarify intended relation between "pressed" and "enabled", then test as needed
}