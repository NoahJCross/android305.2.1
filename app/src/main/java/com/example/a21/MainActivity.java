package com.example.a21;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaring UI elements
    private Spinner sourceUnitSpinner, destinationUnitSpinner, typeSpinner;
    private EditText valueEditText;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing UI elements
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        valueEditText = findViewById(R.id.valueEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);
        typeSpinner = findViewById(R.id.typeSpinner);

        // Setting up adapter for the type spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        // Setting up listener for the type spinner
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedUnit = parentView.getItemAtPosition(position).toString();
                ArrayAdapter<CharSequence> adapter = getUpdatedAdapter(selectedUnit);
                sourceUnitSpinner.setAdapter(adapter);
                destinationUnitSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        // Setting up listener for the convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
                // Hiding the keyboard after conversion
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

    // Method to get the updated adapter based on selected type
    private ArrayAdapter<CharSequence> getUpdatedAdapter(String selectedType) {
        ArrayAdapter<CharSequence> adapter;

        switch (selectedType) {
            case "Length":
                adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.length_array, android.R.layout.simple_spinner_item);
                break;
            case "Weight":
                adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.weight_array, android.R.layout.simple_spinner_item);
                break;
            case "Temperature":
                adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.temperature_array, android.R.layout.simple_spinner_item);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.default_array, android.R.layout.simple_spinner_item);
                break;
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    // Method to handle conversion
    private void convert() {
        String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
        String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
        String valueString = valueEditText.getText().toString();

        // Checking if the value is empty
        if (valueString.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double value = Double.parseDouble(valueString);

            // Checking if source and destination units are the same
            if (sourceUnit.equals(destinationUnit)) {
                Toast.makeText(MainActivity.this, "Source and destination units are the same", Toast.LENGTH_SHORT).show();
                return;
            }

            double convertedValue = 0;

            // Performing conversion based on source unit
            switch (sourceUnit) {
                case "Inch":
                    convertedValue = convertInchTo(destinationUnit, value);
                    break;
                case "Foot":
                    convertedValue = convertFootTo(destinationUnit, value);
                    break;
                case "Yard":
                    convertedValue = convertYardTo(destinationUnit, value);
                    break;
                case "Mile":
                    convertedValue = convertMileTo(destinationUnit, value);
                    break;
                case "Pound":
                    convertedValue = convertPoundTo(destinationUnit, value);
                    break;
                case "Ounce":
                    convertedValue = convertOunceTo(destinationUnit, value);
                    break;
                case "Ton":
                    convertedValue = convertTonTo(destinationUnit, value);
                    break;
                case "Celsius":
                    convertedValue = convertCelsiusTo(destinationUnit, value);
                    break;
                case "Fahrenheit":
                    convertedValue = convertFahrenheitTo(destinationUnit, value);
                    break;
                case "Kelvin":
                    convertedValue = convertKelvinTo(destinationUnit, value);
                    break;
                case "Centimeter":
                    convertedValue = convertCentimeterTo(destinationUnit, value);
                    break;
                case "Kilometer":
                    convertedValue = convertKilometerTo(destinationUnit, value);
                    break;
                case "Kilogram":
                    convertedValue = convertKilogramTo(destinationUnit, value);
                    break;
                case "Gram":
                    convertedValue = convertGramTo(destinationUnit, value);
                    break;
            }

            // Displaying the converted value
            resultTextView.setText(String.valueOf(convertedValue));
        } catch (NumberFormatException e) {
            // Handling invalid input value
            Toast.makeText(MainActivity.this, "Invalid input value", Toast.LENGTH_SHORT).show();
        }
    }

    // Methods to perform specific unit conversions
    private double convertCentimeterTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Inch":
                return value / 2.54;
            case "Foot":
                return value / 30.48;
            case "Yard":
                return value / 91.44;
            case "Mile":
                return value / 160934;
            case "Kilometer":
                return value / 100000;
        }
        return 0;
    }

    private double convertInchTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Centimeter":
                return value * 2.54;
            case "Foot":
                return value / 12;
            case "Yard":
                return value / 36;
            case "Mile":
                return value / 63360;
            case "Kilometer":
                return value / 39370.1;
        }
        return 0;
    }

    private double convertFootTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Inch":
                return value * 12;
            case "Centimeter":
                return value * 30.48;
            case "Yard":
                return value / 3;
            case "Mile":
                return value / 5280;
            case "Kilometer":
                return value / 3280.84;
        }
        return 0;
    }

    private double convertYardTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Inch":
                return value * 36;
            case "Centimeter":
                return value * 91.44;
            case "Foot":
                return value * 3;
            case "Mile":
                return value / 1760;
            case "Kilometer":
                return value / 1093.61;
        }
        return 0;
    }

    private double convertMileTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Inch":
                return value * 63360;
            case "Centimeter":
                return value * 160934;
            case "Foot":
                return value * 5280;
            case "Yard":
                return value * 1760;
            case "Kilometer":
                return value * 1.60934;
        }
        return 0;
    }

    private double convertKilometerTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Inch":
                return value * 39370.1;
            case "Centimeter":
                return value * 100000;
            case "Foot":
                return value * 3280.84;
            case "Yard":
                return value * 1093.61;
            case "Mile":
                return value / 1.60934;
        }
        return 0;
    }

    private double convertPoundTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Kilogram":
                return value * 0.453592;
            case "Gram":
                return value * 453.592;
            case "Ounce":
                return value * 16;
        }
        return 0;
    }

    private double convertOunceTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Pound":
                return value / 16;
            case "Kilogram":
                return value / 35.274;
            case "Gram":
                return value * 28.3495;
        }
        return 0;
    }

    private double convertTonTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Pound":
                return value * 2000;
            case "Kilogram":
                return value * 907.185;
            case "Gram":
                return value * 907185;
        }
        return 0;
    }

    private double convertKilogramTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Pound":
                return value * 2.20462;
            case "Gram":
                return value * 1000;
            case "Ounce":
                return value * 35.274;
        }
        return 0;
    }

    private double convertGramTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Pound":
                return value / 453.592;
            case "Kilogram":
                return value / 1000;
            case "Ounce":
                return value / 28.3495;
        }
        return 0;
    }

    private double convertCelsiusTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Fahrenheit":
                return (value * 9/5) + 32;
            case "Kelvin":
                return value + 273.15;
        }
        return 0;
    }

    private double convertFahrenheitTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Celsius":
                return (value - 32) * 5/9;
            case "Kelvin":
                return (value - 32) * 5/9 + 273.15;
        }
        return 0;
    }

    private double convertKelvinTo(String destinationUnit, double value) {
        switch (destinationUnit) {
            case "Celsius":
                return value - 273.15;
            case "Fahrenheit":
                return (value - 273.15) * 9/5 + 32;
        }
        return 0;
    }
}
