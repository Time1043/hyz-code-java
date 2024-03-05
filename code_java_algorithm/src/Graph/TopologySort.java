package Graph;

import java.util.*;

import static Graph.GraphGenerator.createGraph;

public class TopologySort {
    // 删除入度为0的点 (同时删去边)
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();  // 登记：key为某个节点:value为其剩余入度
        Queue<Node> zeroInQueue = new LinkedList<>();  // 体现删除：入度为0才能进的队列

        // 初始化：登记、有向无环图比存在入度为0的点
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) zeroInQueue.add(node);
        }

        List<Node> result = new ArrayList<>();  // 拓扑排序的结果：依次加入result
        while (!zeroInQueue.isEmpty()) {
            // 删除入度为0的点 (记录result)
            Node cur = zeroInQueue.poll();
            result.add(cur);
            // 同时删除边 (所有直接指向的邻居 入度-1)
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) zeroInQueue.add(next);  // 新出现的入度为0的点
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 1}, {1, 3, 1}, {1, 4, 1}, {1, 3, 2}, {1, 4, 3}};
        Graph graph = createGraph(matrix);
        List<Node> res = sortedTopology(graph);
        for (Node re : res) {
            System.out.print(re.value + "  ");
        }
        System.out.println();
    }
}
