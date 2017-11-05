package com.example.opeyemi.yourbitcoinconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by opeyemi on 10/30/2017.
 */
public class CardFragment extends Fragment {


    private String currencySymbol; //the currency symbol of each fragment to be passed from Bundle
    private double bitcoinEquivalence; //price of one bitcoin in that currency
    private double ethereumEquivalence; //price of one ethereum to be passsed from the bundle as retrieved


    /*
     *  A method to handle the creation of fragments and the passing of arguments to the fragments
     */
    public static CardFragment newInstance(/*String resCurrencyName,*/ String resCurrenySymbol,
                                           double resBitcoinEquivalence, double resEthereumEquivalence){

        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString("currencySymbol", resCurrenySymbol);
        args.putDouble("bitcoinEquivalence", resBitcoinEquivalence);
        args.putDouble("ethereumEquivalence", resEthereumEquivalence);
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currencySymbol = getArguments().getString("currencySymbol");
        bitcoinEquivalence = getArguments().getDouble("bitcoinEquivalence");
        ethereumEquivalence = getArguments().getDouble("ethereumEquivalence");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.card_fragment_layout,container,false);
        TextView bitValueTextView = (TextView) view.findViewById(R.id.btc_value_textView);
        bitValueTextView.setText(String.format("%.2f",bitcoinEquivalence));

        //inflate the views in each fragment with appropriate set of data
        TextView ethValueTextView = (TextView) view.findViewById(R.id.eth_value_textView);
        ethValueTextView.setText(String.format("%.2f",ethereumEquivalence));

        TextView currencySymbolTextView = (TextView) view.findViewById(R.id.symbol2_textview);
        currencySymbolTextView.setText(currencySymbol);

        TextView currencySymbol2TextView = (TextView) view.findViewById(R.id.symbol_textview);
        currencySymbol2TextView.setText(currencySymbol);

        //give each fragment click listener to the Convert Activity with the right sets of conversion data
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertActivity.btcValue = bitcoinEquivalence;
                ConvertActivity.ethValue = ethereumEquivalence;
                ConvertActivity.currentConversionCurrency = currencySymbol;
                Intent intent = new Intent(getActivity(),ConvertActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
