package Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 用map实现的并查集
public class UnionFind {
    // 一个样本V 外包了一层
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;           // 对应表：V->节点 (一旦放进去永远不更改)
        public HashMap<Node<V>, Node<V>> parents;   // 往上找：存储该节点的父节点 (不必往上指针)
        public HashMap<Node<V>, Integer> sizeMap;   // 记录代表点(和总节点数)

        // 初始化：预设用户倒入所有样本
        public UnionSet(List<V> values) {
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 找到代表点：决定复杂度的上限 (优化：减少遍历链的高度)
        public Node<V> findFather(Node<V> cur) {
            // 优化：记录沿途路径走过的所有结点
            Stack<Node<V>> path = new Stack<>();
            // 从点cur开始一直往上找 找到不能再往上的代表点返回
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 优化：此时cur为代表点 打扁平(沿途的父指向cur)
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        // 检查二者连通情况
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) return false;  // 边界条件：没登记
            return findFather(nodes.get(a)) == findFather(nodes.get(b));  // 二者代表点是否一样
        }

        // 连通二者
        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) return;  // 没登记

            // 找到二者的代表点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));

            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);

                Node<V> big = (aSetSize >= bSetSize) ? aHead : bHead;
                Node<V> small = (big == aHead) ? bHead : aHead;
                parents.put(small, big);  // 小挂大
                sizeMap.put(big, aSetSize + bSetSize);  // 大的接收一切
                sizeMap.remove(small);  // 小的移除

                /*if (aSetSize >= bSetSize) {
                    parents.put(bHead, aHead);  // 小挂大
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);  // 小挂大
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }*/
            }
        }

        // 返回并查集中集合数量
        public int getSetNum() {
            return sizeMap.size();
        }

    }
}
