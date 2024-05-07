package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalcFrame extends JFrame {
   private JPanel buttonpanel;
   private JPanel viewpanel;
   private  JPanel ciderepanel;
   private NumListener listener;
    private RezultViewer viewer;
    private int CALC_WIDTH = 400;
    private int CALC_HEIGHT = 300;
    public CalcFrame(){
        /**
         * Создание панели кнопок и панели экрана вывода
         * а также слушателя кнопок
         * слушатель также получает ссылку на объект экрана вывода
         * при своем создании
         */
        buttonpanel = new JPanel();
        viewpanel = new JPanel();
        viewer = new RezultViewer("0");
        listener = new NumListener(viewer);
        /**
         *создание кнопок и окна вывода ответа
         * регистрация слушателя кнопок и добавление их в контейнер
         * методом addButton
         */
        viewpanel.add(viewer);
        this.add(viewpanel, BorderLayout.NORTH);
        buttonpanel.setLayout(new GridLayout(4, 5));
        addButton("+/-", listener);
        addButton("7", listener);
        addButton("8", listener);
        addButton("9", listener);
        addButton("/", listener);

        addButton("sqr", listener);
        addButton("4", listener);
        addButton("5", listener);
        addButton("6", listener);
        addButton("*", listener);

        addButton("sqrt", listener);
        addButton("1", listener);
        addButton("2", listener);
        addButton("3", listener);
        addButton("-", listener);

        addButton("c", listener);
        addButton("0", listener);
        addButton(".", listener);
        addButton("=", listener);
        addButton("+", listener);

        this.add(buttonpanel, BorderLayout.CENTER);


        /**
         * установление размера окна
         */
        setSize(CALC_WIDTH, CALC_HEIGHT);
    }

    /**
     * вводит кнопки, регистрирует слушателя и добавляет их в панель buttonpanel
     */
    private void addButton(String label, ActionListener listener){
        JButton button = new JButton(label);
        button.addActionListener(listener);
        buttonpanel.add(button);
    }
}
