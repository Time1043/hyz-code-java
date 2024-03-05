package Graph;

public class GraphGenerator {
    // 转换接口：三元组转化为熟悉的图表示
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            // 拿到每一条边 matrix[i]
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            // 如果当前节点没出现则新建
            if (!graph.nodes.containsKey(from)) graph.nodes.put(from, new Node(from));
            if (!graph.nodes.containsKey(to)) graph.nodes.put(to, new Node(to));
            // 新建图节点和边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }

        return graph;
    }

    public static void main(String[] args) {
        int[][] matrix = {{7, 0, 4}, {4, 1, 3}, {3, 2, 3}, {8, 2, 4}};
        Graph graph = createGraph(matrix);
        System.out.println(graph.edges);
        System.out.println(graph.nodes);
    }
}
