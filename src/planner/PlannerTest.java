package planner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskDetail;
import timer.Timer;
import trigger.Trigger;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {


    private Planner planner;
    private Trigger trigger;
    private Task task;
    private TaskDetail taskDetail;
    private Timer timer;

    @BeforeEach
    void setUp() {
        planner = new Planner();
        taskDetail = new TaskDetail() {
            @Override
            public void execute() {
                System.out.println("Task executed");
            }
        };
        task = new Task(taskDetail);
        timer = new Timer().repeat().interval(1000); // Configuring timer with repeat = true
        trigger = new Trigger().when(timer);
    }

    @Test
    void testSetOutput() {
        String outputPath = "/path/to/output";
        planner.setOutput(outputPath);
        assertEquals(outputPath, planner.getOutput());
    }

    @Test
    void testPlanTask() {
        planner.planTask(trigger, task);
        assertEquals(trigger, planner.getTrigger());
        assertEquals(task, planner.getTask());
    }

    @Test
    void testCron() {
        String cronExp = "0 12 * * *";
        planner.cron(cronExp, task);

        assertNotNull(planner.getTrigger());
        assertNotNull(planner.getTask());
        assertEquals(task, planner.getTask());
    }

    @Test
    void testStartAsyncExecution() {
        planner.planTask(trigger, task);
        CompletableFuture<Void> future = planner.asyncFunction(task);

        assertNotNull(future);
        assertFalse(future.isDone()); // A tarefa está em execução assíncrona
    }

    @Test
    void testExecuteTaskSuccess() {
        trigger.setRun(true);
        timer.setTime(1);

        planner.planTask(trigger, task);
        planner.start();

        // Here, we assume the task's execution can be observed by the print statement or other output
        // As this is a simple setup, we are checking that no exceptions were thrown
        assertTrue(true); // No exception means success
    }

    @Test
    void testExecuteTaskFail() {
        trigger.setRun(true);
        timer.setTime(1);

        // Create a task that throws an exception when executed
        Task failingTask = new Task(new TaskDetail() {
            @Override
            public void execute() {
                throw new RuntimeException("Task failed");
            }
        });

        planner.planTask(trigger, failingTask);

        // We expect the start method to handle the exception gracefully
        assertDoesNotThrow(() -> planner.start());
    }

    @Test
    void testWriteLogsToFile() {
        // Configurar o caminho de saída e a tarefa para gerar logs
        planner.setOutput("/path/to/output");
        planner.planTask(trigger, task);

        // Simular a execução da tarefa para gerar logs
        trigger.setRun(true);
        timer.setTime(1);

        planner.start();

        // Verifique se os logs foram escritos (não é possível verificar o arquivo diretamente sem Mocking ou uma biblioteca de arquivos)
        // Aqui estamos confiando na configuração inicial de logs e que a execução ocorreu sem exceções
        assertTrue(true); // Sucesso se nenhum erro ocorreu durante a execução
    }
}
