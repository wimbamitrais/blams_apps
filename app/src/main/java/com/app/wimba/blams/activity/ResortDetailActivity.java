package com.app.wimba.blams.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.app.wimba.blams.R;
import com.app.wimba.blams.model.Kuliner;
import com.app.wimba.blams.util.KulinerRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResortDetailActivity extends AppCompatActivity {

    private TextView tvKuliner;
    private TextView tvDetailWisataTitle;
    private RecyclerView rvKuliner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_detail);

        tvKuliner = (TextView) findViewById(R.id.kulinerTitle);
        tvKuliner.setText("Kuliner Sekitar " + getIntent().getStringExtra("RESORT_NAME"));

        tvDetailWisataTitle = (TextView) findViewById(R.id.detailWisataTitle);
        tvDetailWisataTitle.setText("Resort ID: " + getIntent().getStringExtra("RESORT_ID") + ", Resort Name: " + getIntent().getStringExtra("RESORT_NAME"));

        rvKuliner = (RecyclerView) findViewById(R.id.rvKuliner);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(ResortDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvKuliner.setLayoutManager(horizontalLayoutManager);
        rvKuliner.setItemAnimator(new DefaultItemAnimator());

        KulinerRVAdapter kulinerRVAdapter = new KulinerRVAdapter(getDummyDataKuliner());
        rvKuliner.setAdapter(kulinerRVAdapter);

    }

    private List<Kuliner> getDummyDataKuliner(){
        List<Kuliner> kuliners = new ArrayList<Kuliner>();

        Kuliner kuliner = new Kuliner();
        for (int i=0; i<10; i++){
            kuliner.setId(i);
            kuliner.setName("Rujak Soto");
            kuliner.setPhoto(R.drawable.pm4);
            kuliners.add(kuliner);
        }
        return kuliners;
    }

}
