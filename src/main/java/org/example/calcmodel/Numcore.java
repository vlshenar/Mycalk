package org.example.calcmodel;

/**
 * В соответствии с шаблоном MVC в текущем классе реализована модель
 * в отвлечении от контроллера и представления
 */
public class Numcore {
    /*
     * если значение в поле answer равно значению в этом поле, то происходит замещение
     * значения в поле answer
     * также, процесс обнуления калькулятора использует это значение
     */
    private final String default_val = "0";

    // поле answer сочетает функции первого компонента вычисления и
    // приемника результата
    private String answer = "0";

    // компонент высокого приоритета
    private float highPriorityTerm = 0F;

    // компонент низкого приоритета
    private float lowPriorityTerm = 0F;


    // первый компонент
    private float firstTerm = 0F;

    // флаг нового компонента:
    // если true, то начать вводить новое число
    private static boolean newterm = true;

    //массив объектов-операторов
    private MathOperator[] operators;

    //конструктор
    public Numcore() {
        operators = new MathOperator[4];
        operators[0] = new Add('+');
        operators[1] = new Substruct('-');
        operators[2] = new Multiply('*');
        operators[3] = new Divide('/');
    }

    /*
     * команды высокого приоритета: такие команды всегда выполняются первыми относительно
     * команд низкого приоритета
     * значения поля: n - отсутствие действия
     * * - умножение
     * / - деление
     */
    private char highPriorityCommand = 'n';

    /*
     * команды низкого приоритета
     * значения поля:
     * n - нет действия
     * + - сложение
     * - - вычитание
     */
    private char lowPriorityCommand = 'n';

    // получение ответа
    public String getAnswer() {
        return answer;
    }


    // Ввод числа для последующих вычислений
    public void insertTerm(String s) {
        if (newterm) {
            answer = default_val;   //на случай, если ввод начинается с точки
            if (s.equals("."))
                answer += s;
            else answer = s;
            newterm = false;
        } else {
            if (answer.equals(default_val) && !s.equals("."))
                answer = s;
            else if (answer.contains(".") && s.equals("."))
                answer += "";
            else
                answer += s;
        }
    }

    private void calculate(char action) {
        int i = 0;
        while (action != operators[i].mathAction) i++;
        operators[i].operate();
    }

    //выводит в строку ответа указанное значение
    private void toAnswer(float result) {
        String ans;
        //checkFloatPoint нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int checkFloatPoint;
        ans = Float.toString(result);
        checkFloatPoint = (int) ((result - (int) result) * 10000000);
        if (checkFloatPoint == 0)
            ans = ans.replace(".0", "");
        answer = ans;
    }


    //умножение, деление, промежуточные ответы
    public void highPriorityOperate(char action) {
        if (highPriorityCommand != 'n') {
            calculate(highPriorityCommand);
            toAnswer(lowPriorityTerm);
            highPriorityCommand = action;
            highPriorityTerm = Float.parseFloat(answer);
        } else {
            lowPriorityTerm = Float.parseFloat(answer);
            highPriorityCommand = action;
        }
        newterm = true;
    }

    //сложение, вычитание, промежуточные ответы
    public void lowPriorityOperate(char action) {
        if(highPriorityCommand != 'n') calculate(highPriorityCommand);
        if(lowPriorityCommand != 'n') calculate(lowPriorityCommand);
        lowPriorityCommand = action;
        highPriorityCommand = 'n';
        firstTerm = Float.parseFloat(answer);
        toAnswer(firstTerm);
        newterm = true;
    }

    //окончательный ответ, завершение процесса вычисления
    public void resultOperate() {
        float tmpValue = Float.parseFloat(answer);
        if(highPriorityCommand != 'n') {
            highPriorityTerm = tmpValue;
            calculate(highPriorityCommand);
        }
        else lowPriorityTerm = tmpValue;

        if(lowPriorityCommand != 'n') calculate(lowPriorityCommand);
        toAnswer(firstTerm);
        highPriorityCommand = lowPriorityCommand = 'n';
        firstTerm = lowPriorityTerm = highPriorityTerm = 0.0F;
        newterm = true;
    }


    /*
     * cidereNum - обнуляет компоненту answer, если она не равна default_val
     * обнуляет все компоненты и переводит current_command в 1 если равна
     */
    public void cidereNum() {
        if (answer.equals(default_val)) {
            highPriorityTerm = 0.0F;
            lowPriorityTerm = 0.0F;
            highPriorityCommand = 'n';
            lowPriorityCommand = 'n';
        } else
            answer = default_val;
    }

    //Возведение в квадрат, извлечение корня и изменение знака числа
    public void singletermcalcNum(String command) {
        int chkpt;
        float term = Float.parseFloat(answer);
        float tmp = 0F;
        if (command.equals("sqr"))
            tmp = term * term;
        else if (command.equals("sqrt"))
            tmp = (float) Math.sqrt(term);
        else if (command.equals("+/-"))
            tmp = (-1) * term;
        String ans = Float.toString(tmp);
        chkpt = (int) ((tmp - (int) tmp) * 10000000);
        if (chkpt == 0)
            ans = ans.replace(".0", "");
        answer = ans;
    }

    //внутренние классы арифметических действий
    //сложение
    private class Add extends MathOperator {

        protected Add(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm += lowPriorityTerm;
        }
    }

    //вычитание
    private class Substruct extends MathOperator {

        protected Substruct(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm -= lowPriorityTerm;
        }
    }

    //умножение
    private class Multiply extends MathOperator {

        protected Multiply(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm *= lowPriorityTerm;
        }
    }

    //деление
    private class Divide extends MathOperator {

        protected Divide(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm /= lowPriorityTerm;
        }
    }

}
