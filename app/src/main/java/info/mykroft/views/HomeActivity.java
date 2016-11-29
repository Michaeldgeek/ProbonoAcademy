package info.mykroft.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import info.mykroft.R;
import info.mykroft.adapters.CategoriesAdapter;
import info.mykroft.adapters.CoursesAdapter;
import info.mykroft.adapters.CoursesAdapterRating;
import info.mykroft.model.Course;
import info.mykroft.utils.Constants;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ImageListener {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.img_2, R.drawable.img_3, R.drawable.cropped_cropped_law};
    private TextView username;
    private NavigationView navigationView;
    private RecyclerView rv;
    private RecyclerView rv2;
    private RecyclerView rv3;
    private CoursesAdapter coursesAdapter;
    private CoursesAdapterRating coursesAdapter2;
    private CategoriesAdapter categoriesAdapter;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> courses2 = new ArrayList<>();
    private ArrayList<Course> courses3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeScreen();
        autenticate();
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawer(toolbar);
        SharedPreferences sharedPreferences = this.getSharedPreferences(Constants.PREF, MODE_PRIVATE);
        username.setText("Hello " + sharedPreferences.getString(Constants.USERNAME, null));
        rv = (RecyclerView) findViewById(R.id.rv);
        rv2 = (RecyclerView) findViewById(R.id.rv2);
        rv3 = (RecyclerView) findViewById(R.id.rv3);
        fetchData();
        coursesAdapter = new CoursesAdapter(this, courses);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(coursesAdapter);
        fetchData2();
        coursesAdapter2 = new CoursesAdapterRating(this, courses2);
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv2.setAdapter(coursesAdapter2);
        fetchData3();
        categoriesAdapter = new CategoriesAdapter(this, courses3);
        rv3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv3.setAdapter(categoriesAdapter);
        carouselView = (CarouselView) findViewById(R.id.carousel_view);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(this);
    }

    private void homeScreen() {
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }

    private void fetchData3() {
        Course course = new Course();
        course.setLevel("100L");
        Course course2 = new Course();
        course2.setLevel("200L");
        Course course3 = new Course();
        course3.setLevel("300L");
        Course course4 = new Course();
        course4.setLevel("400L");
        Course course5 = new Course();
        course5.setLevel("500L");
        Course course6 = new Course();
        course6.setLevel("600L");
        courses3.add(course);
        courses3.add(course2);
        courses3.add(course3);
        courses3.add(course4);
        courses3.add(course5);
        courses3.add(course6);
    }


    private void fetchData() {
        Course course = new Course();
        course.setName("FAMILY LAW (UG)");
        course.setPicture(R.drawable.legal_law);
        Course course1 = new Course();
        course1.setName("FAMILY LAW (UG)");
        course1.setPicture(R.drawable.criminal_law);
        Course course2 = new Course();
        course2.setName("FAMILY LAW (UG)");
        course2.setPicture(R.drawable.family_law);
        Course course3 = new Course();
        course3.setName("FAMILY LAW (UG)");
        course3.setPicture(R.drawable.gear_law);
        courses.add(course);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);


    }

    private void fetchData2() {
        Course course = new Course();
        course.setName("FAMILY LAW (UG)");
        course.setPicture(R.drawable.legal_law);
        course.setRating(3);
        Course course1 = new Course();
        course1.setName("FAMILY LAW (UG)");
        course1.setRating(4);
        course1.setPicture(R.drawable.criminal_law);
        Course course2 = new Course();
        course2.setName("FAMILY LAW (UG)");
        course2.setRating(5);
        course2.setPicture(R.drawable.family_law);
        Course course3 = new Course();
        course3.setName("FAMILY LAW (UG)");
        course3.setRating(1);
        course3.setPicture(R.drawable.gear_law);
        courses2.add(course);
        courses2.add(course1);
        courses2.add(course2);
        courses2.add(course3);
    }

    private void setupDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LinearLayout linearLayout = (LinearLayout) navigationView.getHeaderView(0);
        username = (TextView) linearLayout.findViewById(R.id.username);
    }

    private void autenticate() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(Constants.PREF, MODE_PRIVATE);
        String username = sharedPreferences.getString(Constants.USERNAME, null);
        if (username == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        imageView.setImageResource(sampleImages[position]);
    }
}
