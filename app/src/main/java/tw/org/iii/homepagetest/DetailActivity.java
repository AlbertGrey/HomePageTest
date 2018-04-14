package tw.org.iii.homepagetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wei-chengni on 2018/4/8.
 */

public class DetailActivity extends AppCompatActivity {
    private TextView name, addr, tel, toldesc;
    private String image;
    private ImageView img;
    private Bitmap bmp;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        uiHandler = new UIHandler();

        name = findViewById(R.id.detail_title);
        addr = findViewById(R.id.detail_address);
        tel = findViewById(R.id.detail_tel);
        toldesc = findViewById(R.id.detail_cont);
        img = findViewById(R.id.detail_image);

        Intent intent =getIntent();
        name.setText(intent.getStringExtra("name"));
        addr.setText(intent.getStringExtra("addr"));
        tel.setText(intent.getStringExtra("tel"));
        toldesc.setText(intent.getStringExtra("toldesc"));


        image = intent.getStringExtra("image");

    }

    private void getImage(final String urlString){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.connect();

                    bmp = BitmapFactory.decodeStream(conn.getInputStream());
                    uiHandler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            img.setImageBitmap(bmp);
        }
    }
}
