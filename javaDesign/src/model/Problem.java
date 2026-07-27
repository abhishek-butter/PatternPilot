package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Abhishek V S
 **/
public class Problem {
    private String Title;
    private String Difficulty;
    private String Url;
    private Submissions submissions;

    public Problem(String title, String difficulty, String url) {
        Title = title;
        Difficulty = difficulty;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Submissions getSubmissions() {
        return submissions;
    }

    public void setSubmissions(LocalDateTime date) {
        submissions.setDates(date);
    }
}
