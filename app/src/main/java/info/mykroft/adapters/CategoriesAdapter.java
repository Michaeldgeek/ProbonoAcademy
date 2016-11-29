package info.mykroft.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import info.mykroft.R;
import info.mykroft.model.Course;
import info.mykroft.views.HomeActivity;
import info.mykroft.views.L200Activity;


/**
 * Created by MyKroft on 11/1/2016.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private ArrayList<Course> courses = new ArrayList<>();
    private Activity activity;

    public CategoriesAdapter(Activity activity, ArrayList<Course> courses) {
        this.courses = courses;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.categories_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.more.setText(courses.get(position).getLevel());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.more.getText().toString().contentEquals("200L")){
                    Intent intent = new Intent(activity, L200Activity.class);
                    activity.startActivity(intent);
                }
                else {

                }
            }
        });
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
        Button more;

        public ViewHolder(View itemView) {
            super(itemView);
            more = (Button) itemView.findViewById(R.id.more);

        }
    }
}
