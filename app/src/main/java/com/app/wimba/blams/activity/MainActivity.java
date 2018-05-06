package com.app.wimba.blams.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.wimba.blams.R;
import com.app.wimba.blams.util.EncyclopediaRVAdapter;
import com.app.wimba.blams.model.Encyclopedia;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Encyclopedia> encyclopedias = new ArrayList<>();
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_upload:
                    mTextMessage.setText(R.string.title_upload);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        encyclopedias = getDummyData();
//
//        EncyclopediaRVAdapter rvAdapter = new EncyclopediaRVAdapter(encyclopedias);
//        recyclerView.setAdapter(rvAdapter);

        WisataTask mAuthTask = new WisataTask(4, 0);
        mAuthTask.execute(getResources().getString(R.string.server_address) + getResources().getString(R.string.api_wisata_read));

    }

/*    private List<Encyclopedia> getDummyData(){
        List<Encyclopedia> contents = new ArrayList<Encyclopedia>();

        List<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.pm1);
        images.add(R.drawable.pm2);
        images.add(R.drawable.pm3);
        images.add(R.drawable.pm4);
        images.add(R.drawable.pm2);

        Encyclopedia content = new Encyclopedia("Pulau Merah", "Kecamatan Pesanggaran", images);

        contents.add(content);
        contents.add(content);
        contents.add(content);
        contents.add(content);
        contents.add(content);

        return contents;
    }*/

    public class WisataTask extends AsyncTask<String, String, String> {

        private Integer per_page;
        private Integer segment;

        WisataTask(Integer per_page, Integer segment) {
            this.per_page = per_page;
            this.segment = segment;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", getResources().getString(R.string.content_type));
                connection.addRequestProperty("Client-Service",getResources().getString(R.string.client_service));
                connection.addRequestProperty("Auth-Key", getResources().getString(R.string.auth_key));
                connection.setDoOutput(true);

                String parameter = "per_page="+per_page+"&segment="+segment;

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
                Integer firstIndex = segment*per_page;
                for (int i=firstIndex; i<=firstIndex+per_page-1; i++){
                    JSONObject jsonEncyclopedia = result.getJSONObject(String.valueOf(i));
                    Encyclopedia encyclopedia = new Encyclopedia();
                    encyclopedia.setId(jsonEncyclopedia.getLong("idwisata"));
                    encyclopedia.setTitle(jsonEncyclopedia.getString("nama"));
                    encyclopedia.setLocation(jsonEncyclopedia.getString("kategori"));
                    encyclopedia.setDescription(jsonEncyclopedia.getString("keterangan"));

                    List<Integer> images = new ArrayList<Integer>();
                        images.add(R.drawable.pm1);
                    images.add(R.drawable.pm2);
                    images.add(R.drawable.pm3);
                    images.add(R.drawable.pm4);
                    images.add(R.drawable.pm2);

                    encyclopedia.setImages(images);
                    encyclopedia.setLikes(jsonEncyclopedia.getInt("rating"));
                    encyclopedia.setComments(jsonEncyclopedia.getInt("jumlah_perating"));
                    encyclopedias.add(encyclopedia);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            EncyclopediaRVAdapter rvAdapter = new EncyclopediaRVAdapter(encyclopedias);
            recyclerView.setAdapter(rvAdapter);
        }

        @Override
        protected void onCancelled() {
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


}
