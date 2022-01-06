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
        clear.setText("C");
        clear.setLayoutParams(layoutParams);
        clear.setBackgroundColor(0xFFD4D4D2);
        clear.setPaddingRelative(10,10,10,10);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                	screen.setText("");
            }
        });



        Button switch_sign = new Button(this);
        switch_sign.setText("+/-");
        switch_sign.setBackgroundColor(0xFFD4D4D2);
        switch_sign.setLayoutParams(layoutParams);

        Button percent = new Button(this);
        percent.setText("%");
        percent.setBackgroundColor(0xFFD4D4D2);
        percent.setLayoutParams(layoutParams);

        Button divide = new Button(this);
        divide.setText("/");
        divide.setBackgroundColor(0xFFFF9500);
        divide.setLayoutParams(layoutParams);
        //divide.getBackground().setColorFilter(0x00FF9500, PorterDuff.Mode.MULTIPLY);
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
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("7");
            }
        });

        Button eight = new Button(this);
        eight.setText("8");
        eight.setBackgroundColor(0xFFD4D4D2);
        eight.setLayoutParams(layoutParams);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("8");
            }
        });

        Button nine = new Button(this);
        nine.setText("9");
        nine.setBackgroundColor(0xFFD4D4D2);
        nine.setLayoutParams(layoutParams);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("9");
            }
        });

        Button multiply = new Button(this);
        multiply.setText("x");
        multiply.setBackgroundColor(0xFFFF9500);
        multiply.setLayoutParams(layoutParams);

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
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("4");
            }
        });

        Button five = new Button(this);
        five.setText("5");
        five.setBackgroundColor(0xFFD4D4D2);
        five.setLayoutParams(layoutParams);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("5");
            }
        });

        Button six = new Button(this);
        six.setText("6");
        six.setBackgroundColor(0xFFD4D4D2);
        six.setLayoutParams(layoutParams);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("6");
            }
        });

        Button subtract = new Button(this);
        subtract.setText("-");
        subtract.setBackgroundColor(0xFFFF9500);
        subtract.setLayoutParams(layoutParams);

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
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("1");
            }
        });

        Button two = new Button(this);
        two.setText("2");
        two.setBackgroundColor(0xFFD4D4D2);
        two.setLayoutParams(layoutParams);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("2");
            }
        });

        Button three = new Button(this);
        three.setText("3");
        three.setBackgroundColor(0xFFD4D4D2);
        three.setLayoutParams(layoutParams);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("3");
            }
        });

        Button add = new Button(this);
        add.setText("+");
        add.setBackgroundColor(0xFFFF9500);
        add.setLayoutParams(layoutParams);

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
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("0");
            }
        });

        Button decimal_point = new Button(this);
        decimal_point.setText(".");
        decimal_point.setBackgroundColor(0xFFD4D4D2);
        decimal_point.setLayoutParams(layoutParams);

        Button equals = new Button(this);
        equals.setText("=");
        equals.setBackgroundColor(0xFFFF9500);
        equals.setLayoutParams(layoutParams);

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