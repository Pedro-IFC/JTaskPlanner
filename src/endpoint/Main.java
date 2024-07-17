package endpoint;

import java.util.Calendar;

import planner.PlanBuilder;
import trigger.Trigger;
import trigger.TriggerBuilder;
import task.Task;
import task.TaskBuilder;
import timer.TimerBuilder;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		
		Trigger trigger2 = TriggerBuilder.newTrigger()
				.rename("New")
				.when(
					TimerBuilder.newTimer()
					.interval(1000)
					.repeat(10)
				);
		Task task = TaskBuilder.newTask()
			.rename("Task")
			.setTask(new TarefaTeste());

		PlanBuilder.newPlanner()
			.setOutput("./")
			.planTask(trigger2, task)
			.start();
		System.out.println("teste");
		PlanBuilder.keepRunning();
	}

}
