package com.example.drysister;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UnsplashApi {
    private static final String TAG = "Network";
    private static final String ACCESS_KEY = "6I_Cls2BK4WsdccSABayphtKdAeDt-Tr_dXutq_eKDc";
    private static final String BASE_URL = "https://api.unsplash.com/photos/random?client_id=" + ACCESS_KEY;

    /**
     * 查询图片信息
     */
    public ArrayList<Unsplash> fetchPhotos(int count) {
        String fetchUrl = BASE_URL + "&count=" + count;
        ArrayList<Unsplash> photos = new ArrayList<>();
        try {
            URL url = new URL(fetchUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            Log.v(TAG, "Server response：" + code);
            if (code == 200) {
                InputStream in = conn.getInputStream();
                byte[] data = readFromStream(in);
                String result = new String(data, "UTF-8");
                photos = parsePhotos(result);
            } else {
                Log.e(TAG,"请求失败：" + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photos;
    }

    /**
     * 解析返回Json数据的方法
     */
    public ArrayList<Unsplash> parsePhotos(String content) throws Exception {
        ArrayList<Unsplash> photos = new ArrayList<>();
        JSONArray array = new JSONArray(content);
        for (int i = 0; i < array.length(); i++) {
            JSONObject photo = (JSONObject) array.get(i);
            JSONObject urls = photo.getJSONObject("urls");
            String photoUrl = urls.getString("small");
            Unsplash unsplash = new Unsplash();
            unsplash.setUrl(photoUrl);
            photos.add(unsplash);
        }
        return photos;
    }

    /**
     * 读取流中数据的方法
     */
    public byte[] readFromStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len ;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }

}