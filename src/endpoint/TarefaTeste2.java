package endpoint;

import task.TaskDetail;

public class TarefaTeste2 implements TaskDetail{
	@Override
	public void execute() {
		System.out.println("Teste Com erro 2. . .  ");
        int[] array = {1, 2, 3};
        System.out.println(array[5]);
	}
}
