package trigger;

/**
 * Classe responsável por construir e instanciar objetos da classe {@link Trigger}.
 * Utiliza o padrão de projeto Builder para criar instâncias de {@link Trigger}.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Trigger trigger = TriggerBuilder.newTrigger();
 * </pre>
 * </p>
 * @see Trigger
 */
public class TriggerBuilder {

    /**
     * Cria uma nova instância de {@link Trigger}.
     *
     * @return uma nova instância de {@link Trigger}
     */
    public static Trigger newTrigger() {
        return new Trigger();
    }
}
