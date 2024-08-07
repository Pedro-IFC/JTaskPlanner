package trigger;

import timer.Timer;

/**
 * Classe que representa um Trigger, que pode ser configurado com um nome, um estado de execução e um temporizador.
 * <p>
 * Esta classe permite renomear o Trigger, iniciá-lo e associá-lo a um temporizador utilizando uma interface fluente.
 * </p>
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Timer timer = new Timer();
 *     Trigger trigger = new Trigger().rename("MyTrigger").when(timer).start();
 * </pre>
 * </p>
 */
public class Trigger {
    private String name;
    private boolean run = false;
    private Timer timer;

    /**
     * Renomeia o Trigger com o nome especificado.
     *
     * @param name o novo nome do Trigger
     * @return a própria instância do Trigger, permitindo encadeamento de métodos
     */
    public Trigger rename(String name) {
        this.name = name;
        return this;
    }

    /**
     * Inicia o Trigger, alterando seu estado de execução para true.
     *
     * @return a própria instância do Trigger, permitindo encadeamento de métodos
     */
    public Trigger start() {
        this.run = true;
        return this;
    }

    /**
     * Associa um temporizador ao Trigger.
     *
     * @param timer o temporizador a ser associado ao Trigger
     * @return a própria instância do Trigger, permitindo encadeamento de métodos
     */
    public Trigger when(Timer timer) {
        this.timer = timer;
        return this;
    }

    /**
     * Obtém o nome do Trigger.
     *
     * @return o nome do Trigger
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do Trigger.
     *
     * @param name o novo nome do Trigger
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Verifica se o Trigger está em execução.
     *
     * @return true se o Trigger estiver em execução, false caso contrário
     */
    public boolean isRun() {
        return run;
    }

    /**
     * Define o estado de execução do Trigger.
     *
     * @param run o novo estado de execução do Trigger
     */
    public void setRun(boolean run) {
        this.run = run;
    }

    /**
     * Obtém o temporizador associado ao Trigger.
     *
     * @return o temporizador associado ao Trigger
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Define o temporizador a ser associado ao Trigger.
     *
     * @param timer o novo temporizador a ser associado ao Trigger
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
