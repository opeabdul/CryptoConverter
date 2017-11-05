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


    private String currencySymbol;
    private double bitcoinEquivalence;
    private double ethereumEquivalence;

    private View.OnClickListener onClickListener;


    public static CardFragment newInstance(/*String resCurrencyName,*/ String resCurrenySymbol,
                                           double resBitcoinEquivalence, double resEthereumEquivalence){

        //Creatng a new fragment and adding
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

        TextView ethValueTextView = (TextView) view.findViewById(R.id.eth_value_textView);
        ethValueTextView.setText(String.format("%.2f",ethereumEquivalence));



        TextView currencySymbolTextView = (TextView) view.findViewById(R.id.symbol2_textview);
        currencySymbolTextView.setText(currencySymbol);

        TextView currencySymbol2TextView = (TextView) view.findViewById(R.id.symbol_textview);
        currencySymbol2TextView.setText(currencySymbol);

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
