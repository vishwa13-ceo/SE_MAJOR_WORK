import java.util.Vector;

/**
 * RsaRepository represents a simple stack-like data structure 
 * with limited capacity and basic operations.
 */
public class RsaRepositoryDemo {

    // Stack implementation class
    static class RsaRepository {
        // Internal vector to store stack elements
        private Vector<Integer> stackData;
        
        // Maximum number of elements the stack can hold
        private final int maxCapacity;

        /**
         * Constructs a new repository with specified maximum capacity.
         * 
         * @param capacity Maximum number of elements allowed in the stack
         */
        public RsaRepository(int capacity) {
            this.stackData = new Vector<>(capacity);
            this.maxCapacity = capacity;
        }

        /**
         * Attempts to store a new element in the stack.
         * 
         * @param value Integer to be stored
         * @return Operation status code (1: success, 2: stack full, 0: failure)
         */
        public Integer store(int value) {
            // Check if stack has reached its maximum capacity
            if (stackData.size() >= maxCapacity) {
                return 2;  // Stack is full
            }
            
            try {
                stackData.add(value);
                return 1;  // Successfully added
            } catch (Exception e) {
                return 0;  // Unexpected failure
            }
        }

        /**
         * Removes and returns the top element from the stack.
         * 
         * @return Retrieved element with status or empty repository message
         */
        public String retrieve() {
            // Check if stack is empty
            if (stackData.isEmpty()) {
                return "#Repository Empty";
            }
            
            // Remove and return the top element
            int topElement = stackData.remove(stackData.size() - 1);
            return topElement + "#Successful";
        }

        /**
         * Provides a view of current stack contents.
         * 
         * @return Copy of the stack elements
         */
        public Vector<Integer> display() {
            return new Vector<>(stackData);
        }
    }

    /**
     * Main method to demonstrate and test RsaRepository functionality.
     */
    public static void main(String[] args) {
        // Test Suite for RsaRepository
        System.out.println("RsaRepository Test Suite");
        System.out.println("------------------------");

        // Create a repository with capacity of 3
        RsaRepository repo = new RsaRepository(3);

        // Test 1: Verify empty stack behavior
        performTest("Empty Stack Display", 
            repo.display().isEmpty(), 
            "Stack should be initially empty");

        // Test 2: Retrieve from empty stack
        performTest("Empty Stack Retrieval", 
            repo.retrieve().equals("#Repository Empty"), 
            "Retrieving from empty stack should return empty message");

        // Test 3: Store first element
        performTest("First Element Storage", 
            repo.store(10) == 1, 
            "Should successfully store first element");

        // Test 4: Verify display after first element
        Vector<Integer> currentStack = repo.display();
        performTest("Display After First Element", 
            currentStack.size() == 1 && currentStack.get(0) == 10, 
            "Stack should contain first stored element");

        // Test 5: Fill stack to capacity
        repo.store(20);
        repo.store(30);
        performTest("Stack Capacity Limit", 
            repo.store(40) == 2, 
            "Attempting to exceed capacity should return full status");

        // Test 6: Retrieve from full stack
        String retrievalResult = repo.retrieve();
        performTest("Retrieve Top Element", 
            retrievalResult.startsWith("30#"), 
            "Should retrieve most recently added element");

        // Final stack contents
        System.out.println("\nFinal Stack Contents: " + repo.display());
    }

    /**
     * Utility method to standardize test result reporting.
     * 
     * @param testName Description of the test
     * @param condition Test condition result
     * @param description Expected behavior description
     */
    private static void performTest(String testName, boolean condition, String description) {
        System.out.println(testName + ": " + 
            (condition ? "PASSED ✓" : "FAILED ✗") + 
            " (" + description + ")");
    }
}