package timer;

public class Timer {
	private int time;
	private boolean repeat;
	private int repTimes=-1;
	public Timer interval(int sec) {
		this.time=sec;
		return this;
	}
	public Timer repeat() {
		this.repeat=true;
		return this;
	}
	public Timer repeat(int times) {
		this.repeat=true;
		this.repTimes=times;
		return this;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	public int getRepTimes() {
		return repTimes;
	}
	public void setRepTimes(int repTimes) {
		this.repTimes = repTimes;
	}
}
