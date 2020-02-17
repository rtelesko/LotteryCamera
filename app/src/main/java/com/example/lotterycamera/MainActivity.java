package com.example.lotterycamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // Define the pic id
    private static final int pic_id = 123;

    // Define the Button and ImageView type variables
    Button btCamera;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // By ID we can get each component
        // which id is assigned in XML file
        // get Buttons and ImageView.
        btCamera = findViewById(R.id.btCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        // Camera_open button is for open the camera
        // and add the setOnClickListener in this button
        btCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create the camera_intent ACTION_IMAGE_CAPTURE
                // it will open the camera for capture the image
                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);

                // Start the activity with camera_intent,
                // and request pic id
                if (camera_intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(camera_intent, pic_id);
            }
        });
    }

    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Match the request 'pic id" with requestCode
        if (requestCode == pic_id && resultCode == RESULT_OK) {
            // BitMap is a data structure of image file
            // which stores the image in memory
            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");

            // Set the image in ImageView for display
            ivPhoto.setImageBitmap(photo);
        }
    }
}
