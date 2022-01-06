package com.example.calculator;

public class Model {

    public static final int EQUALS = 0;
    public static final int ADD = 1;
    public static final int SUBTRACT = 2;
    public static final int MULTIPLY = 3;
    public static final int DIVIDE = 4;

    public static final int WHOLE_NUMBERS = 0;
    public static final int FRACTIONAL_NUMBERS = 1;

    public static final int RESULT = 0;
    public static final int OPERAND_1 = 1;
    public static final int RESULT_D = 0;
    public static final int OPERAND_D = 1;

    public static final int AC = 0;
    public static final int C = 1;

    String display = "";

    int operand_1 = 0;
    int result = 0;
    double operand_d = 0.0;
    double result_d = 0.0;

    int operation = EQUALS;
    int mode = RESULT;
    int clear_level = AC;
    int precision = WHOLE_NUMBERS;

    void evaluate(int next) {
        if (precision == WHOLE_NUMBERS) {
            switch (operation) {
                case 1:
                    result = operand_1 + result;
                    break;
                case 2:
                    result = result - operand_1;
                    break;
                case 3:
                    result = operand_1 * result;
                    break;
                case 4:
                    result_d = result_d / operand_d;
                    break;
                case 5:
                    result = result * -1;
                    break;
                case 6:
                    result = result / 100;
                    break;
                case 0:
                default:
                    break;
            }
        } else {
            switch (operation) {
                case 1:
                    result_d = operand_d + result_d;
                    break;
                case 2:
                    result_d = result_d - operand_d;
                    break;
                case 3:
                    result_d = operand_d * result_d;
                    break;
                case 4:
                    result_d = result_d / operand_d;
                    break;
                case 5:
                    result_d = result_d * -1;
                    break;
                case 6:
                    result_d = result_d / 100;
                    break;
                case 0:
                default:
                    break;
            }
        }
        operation = next;
    }

    void clear_level_operation() {
        result = 0;
        result_d = 0;
        operand_1 = 0;
        operand_d = 0;
        mode = Model.RESULT;
        precision = Model.WHOLE_NUMBERS;
        clear_level = Model.AC;
    }

    void number_button_actions() {
        if (precision == Model.WHOLE_NUMBERS) {
            switch (mode) {
                case RESULT:

                    result = Integer.parseInt(display);
                    break;
                case Model.OPERAND_1:

                    operand_1 = Integer.parseInt(display);
                    break;
            }
        } else {
            switch (mode) {
                case Model.RESULT_D:

                    result_d = Double.parseDouble(display);
                    break;
                case Model.OPERAND_D:

                    operand_d = Double.parseDouble(display);
                    break;
            }
        }
    }

    void debugger_System_Out(String s, String s2) {
        System.out.println("Model Result: " + result);
        System.out.println("Model Operand 1: " + operand_1);
        System.out.println("Model "+s + result_d);
        System.out.println("Model "+s2 + operand_d);
        System.out.println("Model Operation: " + operation);
        System.out.println("Model Mode: " + mode);
    }

    public Model ()
    {
    }

}
