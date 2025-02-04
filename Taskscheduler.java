import java.util.*;

class Taskscheduler {
    private static List<Task> tasks = new ArrayList<>();

    static class Task {
        String name;
        String time;

        Task(String name, String time) {
            this.name = name;
            this.time = time;
        }
    }

    public static void addTask(String taskName, String time) {
        tasks.add(new Task(taskName, time));
        System.out.println("Task '" + taskName + "' scheduled at " + time + ".");
    }

    public static void runScheduler() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled.");
            return;
        }
        
        tasks.sort(Comparator.comparing(t -> t.time));
        
        System.out.println("Running Task Scheduler...");
        for (Task task : tasks) {
            System.out.println("Executing task: " + task.name + " at " + task.time);
        }
    }

    public static void main(String[] args) {
        addTask("Do AWS Modules", "10:00 PM");
        addTask("Play Games", "11:30 PM");
        addTask("Watch Anime", "12:00 AM");
        
        runScheduler();
    }
}