import java.util.*;

public class Task {

    private ArrayList<Task> predecessors;
    private ArrayList<Task> ancestors;
    private String name;
    private Status status;



    private boolean isComplete;

    Task(String taskName)
    {
        this.predecessors = new ArrayList<Task>();
        this.ancestors = new ArrayList<Task>();
        this.name = taskName;
        this.status = Status.NOT_STARTED;
    }

    private boolean hasPredecessors()
    {
        return !this.predecessors.isEmpty();
    }

    private boolean hasAncestors()
    {
        return !this.ancestors.isEmpty();
    }

    void addPredecessorTask(Task task)
    {
        this.predecessors.add(task);
    }

    void addAncestorTask(Task task)
    {
        this.ancestors.add(task);
    }

    void execute()
    {
        if(this.hasAncestors()) {
            this.status = Status.BLOCKED;
        }else{
            this.status = Status.INFLIGHT;
        }
    }


    public boolean isComplete()
    {
        return this.status.equals(Status.COMPLETED);
    }

    public void checkStatus()
    {

        //remove any completed ancestor tasks
        removeCompletedTasks(this.ancestors);

        //remove any completed ancestor tasks
        removeCompletedTasks(this.predecessors);

        if(this.hasAncestors()) {
            this.status = Status.BLOCKED;
        }
        else if(this.hasPredecessors())
        {
            this.status = Status.INFLIGHT;
        }
        else
        {
            this.status = Status.COMPLETED;
        }


    }

    public void removeCompletedTasks(ArrayList<Task> tasks)
    {
        //remove any completed  tasks
        ListIterator pli = tasks.listIterator();
        ArrayList<Task> tasksToRemove = new ArrayList<Task>();
        while(pli.hasNext())
        {
            Task currentTask = (Task)pli.next();
            if(currentTask.isComplete())
                tasksToRemove.add(currentTask);
        }
        tasks.removeAll(tasksToRemove);
    }

    public Status getStatus() {
        return this.status;
    }

    public String getStatusString() {

        return this.name + " - " + this.status.toString();
    }
}
