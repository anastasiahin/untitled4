public class MyHashMap {
    private static class Node {
        Object key;
        Object value;
        Node next;
        Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }
    public void put(Object key, Object value) {
        int index = getBucketIndex(key);
        Node current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }
    public void remove(Object key) {
        int index = getBucketIndex(key);
        Node current = buckets[index];
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public void clear() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    public int size() {
        return size;
    }
    public Object get(Object key) {
        int index = getBucketIndex(key);
        Node current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Якщо ключ не знайдено
    }
    private int getBucketIndex(Object key) {
        return key.hashCode() % buckets.length;
    }
}
class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println("Get key1: " + map.get("key1"));
        System.out.println("Get key2: " + map.get("key2"));
        map.remove("key2");
        System.out.println("Get key2 after removal: " + map.get("key2"));
        System.out.println("Size: " + map.size());
        map.clear();
        System.out.println("Size after clear: " + map.size());
    }
}
