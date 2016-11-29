package info.mykroft.model;

/**
 * Created by MyKroft on 11/18/2016.
 */

public class Course {

    private Integer picture;
    private String name;
    private String review;
    private Integer rating;
    private String level;
    private Integer cost;

    public Course(){

    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    public Integer getCost() {
        return cost;
    }

    public String getReview() {
        return review;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
