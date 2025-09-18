package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Вместе с кнопками - объектами класса JButton
 * объект этого класса реализует компонент Контроллер из
 * шаблона проектирования MVC
 * слушает нажатие на кнопку с цифрой
 */

public class NumListener implements ActionListener {
    private RezultViewer workLink = null;

    //конструктор
    public NumListener(RezultViewer workLink){
        this.workLink = workLink;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String calcCommand = e.getActionCommand();

        Numcore.insertTerm(calcCommand);

        workLink.setText(Numcore.getAnswer());
    }
}
