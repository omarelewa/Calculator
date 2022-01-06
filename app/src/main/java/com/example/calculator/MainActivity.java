package com.example.calculator;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    String display = "";

    private static final int EQUALS = 0;
    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;
    private static final int SWITCH_SIGN = 5;
    private static final int PERCENT = 6;

    private static final int RESULT = 0;
    private static final int OPERAND_1 = 1;

    private static final int AC = 0;
    private static final int C = 1;

    int operand_1 = 0;
    // 1 = add; 2 = subtract; 3 = multiply; 4 divide; 5 switch_sign; 6 percent; 0 = equals;
    int operation = EQUALS;

    int result = 0;
    int mode = RESULT;
    int clear_level = AC;

    // 0 = Error; 1 = Operand 1; 2 = Operand 2; 3 = Result;
    int view = 1;

    private void evaluate (int next) {
        switch (operation){
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
                result = result / operand_1;
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
        operation = next;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        // Create the root layout
        LinearLayout ll = new LinearLayout(this);

        // Set root orientation to vertical
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsText = new LinearLayout.LayoutParams(
                2000,
                320,
                2
         );

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
        if (clear_level == 0){
            clear.setText("AC");
        }
        else {
            clear.setText("C");
        }
        clear.setLayoutParams(layoutParams);
        clear.setBackgroundColor(0xFFD4D4D2);
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                display = "";

                if (clear_level>0){
                    result = 0;
                    operand_1 = 0;
                    mode = 0;
                    clear_level = 0;
                    clear.setText("AC");
                }
                else {
                    clear_level = 1;
                    clear.setText("C");
                }
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
//                System.out.println(btn.getText().toString());
            }
        });

        Button switch_sign = new Button(this);
        switch_sign.setText("+/-");
        switch_sign.setBackgroundColor(0xFFD4D4D2);
        switch_sign.setLayoutParams(layoutParams);
        switch_sign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(0);
//                display = display + btn.getText().toString();
//                screen.setText(display);
//                System.out.println(btn.getText().toString());
                if (display.length()>0){
                    result = Integer.parseInt(display) * -1;
                }
                display = String.valueOf(result);
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button percent = new Button(this);
        percent.setText("%");
        percent.setBackgroundColor(0xFFD4D4D2);
        percent.setLayoutParams(layoutParams);
        percent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(0);
                result = Integer.parseInt(display) / 100;
                display = String.valueOf(result);
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button divide = new Button(this);
        divide.setText("/");
        divide.setBackgroundColor(0xFFFF9500);
        divide.setLayoutParams(layoutParams);
        divide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(4);
                mode = 1;
                display = "";
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        // Add buttons to Row1 Layout
        Row1.addView(clear);
        Row1.addView(switch_sign);
        Row1.addView(percent);
        Row1.addView(divide);

        // Row 2
        LinearLayout Row2 = new LinearLayout(this);
        Button seven = new Button(this);
        seven.setText("7");
        seven.setBackgroundColor(0xFFD4D4D2);
        seven.setLayoutParams(layoutParams);
        seven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button eight = new Button(this);
        eight.setText("8");
        eight.setBackgroundColor(0xFFD4D4D2);
        eight.setLayoutParams(layoutParams);
        eight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button nine = new Button(this);
        nine.setText("9");
        nine.setBackgroundColor(0xFFD4D4D2);
        nine.setLayoutParams(layoutParams);
        nine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }

        });

        Button multiply = new Button(this);
        multiply.setText("x");
        multiply.setBackgroundColor(0xFFFF9500);
        multiply.setLayoutParams(layoutParams);
        multiply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(3);
                mode = 1;
                display = "";
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        // Add buttons to Row2 Layout
        Row2.addView(seven);
        Row2.addView(eight);
        Row2.addView(nine);
        Row2.addView(multiply);

        // Row 3
        LinearLayout Row3 = new LinearLayout(this);

        Button four = new Button(this);
        four.setText("4");
        four.setBackgroundColor(0xFFD4D4D2);
        four.setLayoutParams(layoutParams);
        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button five = new Button(this);
        five.setText("5");
        five.setBackgroundColor(0xFFD4D4D2);
        five.setLayoutParams(layoutParams);
        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button six = new Button(this);
        six.setText("6");
        six.setBackgroundColor(0xFFD4D4D2);
        six.setLayoutParams(layoutParams);
        six.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button subtract = new Button(this);
        subtract.setText("-");
        subtract.setBackgroundColor(0xFFFF9500);
        subtract.setLayoutParams(layoutParams);
        subtract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(2);
                display = "";
                screen.setText(display);
                mode = 1;
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        // Add buttons to Row3 Layout
        Row3.addView(four);
        Row3.addView(five);
        Row3.addView(six);
        Row3.addView(subtract);

        // Row 4
        LinearLayout Row4 = new LinearLayout(this);

        Button one = new Button(this);
        one.setText("1");
        one.setBackgroundColor(0xFFD4D4D2);
        one.setLayoutParams(layoutParams);
        one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button two = new Button(this);
        two.setText("2");
        two.setBackgroundColor(0xFFD4D4D2);
        two.setLayoutParams(layoutParams);
        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button three = new Button(this);
        three.setText("3");
        three.setBackgroundColor(0xFFD4D4D2);
        three.setLayoutParams(layoutParams);
        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button add = new Button(this);
        add.setText("+");
        add.setBackgroundColor(0xFFFF9500);
        add.setLayoutParams(layoutParams);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(1);
                display = "";
                screen.setText(display);
                mode = 1;
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        // Add buttons to Row4 Layout
        Row4.addView(one);
        Row4.addView(two);
        Row4.addView(three);
        Row4.addView(add);

        // Row 5
        LinearLayout Row5 = new LinearLayout(this);

        Button zero = new Button(this);
        zero.setText("0");
        zero.setBackgroundColor(0xFFD4D4D2);
        zero.setLayoutParams(layoutParams2);
        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button decimal_point = new Button(this);
        decimal_point.setText(".");
        decimal_point.setBackgroundColor(0xFFD4D4D2);
        decimal_point.setLayoutParams(layoutParams);
        decimal_point.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                if (display.length() < 5){
                    switch (mode){
                        case 0:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            result = Integer.parseInt(display);
                            break;
                        case 1:
                            display = display + btn.getText().toString();
                            screen.setText(display);
                            operand_1 = Integer.parseInt(display);
                            break;
                    }
                }
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
        });

        Button equals = new Button(this);
        equals.setText("=");
        equals.setBackgroundColor(0xFFFF9500);
        equals.setLayoutParams(layoutParams);
        equals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn = (Button) v;
                evaluate(0);
                display = String.valueOf(result);
                screen.setText(display);
                System.out.println("Result: " + result);
                System.out.println("Operand 1: " + operand_1);
                System.out.println("Operation: " + operation);
                System.out.println("Mode: " + mode);
            }
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

}