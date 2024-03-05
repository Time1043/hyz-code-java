package greedAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    // Program对象：花费、利润
    public static class Program {
        public int p;  // 利润
        public int c;  // 花费

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     * 贪心策略实现的
     * @param K       最多K个项目
     * @param W       初始资金为W
     * @param Profits 每一个项目的利润
     * @param Capital 每一个项目的花费
     * @return
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        // 根据花费组织的小根堆 根据利润组织的大根堆
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        // step1：将所有项目加入到 由花费组织的小根堆 中
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }

        // step2：挑K个 (进 由利润组织的大根堆、大根堆的堆顶返回)
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) return W;  // 边界条件：不再有项目进入大根堆 无事可做提前结束
            W += maxProfitQ.poll().p;
        }

        return W;
    }

    private static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    private static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
