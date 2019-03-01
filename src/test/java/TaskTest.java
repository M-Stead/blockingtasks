import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {



    @Test
    public void verifyStatusStringNoAncestorsOrDependantsTest()
    {
        //Not Started
        Task testTask1 = new Task("Test Task 1");
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());

        //Inflight
        Task testTask2 =  new Task("Test Task 2");
        testTask2.execute();
        assertEquals("Test Task 2 - INFLIGHT", testTask2.getStatusString());

        //Blocked


    }

    @Test
    public void verifyStatusStringWithPredecessorTest()
    {
        //Inflight
        Task testTask1 = new Task("Test Task 1");
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());
        Task testTask2 = new Task("Test Task 2");
        testTask2.addPredecessorTask(testTask1);
        testTask2.execute();
        assertEquals("Test Task 2 - INFLIGHT", testTask2.getStatusString());
        testTask2.checkStatus();
        assertEquals("Test Task 2 - INFLIGHT", testTask2.getStatusString());
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());

        //Completed
        Task testTask3 = new Task("Test Task 3");
        assertEquals("Test Task 3 - NOT_STARTED", testTask3.getStatusString());

        Task testTask4 = new Task("Test Task 4");
        testTask4.addPredecessorTask(testTask3);
        testTask4.execute();

        assertEquals("Test Task 4 - INFLIGHT", testTask4.getStatusString());

        testTask4.checkStatus();
        assertEquals("Test Task 4 - INFLIGHT", testTask4.getStatusString());
        assertEquals("Test Task 3 - NOT_STARTED", testTask3.getStatusString());

        testTask3.execute();
        testTask3.checkStatus();
        assertEquals("Test Task 3 - COMPLETED", testTask3.getStatusString());

        testTask4.checkStatus();
        assertEquals("Test Task 4 - COMPLETED", testTask4.getStatusString());



    }

    @Test
    public void verifyStatusStringWithAncestorsTest()
    {
        //Blocked
        Task testTask1 = new Task("Test Task 1");
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());
        Task testTask2 = new Task("Test Task 2");
        testTask2.addAncestorTask(testTask1);
        testTask2.execute();
        assertEquals("Test Task 2 - BLOCKED", testTask2.getStatusString());
        testTask2.checkStatus();
        assertEquals("Test Task 2 - BLOCKED", testTask2.getStatusString());
        assertEquals("Test Task 1 - NOT_STARTED", testTask1.getStatusString());
    }

}
