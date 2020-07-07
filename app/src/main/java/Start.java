package com.atmnirbharbharat.photoeditor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    Button button,camera;
    ImageView photo;
    String source = null;
    private static final int PICK_REQUEST = 53;
    private static final int CAMERA_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);


        button = findViewById(R.id.button);

        camera =findViewById(R.id.button2);

        //photo = findViewById(R.id.picture);


        source = null;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source = "Gallary";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_REQUEST);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                source = "Camera";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_REQUEST) {

                //     imageView.clearAllViews();
                Uri uri = data.getData();
                // Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("source", source);
                intent.putExtra("image", uri.toString());
                startActivity(intent);
                finish();


            }

            if(requestCode == CAMERA_REQUEST){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("source",source);
                intent.putExtra("image",bitmap);
                startActivity(intent);
                finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
