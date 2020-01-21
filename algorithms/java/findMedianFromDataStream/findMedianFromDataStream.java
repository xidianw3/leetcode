/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


package findMedianFromDataStream;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author: wangwenjie <wangwenjie1@xiaomi.com>
 * Created on 2020-01-21
 */

class MedianFinder {

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    };
    //小顶堆，存储前n/2个大元素
    private PriorityQueue<Integer> first = new PriorityQueue<>();

    //大顶堆，存储前n/2个小元素
    private PriorityQueue<Integer> second = new PriorityQueue<>(cmp);

    public void addNum(int num) {
        if (first.isEmpty() || num > first.peek()) {
            first.add(num);
        } else {
            second.add(num);
        }

        if (first.size() > second.size() + 1) {
            second.add(first.poll());
        }
        if (first.size() < second.size()) {
            first.add(second.poll());
        }
    }

    public double findMedian() {
        if (first.size() > second.size()) {
            return first.peek();
        }
        return (first.peek() + second.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(-1);
        obj.addNum(-2);
        obj.addNum(-3);
        obj.addNum(-4);
        obj.addNum(-5);
        double ret = obj.findMedian();
        System.out.println(ret);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

