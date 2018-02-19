package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 10) {
            Toast.makeText(this, getString(R.string.too_many_coffees), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.too_few_coffees),Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        String clientName = ((EditText) findViewById(R.id.name_edit_text)).getText().toString();
        String orderSummary = createOrderSummary(
                calculatePrice(quantity, hasWhippedCream, hasChocolate),
                clientName,
                hasWhippedCream,
                hasChocolate
        );

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        String[] addresses = new String[] {"fakemail@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_incoming) + " " + clientName);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int quantity, boolean whippedCream, boolean chocolate) {
        int price = 5;
        if (whippedCream) {
            price += 1;
        }
        if (chocolate) {
            price += 2;
        }
        return quantity * price;
    }

    private String createOrderSummary(int price, String name, boolean whippedCream, boolean chocolate) {
        String summary = getString(R.string.name) + ": "+ name + "\n";
        summary += getString(R.string.quantity) + ": " + quantity + "\n";
        summary += getString(R.string.whipped_cream) + "? " + (whippedCream ? getString(R.string.yes) : getString(R.string.no)) + "\n";
        summary += getString(R.string.chocolate) + "? " + (chocolate ? getString(R.string.yes) : getString(R.string.no)) + "\n";
        summary += getString(R.string.total) + ": $ " + price + "\n";
        summary += getString(R.string.thank_you) + "!";
        return summary;
    }

}