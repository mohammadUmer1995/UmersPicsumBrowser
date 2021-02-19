package com.example.umerspicsumbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ImageViewSingleActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_view_single);
        imageView=findViewById(R.id.img_author);
        tv_name=findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String imageget = intent.getStringExtra("imagekey");
        String name = intent.getStringExtra("name");


        Picasso.with(ImageViewSingleActivity.this).load(imageget).into(imageView);
        tv_name.setText(name);
    }
}
