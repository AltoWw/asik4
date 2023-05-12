import java.util.*;
public class BST <K extends Comparable <K>, V> implements Iterable<K> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private int size = 1;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public void put(K key, V val) {
        if(root == null) {
            root = new Node(key, val);
            return;
        }
        put(root, key, val);
    }

    private void put(Node node, K key, V val) {
        node.size++;
        int comp = key.compareTo(node.key);
        if (comp == 0) {
            node.val = val;
        } else if (comp > 0) {
            if(node.right == null) {
                node.right = new Node(key, val);
            }
            else {
                put(node.right, key, val);
            }
        } else {
            if(node.left == null) {
                node.left = new Node(key, val);
            }
            else {
                put(node.left, key, val);
            }
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if(node == null) return null;

        int comp = key.compareTo(node.key);
        if (comp == 0) {
            return node.val;
        } else if (comp > 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    public void delete(K key) {

    }
    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K> {
        private List<K> inorder;
        private int cursor;
        public MyIterator() {
            inorder = new ArrayList<>();
            inorder(root, inorder);
        }

        private void inorder(Node node, List<K> res) {
            if(node == null) return;
            inorder(node.left, res);
            res.add(node.key);
            inorder(node.right, res);
        }

        @Override
        public boolean hasNext() {
            return cursor < inorder.size();
        }

        @Override
        public K next() {
            return inorder.get(cursor++);
        }
    }
}
