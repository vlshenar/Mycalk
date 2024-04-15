package org.example;
/**
 * В соответствии с шаблоном MVC в текущем классе реализована модель
 * в отвлечении от контроллера и представления
 *
 *
 *
 */
public class Numcore {
    /**
     *если значение в поле answer равно значению в этом поле, то происходит замещение
     * значения в поле answer
     *также, процесс обнуления калькулятора использует это значение
     */
    private static String default_val = "0";

    /**
     * поле answer сочетает функции первого компонента вычисления и
     *приемника результата
     */
    private static String answer = "0";

    /**
     *второй компонент
     */
    private static String term;

    /**
     *поле newterm определяет действия с полем answer: если оно ложно,
     *то сцепление строки с новым символом, если имеет значение "правда"
     *то содержание поля замещается новым символом
     *сразу после такого замещения, поле newterm должно вновь принять значение "ложь"
     */
    private static boolean newterm = false;

    /**
     *текущее математическое действие. Поле может иметь 5 состояний:
     *"+", "-", "*", "/", "empty"
     *последнее означает отсутствие действия
     */
    private static String mathway = "empty";

    /**
     *Отслеживание символа точки в строке компонента вычисления
     *Если значение true, то больше нельзя сцеплять строку с "."
     */
    private static boolean pointfloat = false;
    /**
     * Ввод числа для последующих вычислений
     */
    public static void insertTerm(String s){
        if (default_val.equals(answer) || newterm)
            answer = s;
        else
        {
            if (".".equals(s) && !pointfloat)
            {
                answer = answer + s;
                pointfloat = true;
            }
            else if (".".equals(s) && pointfloat)
            {
                answer += "";
            }
            else
                answer += s;
        }
        newterm = false;
    }

    /**
     * четыре вспомогательных метода для выполнения арифметических
     * действий
     */
    private static String plus(float a, float b){
        String ans;
        //chkpt нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int chkpt;
        float c = a+b;
        ans = Float.toString(c);
        chkpt = (int)((c-(int)c)*10000000);
        if(chkpt == 0)
            ans = ans.replace(".0", "");

        return ans;
    }

    private static String minus(float a, float b){
        String ans;
        //chkpt нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int chkpt;
        float c = a-b;
        ans = Float.toString(c);
        chkpt = (int)((c-(int)c)*10000000);
        if(chkpt == 0)
            ans = ans.replace(".0", "");

        return ans;
    }

    private static String multy(float a, float b){
        String ans;
        //chkpt нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int chkpt;
        float c = a*b;
        ans = Float.toString(c);
        chkpt = (int)((c-(int)c)*10000000);
        if(chkpt == 0)
            ans = ans.replace(".0", "");

        return ans;
    }

    private static String divide(float a, float b){
        String ans;
        //chkpt нужен для проверки необходимости вывода дробной части в ответе
        // если она составляет нуль, то ее можно убрать
        int chkpt;
        float c = a/b;
        ans = Float.toString(c);
        chkpt = (int)((c-(int)c)*10000000);
        if(chkpt == 0)
            ans = ans.replace(".0", "");

        return ans;
    }
    /**
     * Вычиление
     * Происходит в 2 этапа:
     * сначала обозначается действие и выполняется инициация поля term
     * затем при повторном вызове происходит само вычисление
     */
    public static void operateNum(String sigh_action){
        //sigh_action знак действия
        // a, b - операнды
        float a;
        float b;
        if(mathway.equals("empty") && sigh_action.equals("="))
            return;
        else if (mathway.equals("empty") && !sigh_action.equals("="))
        {
            mathway = sigh_action;
            term = answer;
            newterm = true;
            pointfloat = false;
        }
        else {
            a = Float.parseFloat(term); // первое слагаемое
            b = Float.parseFloat(answer); // второе слагаемое
            if (mathway.equals("+") && sigh_action.equals("="))
                answer = Numcore.plus(a, b);
            else if (mathway.equals("-") && sigh_action.equals("="))
                answer = Numcore.minus(a, b);
            else if (mathway.equals("*") && sigh_action.equals("="))
                answer = Numcore.multy(a, b);
            else if (mathway.equals("/") && sigh_action.equals("="))
                answer = Numcore.divide(a, b);
            mathway = "empty";
        }
    }


    public static String getAnswer() {
        return answer;
    }

}
