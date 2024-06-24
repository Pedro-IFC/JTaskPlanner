package planner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
    public void setOutput(String output) {
        this.output = output;
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
    	List<String> cronFields = Arrays.asList(cronExp.split(" "));
        if (cronFields.size() != 5) {
        	 throw new IllegalArgumentException("Expressão cron inválida");
        }
        int min = (cronFields.get(0).equals("*"))?0:Integer.parseInt(cronFields.get(0))*60000;
        int hour = (cronFields.get(1).equals("*"))?0:Integer.parseInt(cronFields.get(1))*3600000;
        int day = (cronFields.get(2).equals("*"))?0:Integer.parseInt(cronFields.get(2))*86400000;
        int month = (cronFields.get(3).equals("*"))?0:Integer.parseInt(cronFields.get(3))*86400000*31;
        int dayWeek = (cronFields.get(4).equals("*"))?0:Integer.parseInt(cronFields.get(4))*604800000;
        int total = min+hour+day+month+dayWeek;
    	Trigger trigger = TriggerBuilder.newTrigger()
    		.rename("Cron")
    		.when(TimerBuilder.newTimer()
    			.interval(total)
    			.repeat());
        this.trigger = trigger;
        this.task = task;
        return this;
    }

    public void start() {
        try {
            if (this.trigger.getTimer().isRepeat()) {
                CompletableFuture<Void> future = asyncFunction(task);
            	future.get(); 
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    public CompletableFuture<Void> asyncFunction(Task task) {
        return CompletableFuture.runAsync(() -> {
        	if (this.trigger.getTimer().getRepTimes() >= 0) {
                for (int i = 0; i < this.trigger.getTimer().getRepTimes(); i++) {
                	if(this.trigger.isRun()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                	}
                    if( i < this.trigger.getTimer().getRepTimes()-1) {
                    	try {
    						Thread.sleep(this.trigger.getTimer().getTime());
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
                    }
                    if(!this.trigger.isRun()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                    }
                	try (FileWriter fileWriter = new FileWriter(this.getOutput()+"/" + this.getTrigger().getName() + "-" + this.task.getName()+".json")) {
                        fileWriter.write(this.logs.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (true) {
                	if(this.trigger.isRun()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                	}
                    try {
						Thread.sleep(this.trigger.getTimer().getTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    if(!this.trigger.isRun()) {
                        task.getTaskDetail().execute();
                        this.logs.add(LocalDateTime.now().toString() + " - " + trigger.getName() + ":" + task.getName() + "\n");
                    }
                	try (FileWriter fileWriter = new FileWriter(this.getOutput()+"/" + this.getTrigger().getName() + "-" + this.task.getName()+".json")) {
                        fileWriter.write(this.logs.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}