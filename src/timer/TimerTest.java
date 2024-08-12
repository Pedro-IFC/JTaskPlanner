package timer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

    @Test
    public void testInterval() {
        Timer timer = new Timer().interval(60);
        assertEquals(60, timer.getTime());
    }

    @Test
    public void testRepeat() {
        Timer timer = new Timer().repeat();
        assertTrue(timer.isRepeat());
    }

    @Test
    public void testRepeatWithTimes() {
        Timer timer = new Timer().repeat(5);
        assertTrue(timer.isRepeat());
        assertEquals(5, timer.getRepTimes());
    }

    @Test
    public void testCron() {
        String cronExp = "0 12 * * 1";
        Timer timer = new Timer().cron(cronExp);
        assertNotNull(timer.getCron());
    }

    @Test
    public void testMatchTime() {
        Timer timer = new Timer();
        assertTrue(timer.matchTime());
    }

    @Test
    public void testSetTime() {
        Timer timer = new Timer();
        timer.setTime(120);
        assertEquals(120, timer.getTime());
    }

    @Test
    public void testSetRepeat() {
        Timer timer = new Timer();
        timer.setRepeat(true);
        assertTrue(timer.isRepeat());
    }

    @Test
    public void testSetRepTimes() {
        Timer timer = new Timer();
        timer.setRepTimes(3);
        assertEquals(3, timer.getRepTimes());
    }
}
