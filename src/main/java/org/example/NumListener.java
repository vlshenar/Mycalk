package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Вместе с кнопками - объектами класса JButton
 * объект этого класса реализует компонент Контроллер из
 * шаблона проектирования MVC
 */

public class NumListener implements ActionListener {
    private RezultViewer worklink = null;
    public NumListener(RezultViewer r){
        worklink = r;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String calcCommand = e.getActionCommand();
        if (calcCommand.equals("+"))
            Numcore.operateNum("a");
        else if (calcCommand.equals("-"))
            Numcore.operateNum("s");
        else if (calcCommand.equals("*"))
            Numcore.operateNum("m");
        else if (calcCommand.equals("/"))
            Numcore.operateNum("d");
        else if (calcCommand.equals("="))
            Numcore.operateNum(calcCommand);
        else if (calcCommand.equals("c"))
            Numcore.cidereNum();
        else if (calcCommand.equals("sqr") || calcCommand.equals("sqrt") || calcCommand.equals("+/-"))
            Numcore.singletermcalcNum(calcCommand);
        else
            Numcore.insertTerm(calcCommand);

        worklink.setText(Numcore.getAnswer());
    }
}
