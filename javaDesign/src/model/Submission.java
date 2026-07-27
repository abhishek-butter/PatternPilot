package model;

import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Abhishek V S
 **/
public class Submission {
    private String dates;
    private String language;
    private String verdict;

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Submission(LocalDateTime date, String language, String verdict) {
        this.dates = date.toString();
        this.language = language;
        this.verdict = verdict;
    }

    public String getDates() {
        return dates;
    }

    public String getLanguage() {
        return language;
    }

    public String getVerdict() {
        return verdict;
    }
}
