package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit tests
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
       
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        mQueue.enqueue(30);
        mQueue.enqueue(40);
        assertEquals(Integer.valueOf(10), mQueue.peek());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        
            mQueue.enqueue(1);
            mQueue.enqueue(2);
            mQueue.enqueue(3);

            assertEquals(Integer.valueOf(1), mQueue.dequeue());
            assertEquals(Integer.valueOf(2), mQueue.dequeue());
            assertEquals(Integer.valueOf(3), mQueue.dequeue());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testWrapAround() {
        ArrayIntQueue q = new ArrayIntQueue();
        for (int i = 1; i <= 5; i++) {
            q.enqueue(i);
        }

        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());

        q.enqueue(6);
        q.enqueue(7);

        assertEquals(Integer.valueOf(3), q.dequeue());
        assertEquals(Integer.valueOf(4), q.dequeue());
        assertEquals(Integer.valueOf(5), q.dequeue());
        assertEquals(Integer.valueOf(6), q.dequeue());
        assertEquals(Integer.valueOf(7), q.dequeue());

        assertTrue(q.isEmpty());
    }

        @Test
        public void testClear() {
        ArrayIntQueue q = new ArrayIntQueue();
        q.enqueue(1);
        q.enqueue(2);

        q.clear();

        assertTrue(q.isEmpty());
        assertNull(q.peek());
        assertNull(q.dequeue());
    }

        @Test
        public void testDequeueEmptyQueue() {
        ArrayIntQueue q = new ArrayIntQueue();
        assertNull(q.dequeue());
    }

        @Test
        public void testEnsureCapacityWrapAround() {
        ArrayIntQueue q = new ArrayIntQueue();
        
        // initial size 10 → resize trigger
        for (int i = 1; i <= 15; i++) q.enqueue(i);  

        // FIFO шалгах
        for (int i = 1; i <= 15; i++) {
            assertEquals(Integer.valueOf(i), q.dequeue());
        }

        assertTrue(q.isEmpty());
    }


}
