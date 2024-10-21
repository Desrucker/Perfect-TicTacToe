import java.util.ArrayList;

public class ArrayTree<E> {
    private ArrayList<E> array; // Storage for the elements in the tree
    private int count; // Current number of elements in the tree
    private int capacity; // Maximum capacity of the tree
    private int order; // Number of children per node

    // Default constructor with order 2 and capacity 1000
    public ArrayTree() {
        this(2, 1000); // Initialize with default order and capacity
    }

    // Constructor with specified order and capacity
    public ArrayTree(int order, int cap) {
        this.order = order; // Set the number of children per node
        this.capacity = cap; // Set the maximum capacity of the tree
        this.count = 0; // Initialize element count to zero
        this.array = new ArrayList<>(capacity); // Create the ArrayList with initial capacity

        // Initialize the ArrayList with nulls to match the capacity
        for (int i = 0; i < capacity; i++) {
            array.add(null); // Fill the array with nulls to indicate empty slots
        }
    }

    // Return the index of the root node
    public int root() {
        return count > 0 ? 0 : -1; // Return 0 if the tree is not empty, otherwise return -1
    }

    // Return the parent index of the node at index n
    public int parent(int p) {
        if (p == 0) return -1; // Root node has no parent, return -1
        return (p - 1) / order; // Calculate parent index based on the order
    }

    // Calculate the index of a child given a parent index and child index
    public int child(int p, int c) {
        int childIndex = order * p + c + 1; // Calculate child index based on parent index and child index
        return childIndex < capacity ? childIndex : -1; // Return child index if within capacity, otherwise return -1
    }

    // Return the current number of elements in the tree
    public int size() {
        return count; // Return the number of elements currently in the tree
    }

    // Check if the tree is empty
    public boolean isEmpty() {
        return count == 0; // Return true if there are no elements in the tree
    }

    // Add the root element to the tree
    public int addRoot(E e) {
        if (count > 0) {
            throw new IllegalStateException("Root already exists"); // Prevent adding a root if one exists
        }
        array.set(0, e); // Set the root element at index 0
        count = 1; // Update the count of elements
        return 0; // Return the index of the root
    }

    // Get the element at a specific position
    public E get(int pos) {
        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException("Invalid position: " + pos); // Handle invalid position
        }
        return array.get(pos); // Return the element at the specified position
    }

    // Add a child to a parent node
    public void addChild(int parentIndex, int childIndex, E e) {
        int pos = parentIndex * order + childIndex + 1; // Calculate the position in the array
        if (pos >= capacity) {
            throw new IllegalArgumentException("Tree capacity exceeded"); // Check if adding exceeds capacity
        }
        // Only set the child if the position is empty
        if (array.get(pos) == null) {
            array.set(pos, e); // Assign the element to the calculated position
            count++; // Increment the count of elements
        } else {
            throw new IllegalArgumentException("Child already exists at this position"); // Prevent overwriting
        }
    }

    // Get the element stored in the c-th child of a parent node
    public E getChild(int parentIndex, int c) {
        int childIndex = child(parentIndex, c); // Get the child index
        if (childIndex == -1) {
            throw new IndexOutOfBoundsException("Child does not exist"); // Handle invalid index
        }
        return array.get(childIndex); // Return the child element from the array
    }

    // Convert the tree to a string representation for easy viewing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Create a StringBuilder for efficient string concatenation
        sb.append("[ArrayTree: order=").append(order) // Append order information
                .append(", count=").append(count) // Append current count of elements
                .append(", size=").append(capacity) // Append capacity of the tree
                .append(", array={");

        // Append elements of the array to the string representation
        for (int i = 0; i < capacity; i++) {
            if (array.get(i) != null) {
                sb.append(array.get(i)).append(" "); // Append actual values
            } else {
                sb.append("- "); // Append dash for empty slots
            }
        }
        sb.append("}]"); // Close the string representation

        return sb.toString().trim(); // Return the formatted string without trailing whitespace
    }
}
