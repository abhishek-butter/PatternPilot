package model;

import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Abhishek V S
 **/
public class Submissions {
    private List<String> dates;

    public Submissions() {
        this.dates = new ArrayList<>();
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(LocalDateTime date) {
        this.dates.add(date.toString());
    }
}
