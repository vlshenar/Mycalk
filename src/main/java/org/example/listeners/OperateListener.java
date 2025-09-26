package org.example.listeners;

import org.example.RezultViewer;
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
    private final RezultViewer workLink;
    private final Numcore numcore;

    //конструктор
    public OperateListener(RezultViewer workLink, Numcore numcore) {
        this.workLink = workLink;
        this.numcore = numcore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String calcCommand = e.getActionCommand();
        if (calcCommand.equals("="))
            numcore.resultOperate();
        else if (calcCommand.equals("c"))
            numcore.cidereNum();
        else if (calcCommand.equals("sqr") || calcCommand.equals("sqrt") || calcCommand.equals("+/-"))
            numcore.singletermcalcNum(calcCommand);
        else numcore.Operate(calcCommand.charAt(0));
        workLink.setText(numcore.getAnswer());
    }
}
