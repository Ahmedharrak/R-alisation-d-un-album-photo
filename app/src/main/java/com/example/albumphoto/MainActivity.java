package com.example.albumphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    private Button btPrev,btSuiv;
    int imageList[] = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    int count = imageList.length;
    int currentIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPrev = (Button) findViewById(R.id.button);
        btSuiv = (Button) findViewById(R.id.button2);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT
                ));
                return imageView;
            }
        });
        imageSwitcher.setImageResource(imageList[0]);
        btSuiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(MainActivity.this,R.anim.from_right);
                imageSwitcher.setOutAnimation(MainActivity.this,R.anim.to_left);

                --currentIndex;
                if(currentIndex < 0)
                    currentIndex = imageList.length - 1;
                imageSwitcher.setImageResource(imageList[currentIndex]);
            }
        });
        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(MainActivity.this,R.anim.from_left);
                imageSwitcher.setOutAnimation(MainActivity.this,R.anim.to_right);
                currentIndex++;
                if(currentIndex == count)
                    currentIndex = 0;
                imageSwitcher.setImageResource(imageList[currentIndex]);
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        int randInteger = count;
        int[] saveArray = imageList;
        int picNum = imageList[currentIndex];

        savedInstanceState.putInt("picNum", picNum);
        savedInstanceState.putInt("randInteger", randInteger);
        savedInstanceState.putIntArray("picArr", saveArray);
        // etc.

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        int[] picArr =  savedInstanceState.getIntArray("picArr");
        int picNum =  savedInstanceState.getInt("picNum");
        imageList = picArr;
        imageSwitcher.setImageResource(picNum);
    }
}