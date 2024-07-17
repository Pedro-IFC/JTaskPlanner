package endpoint;

import task.TaskDetail;

public class TarefaTeste implements TaskDetail{
	@Override
	public void execute() {
		System.out.println("Teste Com erro . . .  ");
        int[] array = {1, 2, 3};
        System.out.println(array[5]);
	}
}
