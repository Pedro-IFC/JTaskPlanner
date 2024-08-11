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
				.rename("Nova tarefa")
				.when(
					TimerBuilder.newTimer()
					.interval(1000)
					.repeat(10)
				);
		
		Task task = TaskBuilder.newTask()
			.rename("Tarefa de teste 18h mes 8")
			.setTask(new TarefaTeste());

		System.out.println("Inicio");
		
		PlanBuilder.newPlanner()
			.setOutput("./")
			.cron("* 9 * * *", task)
			.start();
		
		
		PlanBuilder.keepRunning();
	}

}
