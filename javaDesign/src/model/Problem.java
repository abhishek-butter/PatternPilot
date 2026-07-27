package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abhishek V S
 **/
public class Problem {
    private String Title;
    private String Difficulty;
    private String Url;
    private List<Submission> submissions;

    public Problem(String title, String difficulty, String url) {
        Title = title;
        Difficulty = difficulty;
        Url = url;
        submissions = new ArrayList<>();
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

    public List<Submission> getSubmission() {
        return submissions;
    }

    public void setSubmission(LocalDateTime date,String language,String verdict) {
        submissions.add(new Submission(date, language, verdict));
    }
}
