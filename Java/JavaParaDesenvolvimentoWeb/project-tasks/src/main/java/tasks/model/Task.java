package tasks.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Size;

public class Task {
    private Long id;

    @Size(min = 5)
    private String description;
    private boolean concluded;
    private Calendar conclusionDate;

    public Task(){}

    public Task(ResultSet rs) throws SQLException {
        id = rs.getLong("id");
        description = rs.getString("description");
        concluded = rs.getBoolean("concluded");

        // montando a data através do Calendar
        Date date = rs.getTimestamp("conclusionDate");
        if (date == null) {
            conclusionDate = null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            conclusionDate = calendar;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public Calendar getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(Calendar conclusionDate) {
        this.conclusionDate = conclusionDate;
    }
}
