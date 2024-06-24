package endpoint;

import task.TaskDetail;

public class TarefaTeste implements TaskDetail{
	@Override
	public void execute() {
		System.out.println("Teste Cron . . .  ");
	}
}
