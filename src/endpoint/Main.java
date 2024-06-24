package endpoint;

import planner.Planner;
import trigger.Trigger;
import trigger.TriggerBuilder;
import task.Task;
import task.TaskBuilder;
import timer.TimerBuilder;

public class Main {

	public static void main(String[] args) {
		Trigger trigger = TriggerBuilder.newTrigger()
			.rename("1 sec")
			.start()
			.when(
				TimerBuilder.newTimer()
				.interval(1000)
				.repeat(3)
			);
		Task task = TaskBuilder.newTask()
			.rename("Tarefa 01")
			.setTask(new TarefaTeste());
		
		Planner plan = new Planner();
		plan.setOutput("C:\\Users\\Pedro\\Documents");
		plan.cron("1 * * * *", task);
		plan.start();
		
		System.out.println("Aqui roda normal");
		
		Planner plan2 = new Planner();
		plan2.setOutput("C:\\Users\\Pedro\\Documents");
		plan2.planTask(trigger, task);
		plan2.start();
		//fazer com planBuilder pq aí dá de usar assyncFunc
	}

}
