import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {



    @Test
    public void verifyStatusString()
    {
        //Not Started
        Task testTask1 = new Task("Test Task 1");
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());

        //Inflight
        Task testTask2 =  new Task("Test Task 2");
        testTask2.execute();
        assertEquals("Test Task 2 - INFLIGHT", testTask2.getStatusString());

        //Blocked

        //Inflight
        Task testTask3 = new Task("Test Task 3");
        testTask3.addPredecessorTask(testTask1);
        testTask3.execute();
        assertEquals("Test Task 3 - INFLIGHT", testTask3.getStatusString());
        testTask3.checkStatus();
        assertEquals("Test Task 3 - INFLIGHT", testTask3.getStatusString());
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());
    }

}
