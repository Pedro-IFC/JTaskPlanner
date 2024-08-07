package task;

/**
 * Interface que define um contrato para a execução de uma tarefa.
 * Qualquer classe que implemente esta interface deve fornecer uma implementação para o método {@link #execute()}.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     public class MyTask implements TaskDetail {
 *         {@literal @}Override
 *         public void execute() {
 *             // Implementação da tarefa
 *         }
 *     }
 * </pre>
 * </p>
 */
public interface TaskDetail {

    /**
     * Executa a tarefa definida pela implementação desta interface.
     */
    void execute();
}
