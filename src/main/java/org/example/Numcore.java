package org.example;
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
    private static final String default_val = "0";

    /*
     * поле answer сочетает функции первого компонента вычисления и
     * приемника результата
     */
    private static String answer = "0";

    /*
     * компонент высокого приоритета
     */
    private static float high_priority_term = 0F;

    /*
     * компонент низкого приоритета
     */
    private static float low_priority_term = 0F;

    /*
     * дублирует answer где требуется промежуточный ответ типа float
     * позволяет избежать лишних преобразований
     */
    private static float temporare_value = 0F;

    /*
     * флаг нового компонента:
     * если true, то начать вводить новое число
     */
    private static boolean newterm = true;

    /*
     * получение ответа
     */
    public static String getAnswer() {
        return answer;
    }

    /*
     * команды высокого приоритета: такие команды всегда выполняются первыми относительно
     * команд низкого приоритета
     * значения поля: n - отсутствие действия
     * m - умножение
     * d - деление
     */
    private static String high_priority_command = "n";

    /*
     * команды низкого приоритета
     * значения поля:
     * n - нет действия
     * a - сложение
     * s - вычитание
     */
    private static String low_priority_command = "n";

    /*
     * Ввод числа для последующих вычислений
     */
    public static void insertTerm(String s) {
        if (newterm) {
            answer = default_val;   //на случай, если ввод начинается с точки
            if (s.equals("."))
                answer += s;
            else answer = s;
            newterm = false;
        }
        else {
            if (answer.equals(default_val) && !s.equals("."))
                answer = s;
            else if (answer.contains(".") && s.equals("."))
                 answer += "";
            else
                answer += s;
        }
    }

    /*
     * Метод для непосредственного вычисления
     * вызывается из метода operateNum
     */
    private static String calcNum(float term1, float term2, String current_command) {
        String ans;
        float result = 0.0F;
        //checkFloatPoint нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int checkFloatPoint;
        if (current_command.equals("a")) {
            result = term1 + term2;
        } else if (current_command.equals("s")) {
            result = term1 - term2;
        } else if (current_command.equals("m")){
            result = term1 * term2;
        } else if (current_command.equals("d")) {
            result = term1 / term2;
        }
        temporare_value = result;
        ans = Float.toString(result);
        checkFloatPoint = (int) ((result - (int) result) * 10000000);
        if (checkFloatPoint == 0)
            ans = ans.replace(".0", "");
        return ans;
    }


    /*
     * Вычиление
     * Происходит в 2 этапа:
     * сначала обозначается действие и выполняется инициация поля term
     * затем при повторном вызове происходит само вычисление
     */

    public static void operateNum(String command){
        if (command.equals("m") || command.equals("d"))
        {
            if (high_priority_command.equals("n") && low_priority_command.equals("n"))
            {
                high_priority_term = Float.parseFloat(answer);
                high_priority_command = command;
            }
            else if (high_priority_command.equals("n"))
            {
                low_priority_term = high_priority_term;
                high_priority_term = Float.parseFloat(answer);
                high_priority_command = command;
            }
            else if (low_priority_command.equals("n"))
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
                high_priority_term = temporare_value;
                high_priority_command = command;
            }
            else
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
                high_priority_term = temporare_value;
                high_priority_command = command;
            }
        }
        else if (command.equals("a") || command.equals("s"))
        {
            if (high_priority_command.equals("n") && low_priority_command.equals("n"))
            {
                high_priority_term = Float.parseFloat(answer);
                low_priority_command = command;
            }
            else if (high_priority_command.equals("n"))
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), low_priority_command);
                high_priority_term = temporare_value;
                low_priority_command = command;
            }
            else if (low_priority_command.equals("n"))
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
                high_priority_term = temporare_value;
                low_priority_command = command;
                high_priority_command = "n";
            }
            else
            {
                Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
                high_priority_term = temporare_value;
                answer = Numcore.calcNum(low_priority_term, high_priority_term, low_priority_command);
                high_priority_term = temporare_value;
                low_priority_term = 0.0F;
                high_priority_command = "n";
                low_priority_command = command;
            }
        }
        else
        {
            if (high_priority_command.equals("n") && low_priority_command.equals("n"))
            {
                return;
            }
            else if (high_priority_command.equals("n"))
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), low_priority_command);
            }
            else if (low_priority_command.equals("n"))
            {
                answer = Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
            }
            else
            {
                Numcore.calcNum(high_priority_term, Float.parseFloat(answer), high_priority_command);
                high_priority_term = temporare_value;
                answer = Numcore.calcNum(low_priority_term, high_priority_term, low_priority_command);
            }
            high_priority_term = 0.0F;
            low_priority_term = 0.0F;
            high_priority_command = "n";
            low_priority_command = "n";
        }
        newterm = true;
    }

    /*
     * cidereNum - обнуляет компоненту answer, если она не равна default_val
     * обнуляет все компоненты и переводит current_command в 1 если равна
     */
    public static void cidereNum(){
        if (answer.equals(default_val)){
            high_priority_term = 0.0F;
            low_priority_term = 0.0F;
            high_priority_command = "n";
            low_priority_command = "n";
        } else
            answer = default_val;
    }

    /*
     * Возведение в квадрат, извлечение корня и изменение знака числа
     */
    public static void singletermcalcNum(String command){
        int chkpt;
        float term = Float.parseFloat(answer);
        float tmp = 0F;
        if (command.equals("sqr"))
            tmp = term*term;
        else if (command.equals("sqrt"))
            tmp = (float)Math.sqrt(term);
        else if (command.equals("+/-"))
            tmp = (-1)*term;
        String ans = Float.toString(tmp);
        chkpt = (int) ((tmp - (int) tmp) * 10000000);
        if (chkpt == 0)
            ans = ans.replace(".0", "");
        answer = ans;
    }
}
