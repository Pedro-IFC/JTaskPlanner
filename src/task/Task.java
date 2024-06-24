package task;


public class Task {
	private String name;
	private TaskDetail taskdetail;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TaskDetail getTaskDetail() {
		return taskdetail;
	}
	public Task rename(String name) {
		this.name=name;
		return this;
	}
	public Task setTask(TaskDetail task) {
		this.taskdetail = task;
		return this;
	}
}
