package cron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

public class CronExpressionTest {

    @Test
    public void testMatches() {
        CronExpression cron = new CronExpression("0 12 * * 1");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.HOUR_OF_DAY, 12);
        date.set(Calendar.DAY_OF_WEEK, 1);
        assertTrue(cron.matches(date));
    }

    @Test
    public void testInvalidCronExpression() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CronExpression("invalid cron expression");
        });
    }

    @Test
    public void testConvertCronExpression() {
        CronExpression cron = new CronExpression("0 12 * * *").convert();
        assertNotNull(cron);
    }
}
