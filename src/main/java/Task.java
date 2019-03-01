import java.util.*;

public class Task {

    private ArrayList<Task> predecessors;
    private ArrayList<Task> ancestors;
    private String name;
    private Status status;



    private boolean isComplete;

    public Task(String taskName)
    {
        this.predecessors = new ArrayList<Task>();
        this.ancestors = new ArrayList<Task>();
        this.name = taskName;
        this.status = Status.NOT_STARTED;
    }

    public boolean hasPredecessors()
    {
        return !this.predecessors.isEmpty();
    }

    public boolean hasAncestors()
    {
        return !this.ancestors.isEmpty();
    }

    public void addPredecessorTask(Task task)
    {
        this.predecessors.add(task);
    }

    public void removePredecessorTask(Task task)
    {
        this.predecessors.remove(task);
    }

    public void execute()
    {
        if(this.hasAncestors()) {
            this.status = Status.BLOCKED;
        }else{
            this.status = Status.INFLIGHT;
        }
    }

    public String getName()
    {
        return this.name;
    }


    public boolean isComplete()
    {
        return this.status.equals(Status.COMPLETED);
    }

    public void checkStatus()
    {

        //remove any completed ancestor tasks
        ListIterator ali = this.ancestors.listIterator();
        while(ali.hasNext())
        {
            Task currentTask = (Task)ali.next();
            if(currentTask.isComplete())
                this.ancestors.remove(currentTask);
        }

        //remove any completed predecessor tasks
        ListIterator pli = this.predecessors.listIterator();
        while(ali.hasNext())
        {
            Task currentTask = (Task)ali.next();
            if(currentTask.isComplete())
                this.predecessors.remove(currentTask);
        }

        if(this.hasAncestors()) {
            this.status = Status.INFLIGHT;
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

    public Status getStatus() {
        return this.status;
    }

    public String getStatusString() {

        return this.name + " - " + this.status.toString();
    }
}
