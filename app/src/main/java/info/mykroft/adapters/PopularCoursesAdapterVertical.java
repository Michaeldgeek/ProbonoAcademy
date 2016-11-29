package info.mykroft.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.mykroft.R;
import info.mykroft.model.Course;
import info.mykroft.utils.Constants;
import info.mykroft.views.CourseActivity;
import io.techery.properratingbar.ProperRatingBar;


/**
 * Created by MyKroft on 11/1/2016.
 */

public class PopularCoursesAdapterVertical extends RecyclerView.Adapter<PopularCoursesAdapterVertical.ViewHolder> {
    private ArrayList<Course> courses = new ArrayList<>();
    private Activity activity;

    public PopularCoursesAdapterVertical(Activity activity, ArrayList<Course> courses) {
        this.courses = courses;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.poplar_course_row_rating_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(courses.get(position).getName());
        holder.ratingBar.setRating(courses.get(position).getRating());
        Picasso.with(activity).load(courses.get(position).getPicture()).into(holder.courseImg);
        holder.cost.setText("Cost: $" + courses.get(position).getCost());
        holder.courseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CourseActivity.class);
                intent.putExtra(Constants.COURSE_NAME, holder.name.getText().toString());
                intent.putExtra(Constants.RATING, holder.ratingBar.getRating());
                intent.putExtra(Constants.COURSE_IMG, courses.get(position).getPicture());
                intent.putExtra(Constants.COURSE_INSTRUCTOR, "Prof. Femi");
                intent.putExtra(Constants.COURSE_DESC, "This course introduces students to the theoretical and practical understanding of the law of contract. The course examines fundamental contract law concepts such as consideration, misrepresentation, and the control of unfair terms, as well as the remedies arising from breach of contract.\n" +
                        "\n" +
                        "COURSE OBJECTIVE\n" +
                        "\n" +
                        "The objectives of this course include:\n" +
                        "\n" +
                        "    1. Introduce the concept and principles of contract law\n" +
                        "    2. Assist students in learning the legal foundations governing the formation, discharge and enforceability of contract\n" +
                        "    3. Familiarise students with the remedial aspects of contractual liability\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "COURSE OUTCOME\n" +
                        "\n" +
                        "At the end of the course, the students would be able to:\n" +
                        "\n" +
                        "    1. Identify the critical elements required to create a legally binding contract enforceable by law\n" +
                        "    2. Recognise issues arising in a contract law and recommend appropriate solution\n" +
                        "    3. Highlight the origin and legal reasoning behind many contract clauses/terminologies\n" +
                        "    4. Prepare, write and negotiate contract arrangements with sufficient protection.\n" +
                        "    5. Understand some of the core contract law doctrines such as sanctity of contract, privity of contract, concept of reasonableness etc.\n" +
                        "    6. Avoid ‘Battle of the Forms’ disputes.\n" +
                        "    7. Recognise the difference between a condition and a warranty and the differing remedies that may apply in the event of a breach of contract.\n" +
                        "    8. Understand the legality of exclusion and limitation clauses.\n" +
                        "    9. Understand contract performance, discharge and breach clauses.\n" +
                        "    10. Accentuate contract vitiating elements such as mistake, misrepresentation, and duress & undue influence\n" +
                        "    11. Recognise the different situations which may lead to early contract termination, such as frustration, repudiation and rescission.\n");
                intent.putExtra(Constants.COURSE_COST, courses.get(position).getCost());
                activity.startActivity(intent);
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
        TextView name;
        ImageView courseImg;
        ProperRatingBar ratingBar;
        TextView cost;
        CardView courseMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_of_course);
            courseImg = (ImageView) itemView.findViewById(R.id.img_of_course);
            ratingBar = (ProperRatingBar) itemView.findViewById(R.id.rating);
            cost = (TextView) itemView.findViewById(R.id.cost);
            cost.setVisibility(View.VISIBLE);
            courseMenu = (CardView) itemView.findViewById(R.id.course_menu);
        }
    }
}
