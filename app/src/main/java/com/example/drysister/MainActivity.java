package com.example.drysister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    private ImageView showImg;
    private ArrayList<String> urls;
    private int curPos = 0;
    private PictureLoader loader;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = new PictureLoader();
        initData();
        initUI();
    }

    private void initData() {
        urls = new ArrayList<>();
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38733_289934.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38741_252498.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38755_184582.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38763_583765.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38771_673943.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38780_708027.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38789_781933.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38801_216938.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38810_491997.jpg");
        urls.add("http://www.sinaimg.cn/dy/slidenews/4_img/2010_13/828_38821_247072.jpg");
    }

    private void initUI() {
        showBtn = (Button) findViewById(R.id.btn_show);
        showImg = (ImageView) findViewById(R.id.img_show);
        showBtn.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            if (curPos > 8) {
                curPos = 0;
            }
            loader.load(showImg, urls.get(curPos));
            curPos++;
        }
    }
}