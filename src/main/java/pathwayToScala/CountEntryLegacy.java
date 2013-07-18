package pathwayToScala;

import java.util.Date;

/**
 * The CountEntry class is an immutable record of the time a person increments the coun
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
}
