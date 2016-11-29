package info.mykroft.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import info.mykroft.R;

public class IntroActivity extends AppCompatActivity {
    private Typeface typefaceLight;
    private Typeface typefaceBold;
    private TextView name;
    private ImageView logo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        intent = getIntent();
        typefaceLight = Typeface.createFromAsset(this.getAssets(), "fonts/roboto-light.ttf");
        typefaceBold = Typeface.createFromAsset(this.getAssets(), "fonts/roboto-medium.ttf");
        name = (TextView)findViewById(R.id.name);
        logo = (ImageView)findViewById(R.id.logo);
        name.setTypeface(typefaceBold);
    }
}
