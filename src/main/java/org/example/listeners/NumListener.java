package org.example.listeners;

import org.example.calcview.ResultViewer;
import org.example.calcmodel.Numcore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Вместе с кнопками - объектами класса JButton
 * объект этого класса реализует компонент Контроллер из
 * шаблона проектирования MVC
 * слушает нажатие на кнопку с цифрой
 */

public class NumListener implements ActionListener {
    private final ResultViewer workLink;
    private final Numcore numcore;

    //конструктор
    public NumListener(ResultViewer workLink, Numcore numcore){
        this.workLink = workLink;
        this.numcore = numcore;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String calcCommand = e.getActionCommand();

        numcore.insertTerm(calcCommand);

        workLink.setText(numcore.getAnswer());
    }
}
