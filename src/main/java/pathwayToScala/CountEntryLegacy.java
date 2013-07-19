package pathwayToScala;

import java.util.Date;

/**
 * The CountEntry class is an immutable record of the time a person increments the count
 *
 * This is the original / legacy java version of the class.  The code now uses the Scala version.
 */

public class CountEntryLegacy {

    private Integer personId;
    private Date timeStamp;

    public CountEntryLegacy(Integer personId, Date timeStamp) {
        this.personId = personId;
        this.timeStamp = timeStamp;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountEntryLegacy that = (CountEntryLegacy) o;

        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CountEntryLegacy{" +
                "personId=" + personId +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
