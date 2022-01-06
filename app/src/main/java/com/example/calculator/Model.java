package com.example.calculator;

public class Model {

    private static final int EQUALS = 0;
    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;

    private static final int WHOLE_NUMBERS = 0;
    private static final int FRACTIONAL_NUMBERS = 1;

    private static final int RESULT = 0;
    private static final int OPERAND_1 = 1;
    private static final int RESULT_D = 0;
    private static final int OPERAND_D = 1;

    private static final int AC = 0;
    private static final int C = 1;

    String display = "";
    int operand_1 = 0;
    int result = 0;
    double operand_d = 0.0;
    double result_d = 0.0;

    int operation = EQUALS;
    int mode = RESULT;
    int clear_level = AC;
    int precision = WHOLE_NUMBERS;

    private void evaluate(int next) {
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

    public Model ()
    {
        String display = "";
        int operand_1 = 0;
        int result = 0;
        double operand_d = 0.0;
        double result_d = 0.0;

        int operation = EQUALS;
        int mode = RESULT;
        int clear_level = AC;
        int precision = WHOLE_NUMBERS;
    }

}
