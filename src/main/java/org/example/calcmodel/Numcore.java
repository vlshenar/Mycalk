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

    // первый операнд
    private float firstTerm = 0F;

    // второй операнд
    private float secondTerm = 0F;

    // флаг нового компонента:
    // если true, то начать вводить новое число
    private static boolean newTerm = true;

    //текущее арифметическое действие, ожидающее исполнения
    private char currentCommand = 'n';

    //поле микростека для выбора порядка действий
    private LowPriorityStack stack;

    //массив объектов-операторов
    private MathOperator[] operators;

    //конструктор
    public Numcore() {
        stack = new LowPriorityStack();
        operators = new MathOperator[5];
        operators[0] = new Add('+');
        operators[1] = new Substruct('-');
        operators[2] = new Multiply('*');
        operators[3] = new Divide('/');
        operators[4] = new NoneAction('n');
    }

    // получение ответа
    public String getAnswer() {
        return answer;
    }

    // Ввод числа для последующих вычислений
    public void insertTerm(String s) {
        if (newTerm) {
            answer = default_val;   //на случай, если ввод начинается с точки
            if (s.equals("."))
                answer += s;
            else answer = s;
            newTerm = false;
        } else {
            if (answer.equals(default_val) && !s.equals("."))
                answer = s;
            else if (answer.contains(".") && s.equals("."))
                answer += "";
            else
                answer += s;
        }
    }

    //выполнение указанного арифметического действия
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

    //выполняет действия высокого приоритета: умножение и деление
    public void highPriorityOperate(char action) {
        stack.push();
        if (currentCommand == '*' || currentCommand == '/') {
            secondTerm = Float.parseFloat(answer);
            calculate(currentCommand);
        } else firstTerm = Float.parseFloat(answer);
        currentCommand = action;
        toAnswer(firstTerm);
        newTerm = true;
    }

    //выполняет действия низкого приоритета: сложение и вычитание
    public void lowPriorityOperate(char action) {
        if (currentCommand == 'n') {
            firstTerm = Float.parseFloat(answer);
        } else {
            secondTerm = Float.parseFloat(answer);
            calculate(currentCommand);
            if (stack.pushed) {
                stack.pop();
                calculate(currentCommand);
            }
        }
        toAnswer(firstTerm);
        currentCommand = action;
        newTerm = true;
    }

    //окончательный ответ, завершение процесса вычисления
    public void resultOperate() {
        secondTerm = Float.parseFloat(answer);
        calculate(currentCommand);
        if (stack.pushed) {
            stack.pop();
            calculate(currentCommand);
        }
        toAnswer(firstTerm);
        currentCommand = 'n';
        newTerm = true;
    }

    // cidereNum - обнуляет компоненту answer, если она не равна default_val
    // обнуляет все компоненты и переводит current_command в 1 если равна
    public void cidereNum() {
        if (answer.equals(default_val)) {
            secondTerm = 0.0F;
            firstTerm = 0.0F;
            currentCommand = 'n';
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
            firstTerm += secondTerm;
        }
    }

    //вычитание
    private class Substruct extends MathOperator {

        protected Substruct(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm -= secondTerm;
        }
    }

    //умножение
    private class Multiply extends MathOperator {

        protected Multiply(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm *= secondTerm;

        }
    }

    //деление
    private class Divide extends MathOperator {

        protected Divide(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            firstTerm /= secondTerm;
        }
    }

    //оператор бездействия
    private class NoneAction extends MathOperator {

        protected NoneAction(char mathAction) {
            super(mathAction);
        }

        @Override
        public void operate() {
            //none action
        }
    }

    //микростек принимает операнд и действие низкого приоритета
    private class LowPriorityStack {

        private float term;
        private char action;
        private boolean pushed = false;

        void push() {
            if ((currentCommand == '+' || currentCommand == '-') && !pushed) {
                term = firstTerm;
                firstTerm = secondTerm;
                action = currentCommand;
                pushed = true;
            }
        }

        void pop() {
            if (pushed) {
                secondTerm = firstTerm;
                firstTerm = term;
                currentCommand = action;
                pushed = false;
            }
        }

    }
}
