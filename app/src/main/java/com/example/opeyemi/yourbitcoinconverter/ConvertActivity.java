package com.example.opeyemi.yourbitcoinconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity {

    //getting the edit text objects
    EditText currencyEditText;
    EditText btcEditText;
    EditText ethEditText;
    TextView currencyTextView;

    private TextWatcher textWatcher;

    public static double btcValue = 0;
    public static double ethValue = 0;
    public static String currentConversionCurrency = ""; //symbol of the current currency used in the conversion



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        currencyTextView = (TextView) findViewById(R.id.currency_symbol_text_view);
        currencyTextView.setText(currentConversionCurrency);
        currencyEditText =(EditText) findViewById(R.id.currency_edit_text);
        btcEditText =(EditText) findViewById(R.id.btc_edit_text);
        ethEditText =(EditText) findViewById(R.id.eth_edit_text);


        //instantiating the textWatcher to
        textWatcher  = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //compare the hash of the Charsequences passed to content of
                // the edit text to ascertain which edit is been typed on

                if (currencyEditText.getText().hashCode() == s.hashCode())
                {
                    currencyEditText_onTextChanged(s, start, before, count);
                }else if (btcEditText.getText().hashCode() == s.hashCode())
                {
                    btcEditText_onTextChanged(s, start, before, count);
                }else if (ethEditText.getText().hashCode() == s.hashCode())
                {
                    ethEditText_onTextChanged(s, start, before, count);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        currencyEditText.addTextChangedListener(textWatcher);
        btcEditText.addTextChangedListener(textWatcher);
        ethEditText.addTextChangedListener(textWatcher);
    }

    //method to handle change in text inside the currency edit text
    private void currencyEditText_onTextChanged(CharSequence s, int start, int before, int count){
        String currencyText = s.toString();
        btcEditText.removeTextChangedListener(textWatcher);
        ethEditText.removeTextChangedListener(textWatcher);
        if(!currencyText.isEmpty()) {

            double currentCurrency = Double.parseDouble(currencyText);
            btcEditText.setText(String.format("%.8f", currentCurrency / btcValue));
            ethEditText.setText(String.format("%.8f", currentCurrency / ethValue));
        }else{

            btcEditText.setText("");
            ethEditText.setText("");

        }
        btcEditText.addTextChangedListener(textWatcher);
        ethEditText.addTextChangedListener(textWatcher);

    }

    //method to handle change in the btc edit text
    private void btcEditText_onTextChanged(CharSequence s, int start, int before, int count){
        String btcText = s.toString();
        currencyEditText.removeTextChangedListener(textWatcher);
        ethEditText.removeTextChangedListener(textWatcher);
        if (!btcText.isEmpty()) {

            double currentBitValue = Double.parseDouble(btcText);
            currencyEditText.setText(String.format("%.2f", currentBitValue * btcValue));
            ethEditText.setText(String.format("%.8f", ((currentBitValue * btcValue)/ ethValue)));
        }else{

            currencyEditText.setText("");
            ethEditText.setText("");

        }
        currencyEditText.addTextChangedListener(textWatcher);
        ethEditText.addTextChangedListener(textWatcher);


    }

    //method to handle change in the eth edit text
    private void ethEditText_onTextChanged(CharSequence s, int start, int before, int count){
        String ethText = s.toString();
        currencyEditText.removeTextChangedListener(textWatcher);
        btcEditText.removeTextChangedListener(textWatcher);

        if (!ethText.isEmpty()){
            double currentEthValue = Double.parseDouble(ethText);
            currencyEditText.setText(String.format("%.2f", currentEthValue * ethValue));
            btcEditText.setText(String.format("%.8f", (currentEthValue * ethValue)/btcValue));
        }else{
            currencyEditText.setText("");
            btcEditText.setText("");
        }
        currencyEditText.addTextChangedListener(textWatcher);
        btcEditText.addTextChangedListener(textWatcher);
    }

}
