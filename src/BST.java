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
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = delete(node.left, key);
        } else if (comp > 0) {
            node.right = delete(node.right, key);
        } else {
            // node to delete found
            if (node.left == null) {
                return node.right; // case 1 and 2a
            } else if (node.right == null) {
                return node.left; // case 2b
            } else {
                // case 3: node has two children
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.val = minNode.val;
                node.right = delete(node.right, minNode.key);
            }
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
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
