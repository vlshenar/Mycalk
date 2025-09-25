package org.example.calcmodel;

public abstract class MathOperator {
    //символ арифметической операции
    public final char mathAction;

    protected MathOperator(char mathAction) {
        this.mathAction = mathAction;
    }

    //действие
    public abstract void operate();
}
