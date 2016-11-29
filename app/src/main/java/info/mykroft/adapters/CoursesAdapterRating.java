package info.mykroft.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.mykroft.R;
import info.mykroft.model.Course;
import info.mykroft.views.HomeActivity;
import io.techery.properratingbar.ProperRatingBar;


/**
 * Created by MyKroft on 11/1/2016.
 */

public class CoursesAdapterRating extends RecyclerView.Adapter<CoursesAdapterRating.ViewHolder> {
    private ArrayList<Course> courses = new ArrayList<>();
    private Activity activity;

    public CoursesAdapterRating(Activity activity, ArrayList<Course> courses) {
        this.courses = courses;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.latest_course_row_rating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(courses.get(position).getName());
        holder.ratingBar.setRating(courses.get(position).getRating());
        Picasso.with(activity).load(courses.get(position).getPicture()).into(holder.courseImg);
        //holder..setText("Loader Name: " + courses.get(position).getMonitoringOfficer());
       

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void addData(ArrayList<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView courseImg;
        ProperRatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_of_course);
            courseImg = (ImageView) itemView.findViewById(R.id.img_of_course);
            ratingBar = (ProperRatingBar) itemView.findViewById(R.id.rating);
           
        }
    }
}
