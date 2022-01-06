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

    private Model model;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        model = new Model();

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
        if (model.clear_level == Model.AC) {
            clear.setText("AC");
        } else {
            clear.setText("C");
        }
        button_design(layoutParams, clear);
        clear.setOnClickListener(v -> {
            clear_button(clear);

            screen.setText(model.display);
            model.debugger_System_Out("Result: ", "Operand 1: ");
        });

        Button switch_sign = new Button(this);
        switch_sign.setText("+/-");
        button_design(layoutParams, switch_sign);

        switch_sign.setOnClickListener(v -> {
            if (model.precision == Model.WHOLE_NUMBERS) {
                if (model.display.length() > 0) {
                    model.result = Integer.parseInt(model.display) * -1;
                }
                model.display = String.valueOf(model.result);
            } else {
                if (model.display.length() > 0) {
                    model.result_d = Double.parseDouble(model.display) * -1;
                }
                model.display = String.valueOf(model.result_d);
            }
            screen.setText(model.display);
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        Button percent = create_functionality_button("%", 0xFFD4D4D2);
        percent.setLayoutParams(layoutParams);
        percent.setOnClickListener(v -> {
            model.precision = Model.FRACTIONAL_NUMBERS;
            model.evaluate(Model.EQUALS);
            model.result_d = Double.parseDouble(model.display) / 100;
            model.display = String.valueOf(model.result_d);
            screen.setText(model.display);
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });

        Button divide = create_functionality_button("/", 0xFFFF9500);
        divide.setLayoutParams(layoutParams);
        divide.setOnClickListener(v -> {
            model.evaluate(Model.DIVIDE);
            model.precision = Model.FRACTIONAL_NUMBERS;
            model.result_d = model.result;
            model.operand_d = model.operand_1;
            model.mode = Model.OPERAND_D;
            model.display = "";
            screen.setText(model.display);
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
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
            model.evaluate(Model.MULTIPLY);
            model.mode = Model.OPERAND_1;
            model.display = "";
            screen.setText(model.display);
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
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
            model.evaluate(Model.SUBTRACT);
            model.display = "";
            screen.setText(model.display);
            model.mode = 1;
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
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
            model.evaluate(Model.ADD);
            model.display = "";
            screen.setText(model.display);
            model.mode = 1;
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
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
            model.evaluate(0);
            if (model.precision == Model.WHOLE_NUMBERS) {
                model.display = String.valueOf(model.result);
            } else {
                model.display = String.valueOf(model.result_d);
            }
            screen.setText(model.display);
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
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

    private void clear_button(Button clear) {
        model.display = "";

        if (model.clear_level > 0) {
            model.clear_level_operation();
            clear.setText("AC");
        } else {
            model.clear_level = Model.C;
            clear.setText("C");
        }
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
            model.display = model.display + btn.getText().toString();
            if (model.display.length() < 5) {
                model.number_button_actions();
                screen.setText(model.display);
            }
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });
    }

    private void button_functionality(TextView screen, Button decimal_point) {
        decimal_point.setOnClickListener(v -> {
            Button btn = (Button) v;
            if (model.display.length() < 5) {
                model.precision = Model.FRACTIONAL_NUMBERS;
                model.operand_d = model.operand_1;
                model.result_d = model.result;
                switch (model.mode) {
                    case Model.RESULT_D:
                    case Model.OPERAND_D:
                        model.display = model.display + btn.getText().toString();
                        screen.setText(model.display);
                        //result_d = Double.parseDouble(display);
                        break;
                    //operand_d = Double.parseDouble(display);
                }
            }
            model.debugger_System_Out("Result Decimal: ", "Operand Decimal: ");
        });
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