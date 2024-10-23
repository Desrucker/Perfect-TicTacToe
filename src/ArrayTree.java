import java.util.ArrayList;

public class ArrayTree<E> {
    private ArrayList<E> array; // Storage for tree elements
    private int count; // Number of elements
    private int capacity; // Max capacity
    private int order; // Children per node

    // Default constructor (order 2, capacity 1000)
    public ArrayTree() {
        this(2, 1000); // Initialize defaults
    }

    // Constructor with specified order and capacity
    public ArrayTree(int order, int cap) {
        this.order = order; // Set order
        this.capacity = cap; // Set capacity
        this.count = 0; // Initialize count
        this.array = new ArrayList<>(capacity); // Create array list

        // Fill array with nulls
        for (int i = 0; i < capacity; i++) {
            array.add(null); // Empty slots
        }
    }

    // Return root index
    public int root() {
        return count > 0 ? 0 : -1; // 0 if not empty, else -1
    }

    // Return parent index of node at index p
    public int parent(int p) {
        if (p == 0) return -1; // No parent for root
        return (p - 1) / order; // Calculate parent index
    }

    // Calculate child index from parent and child index
    public int child(int p, int c) {
        int childIndex = order * p + c + 1; // Calculate child index
        return childIndex < capacity ? childIndex : -1; // Check bounds
    }

    // Return number of elements in the tree
    public int size() {
        return count; // Current element count
    }

    // Check if the tree is empty
    public boolean isEmpty() {
        return count == 0; // True if no elements
    }

    // Add root element
    public int addRoot(E e) {
        if (count > 0) {
            throw new IllegalStateException("Root already exists"); // Prevent duplicate root
        }
        array.set(0, e); // Set root
        count = 1; // Update count
        return 0; // Return root index
    }

    // Get element at specific position
    public E get(int pos) {
        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException("Invalid position: " + pos); // Handle invalid position
        }
        return array.get(pos); // Return element
    }

    // Add a child to a parent node
    public void addChild(int parentIndex, int childIndex, E e) {
        int pos = parentIndex * order + childIndex + 1; // Calculate position
        if (pos >= capacity) {
            throw new IllegalArgumentException("Tree capacity exceeded"); // Check capacity
        }
        // Set child if empty
        if (array.get(pos) == null) {
            array.set(pos, e); // Assign element
            count++; // Increment count
        } else {
            throw new IllegalArgumentException("Child already exists at this position"); // Prevent overwrite
        }
    }

    // Get child element of a parent node
    public E getChild(int parentIndex, int c) {
        int childIndex = child(parentIndex, c); // Get child index
        if (childIndex == -1) {
            throw new IndexOutOfBoundsException("Child does not exist"); // Handle invalid index
        }
        return array.get(childIndex); // Return child element
    }

    // Convert tree to string representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // For string building
        sb.append("[ArrayTree: order=").append(order) // Append order
                .append(", count=").append(count) // Append count
                .append(", size=").append(capacity) // Append capacity
                .append(", array={");

        // Append elements to string
        for (int i = 0; i < capacity; i++) {
            if (array.get(i) != null) {
                sb.append(array.get(i)).append(" "); // Append values
            } else {
                sb.append("- "); // Empty slot
            }
        }
        sb.append("}]"); // Close representation

        return sb.toString().trim(); // Return formatted string
    }
}