package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testRename() {
        Task task = new Task().rename("TestTask");
        assertEquals("TestTask", task.getName());
    }

    @Test
    public void testSetTaskDetail() {
        TaskDetail detail = () -> {};
        Task task = new Task().setTask(detail);
        assertEquals(detail, task.getTaskDetail());
    }

    @Test
    public void testSetName() {
        Task task = new Task();
        task.setName("AnotherTask");
        assertEquals("AnotherTask", task.getName());
    }
}
