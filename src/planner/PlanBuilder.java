package planner;

import java.util.ArrayList;
import java.util.List;

public class PlanBuilder {
	private static List<Planner> plans = new ArrayList<>();
	
	public static Planner newPlanner() {
		Planner n = new Planner();
		plans.add(n);
		return n;
	}
}
