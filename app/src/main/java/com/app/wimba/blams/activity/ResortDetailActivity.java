package com.app.wimba.blams.activity;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.wimba.blams.R;
import com.app.wimba.blams.model.Encyclopedia;
import com.app.wimba.blams.model.Kuliner;
import com.app.wimba.blams.util.EncyclopediaRVAdapter;
import com.app.wimba.blams.util.GridViewImageAdapter;
import com.app.wimba.blams.util.HttpConnectionUtil;
import com.app.wimba.blams.util.KulinerRVAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResortDetailActivity extends AppCompatActivity {

    private TextView tvKuliner;
    private TextView tvResortName;
    private TextView tvResortLoc;
    private TextView tvResortDesc;
    private RecyclerView rvKuliner;
    private GridView gvResortImage;
    private ProgressBar progressBar;
    private LinearLayout linearResortDetail;
    private CircleImageView civResortIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.resort_detail_progress);
        civResortIcon = (CircleImageView) findViewById(R.id.civ_resort_detail_icon_photo);
        tvResortName = (TextView) findViewById(R.id.tv_resort_detail_name);
        tvResortLoc = (TextView) findViewById(R.id.tv_resort_detail_location);
        tvResortDesc = (TextView) findViewById(R.id.tv_resort_detail_description);
        linearResortDetail = (LinearLayout) findViewById(R.id.linear_resort_detail);
        tvKuliner = (TextView) findViewById(R.id.kulinerTitle);
        rvKuliner = (RecyclerView) findViewById(R.id.rvKuliner);
        gvResortImage = (GridView) findViewById(R.id.gridView_resort_detail_image);

        civResortIcon.setImageResource(R.drawable.pm3);
        tvKuliner.setText("Kuliner Sekitar " + getIntent().getStringExtra("RESORT_NAME"));

        Integer resortId = Integer.valueOf(getIntent().getStringExtra("RESORT_ID"));
        ResortDetailTask mAuthTask = new ResortDetailTask(resortId);
        mAuthTask.execute(getResources().getString(R.string.server_address) + getResources().getString(R.string.api_wisata_read_by_id));

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(ResortDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvKuliner.setLayoutManager(horizontalLayoutManager);
        rvKuliner.setItemAnimator(new DefaultItemAnimator());

        KulinerRVAdapter kulinerRVAdapter = new KulinerRVAdapter(createDummyDataKuliner());
        rvKuliner.setAdapter(kulinerRVAdapter);

        GridViewImageAdapter gridViewImageAdapter = new GridViewImageAdapter(this, createDummyImageResources());
        gvResortImage.setAdapter(gridViewImageAdapter);

    }

    private List<Kuliner> createDummyDataKuliner(){
        List<Kuliner> kuliners = new ArrayList<Kuliner>();

        Kuliner kuliner = new Kuliner();
        for (int i=0; i<10; i++){
            kuliner.setId(i);
            kuliner.setName("Rujak Soto");
            kuliner.setPhoto(R.drawable.rujaksoto);
            kuliners.add(kuliner);
        }
        return kuliners;
    }

    private List<Integer> createDummyImageResources(){
        List<Integer> imageIDs = new ArrayList<Integer>();

        imageIDs.add(R.drawable.pm1);
        imageIDs.add(R.drawable.pm2);
        imageIDs.add(R.drawable.pm3);
        imageIDs.add(R.drawable.pm4);
        imageIDs.add(R.drawable.pm1);
        imageIDs.add(R.drawable.pm2);
        imageIDs.add(R.drawable.pm3);
        imageIDs.add(R.drawable.pm4);

        return imageIDs;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /*========================*/
    /* Connection Handler */
    /*========================*/

    public class ResortDetailTask extends AsyncTask<String, String, String> {

        private Integer resortId;

        ResortDetailTask(Integer resortId) {
            this.resortId = resortId;
        }

        protected void onPreExecute() {
            showProgressBar();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();
                httpConnectionUtil.setContext(getApplication().getBaseContext());
                connection = httpConnectionUtil.createConnection(params[0]);
                String parameter = "id=" + resortId;

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(parameter);
                out.close();

                InputStream in = new BufferedInputStream(connection.getInputStream());
                String s = readStream(in);

                connection.disconnect();
                return s;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String object) {
            try {
                JSONObject result = new JSONObject(object);

                tvResortName.setText(result.getString("nama"));
                tvResortLoc.setText(result.getString("kategori"));
                tvResortDesc.setText(result.getString("keterangan"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            hideProgressBar();
        }

        @Override
        protected void onCancelled() {
            hideProgressBar();
        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuffer bf=new StringBuffer();
        String line="";
        while ((line = r.readLine())!= null ){
            bf.append(line);
        }
        return bf.toString();
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        linearResortDetail.setVisibility(View.INVISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        linearResortDetail.setVisibility(View.VISIBLE);
    }


}
