package info.mykroft.views;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import info.mykroft.R;
import info.mykroft.adapters.PopularCoursesAdapter;
import info.mykroft.adapters.PopularCoursesAdapterVertical;
import info.mykroft.model.Course;

public class L200Activity extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView rv2;
    private PopularCoursesAdapter coursesAdapterRating;
    private PopularCoursesAdapterVertical coursesAdapterRating2;
    private ArrayList<Course> courses2 = new ArrayList<>();
    private ArrayList<Course> courses3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l200);
        setParentNavigation();
        rv = (RecyclerView) findViewById(R.id.rv);
        rv2 = (RecyclerView) findViewById(R.id.rv2);
        fetchData2();
        fetchData3();
        coursesAdapterRating = new PopularCoursesAdapter(this, courses2);
        coursesAdapterRating2 = new PopularCoursesAdapterVertical(this, courses3);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(coursesAdapterRating);
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv2.setAdapter(coursesAdapterRating2);
    }

    private void setParentNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

    private void fetchData2() {
        Course course = new Course();
        course.setName("FAMILY LAW (UG)");
        course.setPicture(R.drawable.legal_law);
        course.setCost(100);
        course.setRating(3);
        Course course1 = new Course();
        course1.setName("FAMILY LAW (UG)");
        course1.setRating(4);
        course1.setCost(100);
        course1.setPicture(R.drawable.criminal_law);
        Course course2 = new Course();
        course2.setName("FAMILY LAW (UG)");
        course2.setRating(5);
        course2.setCost(100);
        course2.setPicture(R.drawable.family_law);
        Course course3 = new Course();
        course3.setName("FAMILY LAW (UG)");
        course3.setRating(1);
        course3.setCost(100);
        course3.setPicture(R.drawable.gear_law);
        courses2.add(course);
        courses2.add(course1);
        courses2.add(course2);
        courses2.add(course3);
    }
    private void fetchData3() {
        Course course = new Course();
        course.setName("CONTRACT LAW (UG)");
        course.setPicture(R.drawable.justice_logo);
        course.setCost(100);
        course.setRating(3);
        Course course1 = new Course();
        course1.setName("FAMILY LAW (UG)");
        course1.setRating(4);
        course1.setCost(100);
        course1.setPicture(R.drawable.criminal_law);
        Course course2 = new Course();
        course2.setName("FAMILY LAW (UG)");
        course2.setRating(5);
        course2.setCost(100);
        course2.setPicture(R.drawable.family_law);
        Course course3 = new Course();
        course3.setName("FAMILY LAW (UG)");
        course3.setRating(1);
        course3.setCost(100);
        course3.setPicture(R.drawable.gear_law);
        courses3.add(course);
        courses3.add(course1);
        courses3.add(course2);
        courses3.add(course3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
