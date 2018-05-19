package com.example.anujdawar.udacitylesson2project1;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfCoffee = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view)
    {
        displayQuantity(numberOfCoffee);
        updateOrderSummary(numberOfCoffee * 5);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayQuantity(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityTV);
        quantityTextView.setText(String.valueOf(number));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateOrderSummary(int number)
    {
        EditText nameTV = (EditText) findViewById(R.id.nameEditText);
        CheckBox whippedCreamBox = (CheckBox) findViewById(R.id.whippedCreamBox);
        CheckBox chocolateToppingBox = (CheckBox) findViewById(R.id.chocolateCreamBox);

        String orderSummary = "Name : " + nameTV.getText().toString() + "\n";
        orderSummary += "Add Whipped Cream? : " + String.valueOf(whippedCreamBox.isChecked()) + "\n";
        orderSummary += "Add Chocolate Topping? : " + String.valueOf(chocolateToppingBox.isChecked()) + "\n";
        orderSummary += "Quantity : " + String.valueOf(numberOfCoffee)+ "\n";
        orderSummary += "Total : $" + String.valueOf((numberOfCoffee * 5) + (whippedCreamBox.isChecked() ? 1 : 0)  + (chocolateToppingBox.isChecked() ? 2 : 0)  ) + ".00\n";
        orderSummary += "ThankYou";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "order summary");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void incrementQuantity(View view)
    {
        numberOfCoffee++;
        displayQuantity(numberOfCoffee);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void decrementQuantity(View view)
    {
        if(numberOfCoffee > 0)
        {
            numberOfCoffee--;
            displayQuantity(numberOfCoffee);
        }
    }
}