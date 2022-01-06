package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

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
    private Model model;

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

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        // Create the root layout
        LinearLayout ll = new LinearLayout(this);

        // Set root orientation to vertical
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsText = setLayoutParams();

        // Create TextView to hold the answers screen
        TextView screen = new TextView(this);
        screen.setText("");
        screen.setLayoutParams(layoutParamsText);
        screen.setBackgroundColor(0xFF1C1C1C);
        screen.setTextColor(0xFFD4D4D2);
        screen.setAutoSizeTextTypeUniformWithConfiguration(
                40,
                80,
                1,
                1
        );

        // Button Colors
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000, 320, 1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(2000, 320, 2);

        // Children Layouts
        // Row 1
        LinearLayout Row1 = new LinearLayout(this);

        // Buttons of the row
        Button clear = new Button(this);
        clear.setLayoutParams(layoutParams);
        if (clear_level == AC) {
            clear.setText("AC");
        } else {
            clear.setText("C");
        }
        button_design(layoutParams, clear);
        clear.setOnClickListener(v -> {
            display = "";

            if (clear_level > 0) {
                result = 0;
                result_d = 0;
                operand_1 = 0;
                operand_d = 0;
                mode = RESULT;
                precision = WHOLE_NUMBERS;
                clear_level = AC;
                clear.setText("AC");
            } else {
                clear_level = C;
                clear.setText("C");
            }
            screen.setText(display);
            debugger_System_Out("Result: ", "Operand 1: ");
        });

        Button switch_sign = new Button(this);
        switch_sign.setText("+/-");
        button_design(layoutParams, switch_sign);

        switch_sign.setOnClickListener(v -> {
            if (precision == WHOLE_NUMBERS) {
                if (display.length() > 0) {
                    result = Integer.parseInt(display) * -1;
                }
                display = String.valueOf(result);
            } else {
                if (display.length() > 0) {
                    result_d = Double.parseDouble(display) * -1;
                }
                display = String.valueOf(result_d);
            }
            screen.setText(display);
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        Button percent = create_functionality_button("%", 0xFFD4D4D2);
        percent.setLayoutParams(layoutParams);
        percent.setOnClickListener(v -> {
            precision = FRACTIONAL_NUMBERS;
            evaluate(EQUALS);
            result_d = Double.parseDouble(display) / 100;
            display = String.valueOf(result_d);
            screen.setText(display);
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        Button divide = create_functionality_button("/", 0xFFFF9500);
        divide.setLayoutParams(layoutParams);
        divide.setOnClickListener(v -> {
            evaluate(DIVIDE);
            precision = FRACTIONAL_NUMBERS;
            result_d = result;
            operand_d = operand_1;
            mode = OPERAND_D;
            display = "";
            screen.setText(display);
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        // Add buttons to Row1 Layout
        render_row(Row1, clear, switch_sign, percent, divide);

        // Row 2
        LinearLayout Row2 = new LinearLayout(this);
        Button seven = new Button(this);
        seven.setText("7");
        number_button(screen, layoutParams, seven);

        Button eight = new Button(this);
        eight.setText("8");
        number_button(screen, layoutParams, eight);

        Button nine = new Button(this);
        nine.setText("9");
        number_button(screen, layoutParams, nine);

        Button multiply = create_functionality_button("x", 0xFFFF9500);
        multiply.setLayoutParams(layoutParams);
        multiply.setOnClickListener(v -> {
            evaluate(MULTIPLY);
            mode = OPERAND_1;
            display = "";
            screen.setText(display);
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        // Add buttons to Row2 Layout
        render_row(Row2, seven, eight, nine, multiply);

        // Row 3
        LinearLayout Row3 = new LinearLayout(this);

        Button four = new Button(this);
        four.setText("4");
        number_button(screen, layoutParams, four);

        Button five = new Button(this);
        five.setText("5");
        number_button(screen, layoutParams, five);

        Button six = new Button(this);
        six.setText("6");
        number_button(screen, layoutParams, six);

        Button subtract = create_functionality_button("-", 0xFFFF9500);
        subtract.setLayoutParams(layoutParams);
        subtract.setOnClickListener(v -> {
            evaluate(SUBTRACT);
            display = "";
            screen.setText(display);
            mode = 1;
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        // Add buttons to Row3 Layout
        render_row(Row3, four, five, six, subtract);

        // Row 4
        LinearLayout Row4 = new LinearLayout(this);

        Button one = new Button(this);
        one.setText("1");
        number_button(screen, layoutParams, one);

        Button two = new Button(this);
        two.setText("2");
        number_button(screen, layoutParams, two);

        Button three = new Button(this);
        three.setText("3");
        number_button(screen, layoutParams, three);

        Button add = create_functionality_button("+", 0xFFFF9500);
        add.setLayoutParams(layoutParams);
        add.setOnClickListener(v -> {
            evaluate(ADD);
            display = "";
            screen.setText(display);
            mode = 1;
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        // Add buttons to Row4 Layout
        render_row(Row4, one, two, three, add);

        // Row 5
        LinearLayout Row5 = new LinearLayout(this);

        Button zero = new Button(this);
        zero.setText("0");
        number_button(screen, layoutParams2, zero);

        Button decimal_point = create_functionality_button(".", 0xFFD4D4D2);
        decimal_point.setLayoutParams(layoutParams);
        button_functionality(screen, decimal_point);

        Button equals = create_functionality_button("=", 0xFFFF9500);
        equals.setLayoutParams(layoutParams);
        equals.setOnClickListener(v -> {
            evaluate(0);
            if (precision == WHOLE_NUMBERS) {
                display = String.valueOf(result);
            } else {
                display = String.valueOf(result_d);
            }
            screen.setText(display);
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        // Add buttons to Row5 Layout
        Row5.addView(zero);
        Row5.addView(decimal_point);
        Row5.addView(equals);

        // Last part add components to the root layout
        ll.addView(screen);
        ll.addView(Row1);
        ll.addView(Row2);
        ll.addView(Row3);
        ll.addView(Row4);
        ll.addView(Row5);

        setContentView(ll);


    }

    @NonNull
    private Button create_functionality_button(String s, int i) {
        Button equals = new Button(this);
        equals.setText(s);
        equals.setBackgroundColor(i);
        return equals;
    }

    private void render_row(LinearLayout row1, Button clear, Button switch_sign, Button percent, Button divide) {
        row1.addView(clear);
        row1.addView(switch_sign);
        row1.addView(percent);
        row1.addView(divide);
    }

    private void number_button(TextView screen, LinearLayout.LayoutParams layoutParams2, Button zero) {
        zero.setBackgroundColor(0xFFD4D4D2);
        zero.setLayoutParams(layoutParams2);
        zero.setOnClickListener(v -> {
            Button btn = (Button) v;
            if (display.length() < 5) {
                if (precision == WHOLE_NUMBERS) {
                    switch (mode) {
                        case RESULT:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case OPERAND_1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                } else {
                    switch (mode) {
                        case RESULT_D:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result_d = Double.parseDouble(display);
                            break;
                        case OPERAND_D:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_d = Double.parseDouble(display);
                            break;
                    }
                }
            }
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });
    }

    private void button_functionality(TextView screen, Button decimal_point) {
        decimal_point.setOnClickListener(v -> {
            Button btn = (Button) v;
            if (display.length() < 5) {
                precision = FRACTIONAL_NUMBERS;
                operand_d = operand_1;
                result_d = result;
                switch (mode) {
                    case RESULT_D:
                    case OPERAND_D:
                        display = display + btn.getText().toString();
                        screen.setText(display);
                        //result_d = Double.parseDouble(display);
                        break;
                    //operand_d = Double.parseDouble(display);
                }
            }
            debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });
    }

    private void debugger_System_Out(String s, String s2) {
        System.out.println("Result: " + result);
        System.out.println("Operand 1: " + operand_1);
        System.out.println(s + result_d);
        System.out.println(s2 + operand_d);
        System.out.println("Operation: " + operation);
        System.out.println("Mode: " + mode);
    }

    private void button_design(LinearLayout.LayoutParams layoutParams, @NonNull Button clear) {
        clear.setLayoutParams(layoutParams);
        clear.setBackgroundColor(0xFFD4D4D2);
    }

    @NonNull
    private LinearLayout.LayoutParams setLayoutParams() {
        return new LinearLayout.LayoutParams(
                2000,
                320,
                2
        );
    }

}