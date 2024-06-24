package trigger;

import timer.Timer;

public class Trigger {
	private String name;
	private boolean run=false;
	private Timer timer;
	public Trigger rename(String name) {
		this.name=name;
		return this;
	}
	public Trigger start() {
		this.run=true;
		return this;
	}
	public Trigger when(Timer timer) {
		this.timer=timer;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
}
