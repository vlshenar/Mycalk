package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Study project
 *
 */
public class Calcmain
{
    public static void main( String[] args )
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalcFrame frame = new CalcFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
    });
    }
}
