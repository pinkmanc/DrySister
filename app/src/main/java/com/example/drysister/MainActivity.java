package com.example.drysister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    private Button refreshBtn;
    private ImageView showImg;

    private ArrayList<Unsplash> data;
    private int curPos = 0; //当前显示的是哪一张
    private int page = 1;   //当前页数
    private PictureLoader loader;
    private
    UnsplashApi unsplashApi;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unsplashApi = new UnsplashApi();
        loader = new PictureLoader();
        initData();
        initUI();
    }

    private void initData() {
        data = new ArrayList<>();
        new SisterTask(page).execute();
    }

    private void initUI() {
        showBtn = (Button) findViewById(R.id.btn_show);
        refreshBtn = (Button) findViewById(R.id.btn_refresh);
        showImg = (ImageView) findViewById(R.id.img_show);

        showBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            if(data != null && !data.isEmpty()) {
                if (curPos > 9) {
                    curPos = 0;
                }
                loader.load(showImg, data.get(curPos).getUrl());
                curPos++;
            }
        } else if (v.getId() == R.id.btn_refresh) {
            page++;
            new SisterTask(page).execute();
            curPos = 0;
        }
    }

    private class SisterTask extends AsyncTask<Void,Void,ArrayList<Unsplash>> {

        private int page;

        public SisterTask(int page) {
            this.page = page;
        }

        @Override
        protected ArrayList<Unsplash> doInBackground(Void... params) {
            return unsplashApi.fetchPhotos(10);
        }

        @Override
        protected void onPostExecute(ArrayList<Unsplash> photos) {
            super.onPostExecute(photos);
            data.clear();
            data.addAll(photos);
        }
    }

}