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
	public static void keepRunning() throws InterruptedException {
		int totalTime=0;
		boolean infinite=false;
		for(int i=0; i<plans.size(); i++) {
			if(plans.get(i).getTrigger().getTimer().getRepTimes()<0) {
				infinite=true;
			}else {
				totalTime+=plans.get(i).getTrigger().getTimer().getRepTimes()*plans.get(i).getTrigger().getTimer().getTime();
			}
		}
		while(infinite) {
			Thread.sleep(100000);
		}
		Thread.sleep(totalTime);
	}
}
