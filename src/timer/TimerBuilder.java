package timer;

/**
 * Classe responsável por construir e instanciar objetos da classe {@link Timer}.
 * Utiliza o padrão de projeto Builder para criar instâncias de {@link Timer}.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Timer timer = TimerBuilder.newTimer();
 * </pre>
 * </p>
 * @see Timer
 */
public class TimerBuilder {

    /**
     * Cria uma nova instância de {@link Timer}.
     *
     * @return uma nova instância de {@link Timer}
     */
    public static Timer newTimer() {
        return new Timer();
    }
}
