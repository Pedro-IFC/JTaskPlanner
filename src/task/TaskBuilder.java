package task;

/**
 * Classe responsável por construir e instanciar objetos da classe {@link Task}.
 * Utiliza o padrão de projeto Builder para criar instâncias de {@link Task}.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Task task = TaskBuilder.newTask();
 * </pre>
 * </p>
 * @see Task
 */
public class TaskBuilder {

    /**
     * Cria uma nova instância de {@link Task}.
     *
     * @return uma nova instância de {@link Task}
     */
    public static Task newTask() {
        return new Task();
    }
}
