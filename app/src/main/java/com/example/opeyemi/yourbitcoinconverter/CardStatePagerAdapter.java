package com.example.opeyemi.yourbitcoinconverter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by opeyemi on 10/30/2017.
 */

public class CardStatePagerAdapter extends FragmentStatePagerAdapter {


    private static final int NUM_ITEMS = 20;
    private static Card[] cards;

    public CardStatePagerAdapter(FragmentManager fm, Card[] cards){
        super(fm);
        this.cards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        double bitcoinEquivalence = cards[position].getBitValue();
        double ethereumEquivalence = cards[position].getEthValue();
        String currencySymbol = cards[position].getCurrencySymbol();
                 return  CardFragment.newInstance(currencySymbol,
                         bitcoinEquivalence,ethereumEquivalence);

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String currencyName = cards[position].getCurrencyType();
        return currencyName.toUpperCase();
    }
}
