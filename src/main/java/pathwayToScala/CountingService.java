package pathwayToScala;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class CountingService {


    public static final CountingService COUNTING_SERVICE = new CountingService();

    public static CountingService getInstance() {
        return COUNTING_SERVICE;
    }

    private List<CountEntry> countHistory = new ArrayList<>();

    private CountingService() {
    }

    private final AtomicLong counter = new AtomicLong(0);

    public long increment(Person person) {
        CountEntry countEntry = new CountEntry(person, new Date());
        countHistory.add(countEntry);

        return counter.incrementAndGet();
    }

    public void updatePersonId(int oldPersonId, int newPersonId) {
        countHistory = CountEntry.updatePersonId(oldPersonId, newPersonId);
    }

    public void resetForTestOnly() {
        counter.set(0);
        countHistory = new ArrayList<>();
    }

    public long getCount() {
        return counter.get();
    }

    public List<CountEntry> getCountHistory() {
        return countHistory;
    }
}
