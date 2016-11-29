package info.mykroft.views;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import info.mykroft.R;
import info.mykroft.utils.Constants;
import io.techery.properratingbar.ProperRatingBar;

public class CourseActivity extends AppCompatActivity {
    private String nameofCourse;
    private ImageView pic;
    private TextView nameOfCourse;
    private TextView nameOfInstructor;
    private ProperRatingBar ratingBar;
    private TextView courseDesc;
    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        if (intent == null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            nameofCourse = intent.getStringExtra(Constants.COURSE_NAME);
            if (nameofCourse == null) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        }
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nameofCourse);
        nameOfCourse = (TextView) findViewById(R.id.name_of_course);
        nameOfInstructor = (TextView) findViewById(R.id.name_of_instructor);
        courseDesc = (TextView) findViewById(R.id.course_desc);
        ratingBar = (ProperRatingBar) findViewById(R.id.rating);
        buy = (Button) findViewById(R.id.buy);
        pic = (ImageView) findViewById(R.id.course_img);
        nameOfCourse.setText(intent.getStringExtra(Constants.COURSE_NAME));
        nameOfInstructor.setText(intent.getStringExtra(Constants.COURSE_INSTRUCTOR));
        courseDesc.setText(intent.getStringExtra(Constants.COURSE_DESC));
        buy.setText("BUY ($" + intent.getIntExtra(Constants.COURSE_COST, 0) + ")");
        ratingBar.setRating(intent.getIntExtra(Constants.RATING, 0));
        Picasso.with(this).load(intent.getIntExtra(Constants.COURSE_IMG, 0)).into(pic);
        setSupportActionBar(toolbar);
        setParentNavigation();
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy(intent.getIntExtra(Constants.COURSE_COST, 0));
            }
        });
    }

    private void buy(int cost) {
        new MaterialDialog.Builder(this)
                .title("Make a purhase")
                .content("You are about to make a purchase for " + nameOfCourse.getText().toString() + " at the cost of " + cost + " dollars")
                .positiveText("Agree")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(getActivity(),MainCourseActivity.class);
                        intent.putExtra(Constants.COURSE_NAME,intent.getStringExtra(Constants.COURSE_NAME));
                        getActivity().startActivity(intent);
                    }
                })
                .show();
    }

    private CourseActivity getActivity() {
        return this;
    }


    private void setParentNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
