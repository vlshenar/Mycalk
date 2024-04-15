package org.example;

/**
 * Study project
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String firstterm = "3";
        String tochka = ".";
        String secondterm = "7";
        Numcore.insertTerm("0");
        Numcore.insertTerm(firstterm);
        // Numcore.insertTerm(tochka);
        Numcore.insertTerm(firstterm);
        Numcore.insertTerm(secondterm);
        Numcore.operateNum("+");
        Numcore.insertTerm(firstterm);
        Numcore.operateNum("=");
        System.out.println(Numcore.getAnswer());
    }
}
