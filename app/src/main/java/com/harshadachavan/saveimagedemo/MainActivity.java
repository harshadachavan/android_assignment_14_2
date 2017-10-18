package com.harshadachavan.saveimagedemo;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Create an actionbar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.show();

        // Locate ImageView in activity_main.xml
        ImageView myimage = (ImageView) findViewById(R.id.image);

        // Attach image into ImageView
        myimage.setImageResource(R.drawable.puppy);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Create an actionbar menu
        menu.add("Save Image")
                // Add a new Menu Button
                .setOnMenuItemClickListener(this.SaveImageClickListener)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    // Capture actionbar menu item click
    OnMenuItemClickListener SaveImageClickListener = new OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem item) {

            // TODO Auto-generated method stub

            Bitmap bitmap;
            OutputStream output;

            // Retrieve the image from the res folder
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.puppy);

            // Find the SD Card path
            File filepath = Environment.getExternalStorageDirectory();

            // Create a new folder in SD Card
            File dir = new File(filepath , "SaveImage");
            dir.mkdirs();

            // Create a name for the saved image
            File file = new File(dir, "myimage.jpg");

            // Show a toast message on successful save
            Toast.makeText(MainActivity.this, "Image Saved to External Storage",
                    Toast.LENGTH_SHORT).show();
            try {

                output = new FileOutputStream(file);

                // Compress into png format image from 0% - 100%
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();
            }

            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
    };
}
