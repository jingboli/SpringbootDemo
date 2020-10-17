package cc.lijingbo;

public class MyBloomFilterTest {
    public static void main(String[] args) {
        MyBloomFilter bloomFilter = new MyBloomFilter();
        bloomFilter.add("张学友");
        bloomFilter.add("郭德纲");
        bloomFilter.add("刘德华");
        System.out.println(bloomFilter.isContain("张学友"));
        System.out.println(bloomFilter.isContain("郭德纲"));
        System.out.println(bloomFilter.isContain("刘德华"));
        System.out.println(bloomFilter.isContain("666"));
        System.out.println(bloomFilter.isContain("888"));
    }
}
