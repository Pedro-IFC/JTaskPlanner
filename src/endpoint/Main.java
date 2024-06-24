package endpoint;

import planner.PlanBuilder;
import trigger.Trigger;
import trigger.TriggerBuilder;
import task.Task;
import task.TaskBuilder;
import timer.TimerBuilder;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Trigger trigger = TriggerBuilder.newTrigger()
			.rename("1 sec")
			.start()
			.when(
				TimerBuilder.newTimer()
				.interval(1000)
				.repeat(3)
			);
		Trigger trigger2 = TriggerBuilder.newTrigger()
				.rename("2 sec")
				.start()
				.when(
					TimerBuilder.newTimer()
					.interval(3000)
					.repeat(3)
				);
		Task task = TaskBuilder.newTask()
			.rename("Cron")
			.setTask(new TarefaTeste());

		PlanBuilder.newPlanner()
			.setOutput("/home/pedro/")
			.planTask(trigger, task)
			.start();
		
//		PlanBuilder.newPlanner()
//			.setOutput("/home/pedro/")
//			.cron("1 * * * *", task)
//			.start();
		

		Task task2 = TaskBuilder.newTask()
				.rename("Timer")
				.setTask(new TarefaTeste2());
		PlanBuilder.newPlanner()
			.setOutput("/home/pedro/")
			.planTask(trigger2, task2)
			.start();
	}

}
