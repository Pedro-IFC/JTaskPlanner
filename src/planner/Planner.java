package planner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import task.Task;
import timer.TimerBuilder;
import trigger.Trigger;
import trigger.TriggerBuilder;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por planejar e executar tarefas baseadas em gatilhos temporais.
 * Permite definir a saída de logs, configurar gatilhos e tarefas, e iniciar a execução de tarefas de forma assíncrona.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Planner planner = new Planner().setOutput("outputDirectory")
 *                                    .planTask(trigger, task);
 *     planner.start();
 * </pre>
 * </p>
 */
public class Planner {
    private String output;
    private List<String> logs = new ArrayList<>();
    private Trigger trigger;
    private Task task;
    /**
     * Obtém o diretório de saída dos logs.
     *
     * @return o diretório de saída dos logs
     */
    public String getOutput() {
        return output;
    }

    /**
     * Define o diretório de saída dos logs.
     *
     * @param output o novo diretório de saída dos logs
     * @return a própria instância do Planner, permitindo encadeamento de métodos
     */
    public Planner setOutput(String output) {
        this.output = output;
        return this;
    }

    /**
     * Obtém o gatilho associado a este planner.
     *
     * @return o gatilho associado a este planner
     */
    public Trigger getTrigger() {
        return trigger;
    }

    /**
     * Define o gatilho associado a este planner.
     *
     * @param trigger o novo gatilho a ser associado
     */
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    /**
     * Obtém a tarefa associada a este planner.
     *
     * @return a tarefa associada a este planner
     */
    public Task getTask() {
        return task;
    }

    /**
     * Define a tarefa associada a este planner.
     *
     * @param task a nova tarefa a ser associada
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Planeja uma tarefa com um gatilho específico.
     *
     * @param trigger o gatilho que aciona a tarefa
     * @param task a tarefa a ser executada
     * @return a própria instância do Planner, permitindo encadeamento de métodos
     */
    public Planner planTask(Trigger trigger, Task task) {
        this.trigger = trigger;
        this.task = task;
        return this;
    }

    /**
     * Define uma tarefa baseada em uma expressão cron.
     *
     * @param cronExp a expressão cron que define a periodicidade
     * @param task a tarefa a ser executada
     * @return a própria instância do Planner, permitindo encadeamento de métodos
     */
    public Planner cron(String cronExp, Task task) {
        Trigger trigger = TriggerBuilder.newTrigger()
            .rename("Cron")
            .start()
            .when(
            	TimerBuilder.newTimer()
	            	.interval(60000)
	            	.cron(cronExp)
	            	.repeat()
            );
        this.trigger = trigger;
        this.task = task;
        return this;
    }
    /**
     * Inicia a execução da tarefa planejada de forma assíncrona.
     */
    public void start() {
        if (this.trigger.getTimer().isRepeat()) {
            asyncFunction(task);
        }
    }

    /**
     * Executa a tarefa de forma assíncrona com base no gatilho configurado.
     *
     * @param task a tarefa a ser executada
     * @return um {@link CompletableFuture} que representa a tarefa assíncrona
     */
    public CompletableFuture<Void> asyncFunction(Task task) {
        return CompletableFuture.runAsync(() -> {

            int repTimes = this.trigger.getTimer().getRepTimes();
            int iterationCount = repTimes >= 0 ? repTimes : Integer.MAX_VALUE;

            for (int i = 0; i < iterationCount; i++) {
                executeTask();
                try {
                    Thread.sleep(this.trigger.getTimer().getTime()-(
                    		Calendar.getInstance().get(Calendar.SECOND)*1000+
                    		Calendar.getInstance().get(Calendar.MILLISECOND)
                		));
                } catch (InterruptedException e) {
                    this.logs.add(e.toString());
                }
                writeLogsToFile();
            	this.getTrigger().getTimer().getCron().convert();
            }
        });
    }
    private void executeTask() {
        if (this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
            try {
                task.getTaskDetail().execute();
                this.logs.add(LocalDateTime.now() + " - " + trigger.getName() + ":" + task.getName() + "\n");
            } catch (Exception e) {
                this.logs.add(LocalDateTime.now() + " - " + e.toString() + "\n");
            }
        }
    }

    private void writeLogsToFile() {
        try (FileWriter fileWriter = new FileWriter(this.getOutput() + "/" + this.getTrigger().getName() + "-" + this.task.getName() + ".json")) {
            fileWriter.write(this.logs.toString());
        } catch (IOException e) {
            this.logs.add(LocalDateTime.now() + " - " + e.toString() + "\n");
        }
    }

}
