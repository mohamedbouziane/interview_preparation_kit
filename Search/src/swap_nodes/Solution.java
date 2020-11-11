package swap_nodes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int nodeData) {
        this.data = nodeData;
        left = null;
        right = null;
    }
}
public class Solution {

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[][] result = new int[queries.length][indexes.length];

        // build tree from indexes
        Node root = new Node(1); // our tree
        Queue<Node> q = new LinkedList<>(); // because inputs are in level order
        q.add(root);
        for (int i = 0; i < indexes.length && !q.isEmpty(); i++) {
            Node temp = q.remove();
            if (indexes[i][0] != -1) {
                Node left = new Node(indexes[i][0]);
                temp.left = left;
                q.add(left);
            } else {
                temp.left = null;
            }

            if (indexes[i][1] != -1) {
                Node right = new Node(indexes[i][1]);
                temp.right = right;
                q.add(right);
            } else {
                temp.right = null;
            }
        }

        // swap based on queries
        for (int i = 0; i < queries.length; i++) {
            swap(root, queries[i], 1);
            List<Integer> temp = new ArrayList<>();
            inorder(root, temp);
            result[i] = temp.stream().mapToInt(n -> n).toArray();
        }

        return result;
    }

    static void swap(Node node, int k, int level) {
        if (node == null) return;
        if (level%k == 0) {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        swap(node.left, k, level+1);
        swap(node.right, k, level+1);
    }

    static void inorder(Node node, List<Integer> lst) {
        if (node != null) {
            inorder(node.left, lst);
            lst.add(node.data);
            inorder(node.right, lst);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
