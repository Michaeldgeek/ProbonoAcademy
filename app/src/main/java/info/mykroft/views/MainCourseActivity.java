package info.mykroft.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import info.mykroft.R;
import info.mykroft.utils.Constants;
import info.mykroft.views.fragments.DiscussionFragment;
import info.mykroft.views.fragments.LecturesFragment;
import info.mykroft.views.fragments.QuestionFragment;

public class MainCourseActivity extends AppCompatActivity {

    public String course;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView tabTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course);
        bindViews();
        course = getIntent().getStringExtra(Constants.COURSE_NAME);
        setupViewPager();
        setupActionBar();
    }

    public String getCourse() {
        return course;
    }

    private void bindViews() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void setupViewPager() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    private MainCourseActivity getActivity() {
        return this;
    }

    private void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                LecturesFragment lecturesFragment = new LecturesFragment();
                return lecturesFragment;
            } else if (position == 1) {
                QuestionFragment questionFragment = new QuestionFragment();
                return questionFragment;
            } else {
                DiscussionFragment discussionFragment = new DiscussionFragment();
                return discussionFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Lectures";
            } else if (position == 1) {
                return "Q & A";
            } else {
                return "Discussions";
            }
        }
    }


}
