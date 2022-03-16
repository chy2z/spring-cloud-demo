package org.chy.carorder.util;

/**
 * Created by chy on 2022/3/11.
 */
public class IdWorkUitl {
    private final static IDGenerator idGenerator = new IDGenerator(1, 1, 1000);

    public static Long next() {
        return idGenerator.nextId();
    }
}