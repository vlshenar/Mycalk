package org.example.listeners;

import org.example.calcview.ResultViewer;
import org.example.calcmodel.Numcore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Вместе с кнопками - объектами класса JButton
 * объект этого класса реализует компонент Контроллер из
 * шаблона проектирования MVC
 * слушает нажатие на кнопку с арифметическим действием
 */

public class OperateListener implements ActionListener {
    private final ResultViewer workLink;
    private final Numcore numcore;

    //конструктор
    public OperateListener(ResultViewer workLink, Numcore numcore) {
        this.workLink = workLink;
        this.numcore = numcore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String calcCommand = e.getActionCommand();
        if (calcCommand.equals("*") || calcCommand.equals("/"))
            numcore.highPriorityOperate(calcCommand.charAt(0));
        else if (calcCommand.equals("+") || calcCommand.equals("-"))
            numcore.lowPriorityOperate(calcCommand.charAt(0));
        else if (calcCommand.equals("="))
            numcore.resultOperate();
        else if (calcCommand.equals("c"))
            numcore.cidereNum();
        else if (calcCommand.equals("sqr") || calcCommand.equals("sqrt") || calcCommand.equals("+/-"))
            numcore.singletermcalcNum(calcCommand);

        workLink.setText(numcore.getAnswer());
    }
}
