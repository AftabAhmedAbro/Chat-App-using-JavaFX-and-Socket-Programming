package mainScreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrie {
    public static int ALPHABET_SIZE = 26;

    public MyTrie() {
        String filename = "C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\dict.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                insert(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getRoot() {
        return root;
    }

    private class Node {
        private char value;
        private HashMap<Character, Node> children = new HashMap<Character, Node>();
        private boolean status;

        public Node(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void removeChild(char ch) {
            children.remove(ch);
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        Node current = root;
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.status = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        Node current = root;
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!current.hasChild(ch)) {
                return false;
            }
            current = current.getChild(ch);
        }
        return current.status;
    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(root, word, 0);
    }

    private void remove(Node root, String word, int index) {
        if (index == word.length()) {
            root.status = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if (child == null)
            return;

        remove(child, word, index + 1);

        if (!child.hasChildren() && !child.status)
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<String>();
        Node lastNode = findLastNodeOf(prefix);
        findWords(lastNode, prefix, words, 10);
        return words;
    }

    public void findWords(Node root, String prefix, List<String> words, int numberOfWords) {

        if (root == null || words.size() == numberOfWords)
            return;

        if (root.status) {
            if (words.size() == numberOfWords) return;
            words.add(prefix);
        }

        for (Node child : root.getChildren()) {
            if (words.size() == numberOfWords) return;
            findWords(child, prefix + child.value, words, numberOfWords);
        }
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null)
            return null;

        Node current = root;
        for (char ch : prefix.toCharArray()) {
            Node child = current.getChild(ch);
            if (child == null)
                return null;
            current = child;
        }
        return current;
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;

        return containsRecursive(root, word, 0);
    }


    private boolean containsRecursive(Node root, String word, int index) {
        // Base condition
        if (index == word.length())
            return root.status;

        if (root == null)
            return false;

        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if (child == null)
            return false;

        return containsRecursive(child, word, index + 1);
    }
}
