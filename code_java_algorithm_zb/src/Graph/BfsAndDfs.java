package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static Graph.GraphGenerator.createGraph;

public class BfsAndDfs {
    // 从node出发 宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) return;

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();  // 登记：保证只进一次

        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + "  ");
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        System.out.println();
    }

    // 从node出发 深度优先遍历
    public static void dfs(Node start) {
        if (start == null) return;

        Stack<Node> stack = new Stack<>();  // 栈辅助记录的是深度优先遍历的路径
        HashSet<Node> set = new HashSet<>();  // 登记

        stack.add(start);
        set.add(start);
        System.out.print(start.value + "  ");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.value + "  ");
                    break;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{7, 4, 0}, {4, 3, 1}, {3, 2, 3}, {8, 2, 4}};
        Graph graph = createGraph(matrix);
        bfs(graph.nodes.get(2));
        dfs(graph.nodes.get(2));
    }
}
