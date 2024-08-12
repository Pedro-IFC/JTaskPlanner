package task;

/**
 * Classe que representa uma tarefa, que pode ser configurada com um nome e um detalhe de tarefa específico.
 * Utiliza uma interface fluente para renomear a tarefa e definir seu detalhe.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     TaskDetail detail = new MyTaskDetail();
 *     Task task = new Task().rename("MyTask").setTask(detail);
 * </pre>
 * </p>
 * @see TaskDetail
 */
public class Task {
    private String name;
    private TaskDetail taskdetail;

    /**
     * Obtém o nome da tarefa.
     *
     * @return o nome da tarefa
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da tarefa.
     *
     * @param name o novo nome da tarefa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o detalhe da tarefa.
     *
     * @return o detalhe da tarefa
     */
    public TaskDetail getTaskDetail() {
        return taskdetail;
    }

    /**
     * Renomeia a tarefa com o nome especificado.
     *
     * @param name o novo nome da tarefa
     * @return a própria instância da tarefa, permitindo encadeamento de métodos
     */
    public Task rename(String name) {
        this.name = name;
        return this;
    }

    /**
     * Define o detalhe da tarefa com a instância especificada de {@link TaskDetail}.
     *
     * @param task o detalhe da tarefa a ser associado
     * @return a própria instância da tarefa, permitindo encadeamento de métodos
     */
    public Task setTask(TaskDetail task) {
        this.taskdetail = task;
        return this;
    }

	public Task(TaskDetail taskdetail) {
		super();
		this.taskdetail = taskdetail;
	}
	public Task() {
		super();
	}
    
}
