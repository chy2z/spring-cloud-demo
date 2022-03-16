package org.chy.carorder.util;

/**
 * 分布式ID生成器
 * SnowFlake
 * 41位的时间戳（时间戳差）可使用69年，(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69年
 * 2^41=2199023255552
 * 1000L * 60 * 60 * 24 * 365=31536000000
 * 2199023255552/31536000000=69.7年
 *
 * 41位的时间戳可以使用17.4年
 * 2^39=549755813888
 * 549755813888/31536000000=17.4年
 */
public class IDGenerator {
    /**
     * 机器id
     * 5bit
     */
    private long workerId;
    /**
     * 数据中心id
     * 5bit
     */
    private long datacenterId;
    /**
     * 序列号，用来记录同毫秒内产生的不同id数
     * 12bit
     */
    private long sequence;

    //起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量
    //时间戳差值越大，ID越长
    private long twepoch = 1558517345282L;

    //workerId占用的位数：5
    private long workerIdBits = 5L;
    //datacenterId占用的位数：5
    private long datacenterIdBits = 5L;
    // workerId可以使用的最大数值：31
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // datacenterId可以使用的最大数值：31
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //序列号占用的位数：12
    private long sequenceBits = 12L;

    //偏移比特位
    private long workerIdShift = sequenceBits; //12
    private long datacenterIdShift = sequenceBits + workerIdBits; //17
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;//22
    //每毫秒序号最大值
    private long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    //上一次时间戳
    private long lastTimestamp = -1L;

    public IDGenerator(long workerId, long datacenterId, long sequence){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            //防止1毫秒内,序号溢出
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //序号溢出超出最大值
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        //使用二进制的"|"运算符将4部分的值整合成我们需要的id
        return ((timestamp - twepoch) << timestampLeftShift) | //时间戳部分
                (datacenterId << datacenterIdShift) |          //数据中心部分
                (workerId << workerIdShift) |                  //机器标识部分
                sequence;                                      //序列号部分
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        //如果1毫秒未到,则等待
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        IDGenerator worker = new IDGenerator(1,1,1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(worker.nextId());
        }

        System.out.println("=========================");

        IDGenerator worker2 = new IDGenerator(31,31,1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(worker2.nextId());
        }
    }

    private static void test1(){
        //计算12位能耐存储的最大正整数，相当于：2^12-1 = 4095
        long seqMask = -1L ^ (-1L << 12L);
        System.out.println("seqMask: "+seqMask);
        System.out.println(1L & seqMask);
        System.out.println(2L & seqMask);
        System.out.println(3L & seqMask);
        System.out.println(4L & seqMask);
        System.out.println(4095L & seqMask);
        System.out.println(4096L & seqMask);
        System.out.println(4097L & seqMask);
        System.out.println(4098L & seqMask);
    }

    private static void test2(){
        //2017-09-20 21:43:08:849
        long timestamp = 1505914988849L;
        //2010-11-04 09:42:54:657
        long twepoch = 1288834974657L;
        long datacenterId = 17L;
        long workerId = 25L;
        long sequence = 0L;

        System.out.printf("\ntimestamp: %d \n",timestamp);
        System.out.printf("twepoch: %d \n",twepoch);
        System.out.printf("datacenterId: %d \n",datacenterId);
        System.out.printf("workerId: %d \n",workerId);
        System.out.printf("sequence: %d \n",sequence);
        System.out.println();
        System.out.printf("(timestamp - twepoch): %d \n",(timestamp - twepoch));
        System.out.printf("((timestamp - twepoch) << 22L): %d \n",((timestamp - twepoch) << 22L));
        System.out.printf("(datacenterId << 17L): %d \n" ,(datacenterId << 17L));
        System.out.printf("(workerId << 12L): %d \n",(workerId << 12L));
        System.out.printf("sequence: %d \n",sequence);

        long result = ((timestamp - twepoch) << 22L) |
                (datacenterId << 17L) |
                (workerId << 12L) |
                sequence;
        System.out.println(result);
    }
}
