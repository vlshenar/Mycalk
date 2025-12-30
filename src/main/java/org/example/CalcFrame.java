package org.example;

import org.example.calcmodel.Numcore;
import org.example.calcview.ResultViewer;
import org.example.listeners.NumListener;
import org.example.listeners.OperateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Тело калькулятора, содержащее кнопки
 * и экран вывода результата, содержит
 * в себе элементы представления и контроллера
 */
public class CalcFrame extends JFrame {

    private JPanel buttonPanel;
    private JPanel viewPanel;
    private NumListener listener;
    private OperateListener operateListener;
    private ResultViewer viewer;
    private final int CALC_WIDTH = 600;
    private final int CALC_HEIGHT = 500;
    private Font buttonFont;

    public CalcFrame(){
        /*
         * Создание панели кнопок и панели экрана вывода
         * а также слушателя кнопок
         * слушатель также получает ссылку на объект экрана вывода
         * при своем создании
         */
        Numcore numcore = new Numcore();
        buttonPanel = new JPanel();
        viewPanel = new JPanel();
        viewer = new ResultViewer("0");
        listener = new NumListener(viewer, numcore);
        operateListener = new OperateListener(viewer, numcore);
        buttonFont = new Font("Serif", Font.BOLD, 22);

        /*
         *создание кнопок и окна вывода ответа
         * регистрация слушателя кнопок и добавление их в контейнер
         * методом addButton
         */
        viewPanel.add(viewer);
        this.add(viewPanel, BorderLayout.NORTH);
        buttonPanel.setLayout(new GridLayout(4, 5));
        addButton("+/-", operateListener);
        addButton("7", listener);
        addButton("8", listener);
        addButton("9", listener);
        addButton("/", operateListener);

        addButton("sqr", operateListener);
        addButton("4", listener);
        addButton("5", listener);
        addButton("6", listener);
        addButton("*", operateListener);

        addButton("sqrt", operateListener);
        addButton("1", listener);
        addButton("2", listener);
        addButton("3", listener);
        addButton("-", operateListener);

        addButton("c", operateListener);
        addButton("0", listener);
        addButton(".", listener);
        addButton("=", operateListener);
        addButton("+", operateListener);

        this.add(buttonPanel, BorderLayout.CENTER);

         // установление размера окна
        setSize(CALC_WIDTH, CALC_HEIGHT);
    }


     // вводит кнопки, регистрирует слушателя и добавляет их в панель buttonPanel
    private void addButton(String label, ActionListener listener){
        JButton button = new JButton(label);
        button.setFont(buttonFont);
        button.addActionListener(listener);
        buttonPanel.add(button);
    }
}
