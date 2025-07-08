public class MyHashSet {
    private static final int SIZE = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node[] baskets;
    private int size;
    private final float loadFactor;
    private int threshold;

    public MyHashSet() {
        this(SIZE, LOAD_FACTOR);
    }

    public MyHashSet(int initialSize, float loadFactor) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial size must be positive");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }

        baskets = new Node[initialSize];
        this.loadFactor = loadFactor;
        this.threshold = (int) (initialSize * loadFactor);
        this.size = 0;
    }

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public void add(int value) {
        if (contains(value)) {
            return;
        }

        int index = getIndex(value);
        Node newNode = new Node(value);
        newNode.next = baskets[index];
        baskets[index] = newNode;
        size++;

        if (size >= threshold) {
            rehash();
        }
    }

    public void remove(int value) {
        int index = getIndex(value);
        Node head = baskets[index];
        Node prev = null;

        while (head != null) {
            if (head.value == value) {
                if (prev == null) {
                    baskets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    private int getIndex(int value) {
        return hash(value) % (baskets.length - 1);
    }

    private void rehash() {
        Node[] oldBaskets = baskets;
        baskets = new Node[oldBaskets.length * 2];
        threshold = (int) (baskets.length * loadFactor);
        size = 0;

        for (Node head : oldBaskets) {
            while (head != null) {
                add(head.value);
                head = head.next;
            }
        }
    }

    private int hash(int value) {
        return value ^ (value >>> 16);
    }

    public boolean contains(int value) {
        int index = getIndex(value);
        Node head = baskets[index];

        while (head != null) {
            if (head.value == value) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
