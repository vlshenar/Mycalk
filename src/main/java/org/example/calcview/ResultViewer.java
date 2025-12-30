package org.example.calcview;
import javax.swing.*;
import java.awt.*;

/**
 * Реализация компонента Представление из шаблона проектирования
 * MVC
 */

public class ResultViewer extends JLabel {
    // show calculation result

    public ResultViewer(String text) {
        super(text);
        //установка размера цифр на табло
        this.setFont(new Font("Serif", Font.PLAIN, 46));
    }

    public void setText(String s){
        super.setText(s);
    }
}
