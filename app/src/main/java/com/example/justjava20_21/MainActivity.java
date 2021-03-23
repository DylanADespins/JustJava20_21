package com.example.justjava20_21;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // Looks for users input through text asking what their name is
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // Looks for users input on Whipped Cream Topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Looks for users input on Chocolate Topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        Log.v("MainActivity", "Name: " + name);
        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        Log.v("MainActivity", "Has Chocolate: " + hasChocolate );

        int basePrice = calculatePrice(hasWhippedCream, hasChocolate) ;
        String priceMessage = createOrderSummary(name, basePrice, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }
    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 Cup of Coffee
        int basePrice = 5;

        // Add Price for Whipped Cream Topping
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // Add Price for Chocolate Topping
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculates the total order price based on multiplying the quantity
        return quantity * basePrice;
    }

    /**
     * Create Summary of the order
     *
     * @param name of the user
     * @param basePrice of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants Chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name,int basePrice, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream ;
        priceMessage += "\nAdd Chocolate? " + addChocolate ;
        priceMessage += "\nQuantity: " + quantity ;
        priceMessage += "\nTotal: $" + basePrice;
        priceMessage +="\nThank you!";
        return priceMessage;
    }
    /**
     * This method is called when the + button is clicked.
     */
    public void increment (View view) {
        if(quantity == 100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }
    /**
     * This method is called when the - button is clicked.
     */
    public void decrement (View view) {
        if (quantity == 1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot less than 1 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}