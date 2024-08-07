package planner;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por construir e gerenciar instâncias da classe {@link Planner}.
 * Fornece métodos para criar novos planners e manter o processo em execução contínua.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     Planner planner = PlanBuilder.newPlanner();
 *     PlanBuilder.keepRunning();
 * </pre>
 * </p>
 * @see Planner
 */
public class PlanBuilder {
    private static List<Planner> plans = new ArrayList<>();

    /**
     * Cria uma nova instância de {@link Planner} e a adiciona à lista de planners gerenciados.
     *
     * @return uma nova instância de {@link Planner}
     */
    public static Planner newPlanner() {
        Planner n = new Planner();
        plans.add(n);
        return n;
    }

    /**
     * Mantém o processo em execução contínua, pausando o thread atual por intervalos de tempo.
     * 
     * @throws InterruptedException se a thread atual for interrompida enquanto estiver dormindo
     */
    public static void keepRunning() throws InterruptedException {
        while (true) {
            Thread.sleep(100000);
        }
    }
}
