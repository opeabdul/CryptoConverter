package com.example.opeyemi.yourbitcoinconverter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Card[]> {
    Spinner spinner; //spinner containing the list of all currencies
    ViewPager viewPager; //viewpager object for displaying sliding cards onscreen
    ArrayAdapter<CharSequence> spinnerAdapter;
    CardStatePagerAdapter pagerAdapter; //adapter for the view pager
    ProgressBar progressBar;
    //pager tab strip holding the header of each fragment
    PagerTabStrip pagerTabStrip;
    TextView emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //check for suitable  connection before continuing to load the data into the field
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected()){
            getLoaderManager().initLoader(1,null,this).forceLoad();
        } else {
            //Hide the progress bar
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);


            //Create an empty state textView to display if there is no internet connection
            emptyView = (TextView) findViewById(R.id.no_values_text_view);
            emptyView.setText("No internet connection");
        }


        //create spinner - set adapter and set the item click listener
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,UtilityClass.currencies);
        spinner.setAdapter(spinnerAdapter);



    }



    @Override
    public Loader<Card[]> onCreateLoader(int id, Bundle args) {
        return new CardsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<Card[]> loader, Card[] data) {
        //Hide the progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        //instantiating the viewPager, pagerTabStrip views from the main activity and View pager adapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new CardStatePagerAdapter(getSupportFragmentManager(),data);
        viewPager.setAdapter(pagerAdapter);

        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.DKGRAY);
        */
    }

    @Override
    public void onLoaderReset(Loader<Card[]> loader) {

    }
}