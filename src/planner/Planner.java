package planner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import task.Task;
import timer.TimerBuilder;
import trigger.Trigger;
import trigger.TriggerBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Planner {
    private String output;
    private List<String> logs = new ArrayList<>();
    private Trigger trigger;
    private Task task;
    public String getOutput() {
        return output;
    }
    public Planner setOutput(String output) {
        this.output = output;
        return this;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Planner planTask(Trigger trigger, Task task) {
        this.trigger = trigger;
        this.task = task;
        return this;
    }

    public Planner cron(String cronExp, Task task) {
    	Trigger trigger = TriggerBuilder.newTrigger()
    		.rename("Cron")
    		.start()
    		.when(TimerBuilder.newTimer()
    			.interval(60000)
    			.cron(cronExp)
    			.repeat()
    		);
        this.trigger = trigger;
        this.task = task;
        return this;
    }

    public void start() {
        if (this.trigger.getTimer().isRepeat()) {
            asyncFunction(task);
        }
    }
    public CompletableFuture<Void> asyncFunction(Task task) {
        return CompletableFuture.runAsync(() -> {
        	if (this.trigger.getTimer().getRepTimes() >= 0) {
                for (int i = 0; i < this.trigger.getTimer().getRepTimes(); i++) {
                	if(this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                	}
                	try {
                		Thread.sleep(this.trigger.getTimer().getTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
						this.logs.add(e.toString());
					}
                    if(!this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
                        try {
                        	task.getTaskDetail().execute();
	                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                        }catch(Exception e) {
                        	this.logs.add(LocalDateTime.now().toString() + " - " + e.toString() + "\n");
                        }
                    } 
                    try (FileWriter fileWriter = new FileWriter(this.getOutput() + "/" + this.getTrigger().getName() + "-" + this.task.getName() + ".json")) {
                        fileWriter.write(this.logs.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (true) {
                	if(this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                	}
                	try {
                		Thread.sleep(this.trigger.getTimer().getTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
						this.logs.add(e.toString());
					}
                    if(!this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
                        try { 
                        	task.getTaskDetail().execute();
                        	this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                        }catch(Exception e) {
                        	e.printStackTrace();
                        	this.logs.add(LocalDateTime.now().toString() + " - " + e.toString() + "\n");
                        }
                    }
                    try (FileWriter fileWriter = new FileWriter(this.getOutput() + "/" + this.getTrigger().getName() + "-" + this.task.getName() + ".json")) {
                        fileWriter.write(this.logs.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void executarTarefa() {
        if (this.trigger.isRun() && this.trigger.getTimer().matchTime()) {
            try {
                task.getTaskDetail().execute();
                this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
            } catch (Exception e) {
                this.logs.add(LocalDateTime.now().toString() + " - " + e.toString() + "\n");
            }
        }
        try {
            Thread.sleep(this.trigger.getTimer().getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.logs.add(e.toString());
        }

        salvarLogs();
    }

    private void salvarLogs() {
        try (FileWriter fileWriter = new FileWriter(this.getOutput() + "/" + this.getTrigger().getName() + "-" + this.task.getName() + ".json")) {
            fileWriter.write(this.logs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}