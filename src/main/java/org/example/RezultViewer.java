package org.example;
import javax.swing.*;

/**
 * Реализация компонента Представление из шаблона проектирования
 * MVC
 */

public class RezultViewer extends JLabel {
    // show calculation result

    public RezultViewer(String text) {
        super(text);
    }

    public void setText(String s){
        super.setText(s);
    }
}
