package cron;
import java.util.Calendar;

public class CronExpression {
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;

    public CronExpression(String expression) {
        String[] fields = expression.split(" ");
        if (fields.length != 5) {
            throw new IllegalArgumentException("Invalid cron expression");
        }
        this.minute = fields[0];
        this.hour = fields[1];
        this.dayOfMonth = fields[2];
        this.month = fields[3];
        this.dayOfWeek = fields[4];
    }

    public boolean matches(Calendar date) {
        return matchesField(minute, date.get(Calendar.MINUTE))
            && matchesField(hour, date.get(Calendar.HOUR_OF_DAY))
            && matchesField(dayOfMonth, date.get(Calendar.DAY_OF_MONTH))
            && matchesField(month, date.get(Calendar.MONTH) + 1)
            && matchesField(dayOfWeek, date.get(Calendar.DAY_OF_WEEK));
    }

    private boolean matchesField(String field, int value) {
        if (field.equals("*")) {
            return true;
        } else {
            String[] parts = field.split(",");
            for (String part : parts) {
                if (part.equals(String.valueOf(value))) {
                    return true;
                }
            }
            return false;
        }
    }
}
