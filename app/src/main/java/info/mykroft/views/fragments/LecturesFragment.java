package info.mykroft.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.mykroft.R;
import info.mykroft.utils.Constants;
import info.mykroft.views.MainCourseActivity;
import info.mykroft.views.VideoPlayerActivity;

/**
 * Created by MyKroft on 9/14/2016.
 */
public class LecturesFragment extends Fragment {
    TextView name;
    TextView duration;
    CardView play;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture, container, false);
        name = (TextView) view.findViewById(R.id.name);
        duration = (TextView) view.findViewById(R.id.duration);
        name.setText("Introduction to Misrepresentation (01:24)");
        duration.setText("");
        play = (CardView)view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoPlayer();
            }
        });
        return view;
    }

    private void openVideoPlayer() {
        getActivity().startActivity(new Intent(getActivity(), VideoPlayerActivity.class));
    }

}
