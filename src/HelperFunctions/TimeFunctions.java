package HelperFunctions;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class makes converting from utc to the local time zone of the user easier
 */
public class TimeFunctions {

    /**
     * This class converts from UTC to local time
     */
    public static Timestamp UTCtoLocal(Timestamp UTC) {
        LocalDateTime ldtUTC = UTC.toLocalDateTime();
        ZonedDateTime zdttoUserZone = ldtUTC.atZone(ZoneId.systemDefault());
        LocalDateTime ldtToUserZone = zdttoUserZone.toLocalDateTime();
        Timestamp localTime = Timestamp.valueOf(ldtToUserZone);
        return localTime;
    }

}

