package cc.lijingbo;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 布隆过滤器
 */
public class MyBloomFilter {
    //布隆过滤器容量
    private static final int DEFAULT_SIZE = 2 << 28;
    // bit 数组，用来存放结果
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);
    // hash 函数会用到，用来生成不同的 hash 值，可随意设置
    private static final int[] ints = {1, 6, 16, 38, 58, 68};

    // hash 函数，借鉴了 hashmap 的扰动算法
    private int hash(Object key, int i) {
        int h;
        return key == null ? 0 : (i * (DEFAULT_SIZE - 1) & ((h = key.hashCode()) ^ (h >>> 16)));
    }

    // add 方法，计算出 key 的 hash 值，并将对应下标置为 true
    public void add(Object key) {
        Arrays.stream(ints).forEach(i -> bitSet.set(hash(key, i)));
    }

    // 判断 key 是否存在，true 不一定说明 key 存在，但是 false 一定说明不存在
    public boolean isContain(Object key) {
        boolean result = true;
        for (int i : ints) {
            // 短路与，只要有一个 bit 位为false，则返回false
            result = result && bitSet.get(hash(key, i));
        }
        return result;
    }
}
