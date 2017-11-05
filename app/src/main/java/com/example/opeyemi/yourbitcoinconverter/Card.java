package com.example.opeyemi.yourbitcoinconverter;

/**
 * Created by opeyemi on 10/27/2017.
 */
public class Card {
    private String mCurrencyType; //the name of currency to be displayed on the card
    private double mBitValue; //the current value of bitcoins in currency type
    private double mEthValue; //the current value of litcoin in currency type
    private String mCurrencySymbol; //the symbol of the currency displayed on each card

    public Card(String currencyType,String currencySymbol, double bitValue, double ethValue ){
        mCurrencySymbol = currencySymbol;
        mCurrencyType = currencyType;
        mBitValue = bitValue;
        mEthValue =  ethValue;
    }

    public String getCurrencyType(){
        return mCurrencyType;
    }

    public double getBitValue(){
        return mBitValue;
    }

    public double getEthValue(){
        return mEthValue;
    }

    public String getCurrencySymbol(){
        return mCurrencySymbol;
    }

}
