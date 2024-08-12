package trigger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import timer.Timer;

public class TriggerTest {

    @Test
    public void testRename() {
        Trigger trigger = new Trigger().rename("TestTrigger");
        assertEquals("TestTrigger", trigger.getName());
    }

    @Test
    public void testStart() {
        Trigger trigger = new Trigger().start();
        assertTrue(trigger.isRun());
    }

    @Test
    public void testWhen() {
        Timer timer = new Timer();
        Trigger trigger = new Trigger().when(timer);
        assertEquals(timer, trigger.getTimer());
    }

    @Test
    public void testSetName() {
        Trigger trigger = new Trigger();
        trigger.setName("AnotherName");
        assertEquals("AnotherName", trigger.getName());
    }

    @Test
    public void testSetRun() {
        Trigger trigger = new Trigger();
        trigger.setRun(true);
        assertTrue(trigger.isRun());
    }

    @Test
    public void testSetTimer() {
        Timer timer = new Timer();
        Trigger trigger = new Trigger();
        trigger.setTimer(timer);
        assertEquals(timer, trigger.getTimer());
    }
}
