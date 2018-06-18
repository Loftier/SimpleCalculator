package com.example.loftier.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class
Calculator extends AppCompatActivity implements View.OnClickListener
{
    StringBuffer buffer = new StringBuffer();
    StringBuffer number = new StringBuffer();

    Button[] num;
    Button[] operators;
    EditText display;

    double num1=0.0, num2=0.0;
    int count=0, o=1;

    //Calculations calc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //initialization
        num = new Button[10];
        operators = new Button[9];

        //numbers
        num[1] = findViewById(R.id.idbtnone);
        num[2] = findViewById(R.id.idbtntwo);
        num[3] = findViewById(R.id.idbtnthree);
        num[4] = findViewById(R.id.idbtnfour);
        num[5] = findViewById(R.id.idbtnfive);
        num[6] = findViewById(R.id.idbtnsix);
        num[7] = findViewById(R.id.idbtnseven);
        num[8] = findViewById(R.id.idbtneight);
        num[9] = findViewById(R.id.idbtnnine);
        num[0] = findViewById(R.id.idbtnzero);

        //operators
        operators[0] = findViewById(R.id.idbtnac);
        operators[1] = findViewById(R.id.idbtnback);
        operators[2] = findViewById(R.id.idbtndivide);
        operators[3] = findViewById(R.id.idbtnmultiply);
        operators[4] = findViewById(R.id.idbtnsub);
        operators[5] = findViewById(R.id.idbtnadd);
        operators[6] = findViewById(R.id.idbtnpercent);
        operators[7] = findViewById(R.id.idbtndot);
        operators[8] = findViewById(R.id.idbtnequal);

        //Display Screen
        display = findViewById(R.id.idetdisplay);

        for(int i=0; i<10; i++)
            num[i].setOnClickListener(this);

        for(int i=0; i<9; i++)
            operators[i].setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        for(int i = 0; i<10; i++)
            if(view == num[i])
                displayNumbers(i);

        for(int i=0; i<9; i++)
            if (view == operators[i])
                displayOperations(i);
    }

    void displayNumbers(int i)
    {
        buffer.append(i);
        number.append(i);
        display.setText(buffer);
        operators[0].setClickable(true);
        operators[1].setClickable(true);
    }
    void displayOperations(int i) {
        if (i == 0)
        {
            buffer.delete(0, buffer.length());
            number.delete(0, number.length());
            num1 = 0.0;
            num2 = 0.0;
            count = 0;
            display.setText("");
        }
        else
        if (i == 1)
        {
            if (buffer.length() > 0)
            {
                if(buffer.indexOf("/") != -1 || buffer.indexOf("*") != -1 || buffer.indexOf("+") != -1 ||
                    buffer.indexOf("-") != -1 || buffer.indexOf("^") != -1 )
                {
                    buffer.delete(buffer.length() - 1, buffer.length());
                    display.setText(buffer);
                    number.delete(0, number.length());
                    count=0;
                    o=1;
                }
                else {
                    buffer.delete(buffer.length() - 1, buffer.length());
                    if(number.length()>0)
                        number.delete(number.length() - 1, number.length());
                    display.setText(buffer);
                }
            }
            else
            {
                operators[0].setClickable(false);
                operators[1].setClickable(false);
            }
        }
        else
        if (i >= 2 && i <= 6)
        {
            if(buffer.toString().isEmpty())
            {
                Toast.makeText(this, "Enter a Number first", Toast.LENGTH_SHORT).show();
                operators[0].setClickable(false);
                operators[1].setClickable(false);
            }
            else
                solve(i);
        }
        else
        if (i == 7)
        {
            number.append('.');
            display.setText(buffer.append('.'));
        }
        else
        if (i == 8)
            if(buffer.toString().isEmpty())
                Toast.makeText(this, "Enter a Number first", Toast.LENGTH_SHORT).show();
            else
                solve();
    }

    void solve ( int i) {
        if (count == 0)
        {
            count++;
            num1 = Double.parseDouble(display.getText().toString());
            number.delete(0, number.length());
            buffer.delete(0, buffer.length());
            buffer.append(num1);
            if (buffer.length() > 12)
                buffer.delete(12, buffer.length());
            if (number.length() > 12)
                number.delete(12, number.length());

            switch (i)
            {
                case 2: {
                    o = 2;
                    display.setText(buffer.append('/'));
                    break;
                }
                case 3: {
                    o = 3;
                    display.setText(buffer.append('*'));
                    break;
                }
                case 4: {
                    o = 4;
                    display.setText(buffer.append('-'));
                    break;
                }
                case 5: {
                    o = 5;
                    display.setText(buffer.append('+'));
                    break;
                }
                case 6: {
                    o = 6;
                    display.setText(buffer.append('^'));
                    break;
                }
                default: {
                }
            }
        }
        else
        {
            if (number.toString().isEmpty()) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            } else {
                count++;
                num2 = Double.parseDouble(number.toString());
                switch (o) {
                    case 2:
                        num1 = num1 / num2;
                        break;
                    case 3:
                        num1 = num1 * num2;
                        break;
                    case 4:
                        num1 = num1 - num2;
                        break;
                    case 5:
                        num1 = num1 + num2;
                        break;
                    case 6:
                        num1 = Math.pow(num1, num2);
                        break;
                    default:
                        num1 = 0.0;
                }
                number.delete(0, number.length());
                buffer.delete(0, buffer.length());
                buffer.append(num1);
                if (buffer.length() > 12)
                    buffer.delete(12, buffer.length());
                if (number.length() > 12)
                    number.delete(12, number.length());

                switch (i)
                {
                    case 2: {
                        o = 2;
                        display.setText(buffer.append('/'));
                        break;
                    }
                    case 3: {
                        o = 3;
                        display.setText(buffer.append('*'));
                        break;
                    }
                    case 4: {
                        o = 4;
                        display.setText(buffer.append('-'));
                        break;
                    }
                    case 5: {
                        o = 5;
                        display.setText(buffer.append('+'));
                        break;
                    }
                    case 6: {
                        o = 6;
                        display.setText(buffer.append('^'));
                        break;
                    }
                    default: {
                    }
                }
            }
        }
    }
    void solve ()
    {
        if (number.toString().isEmpty())
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        else
        {
            num2 = Double.parseDouble(number.toString());
            switch (o) {
                case 2:
                    num1 = num1 / num2;
                    break;
                case 3:
                    num1 = num1 * num2;
                    break;
                case 4:
                    num1 = num1 - num2;
                    break;
                case 5:
                    num1 = num1 + num2;
                    break;
                case 6:
                    num1 = Math.pow(num1, num2);
                    break;
                default:
                    num1 = num2;
                    break;
            }
            count = 0;

            buffer.delete(0, buffer.length());
            buffer.append(num1);
            if (buffer.length() > 12)
                buffer.delete(12, buffer.length());

            number.delete(0, number.length());
            number.append(num1);
            if (number.length() > 12)
                number.delete(12, number.length());

            display.setText(buffer);
        }
    }
}
