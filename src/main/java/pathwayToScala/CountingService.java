package javaToScala;

import java.util.concurrent.atomic.AtomicLong;


public class CountingService {


    public static final CountingService COUNTING_SERVICE = new CountingService();

    public static CountingService getInstance() {
        return COUNTING_SERVICE;
    }

    private CountingService() {
    }

    private final AtomicLong counter = new AtomicLong(0);

    public long increment() {
        return counter.incrementAndGet();
    }

    public long getCount() {
        return counter.get();
    }
}
