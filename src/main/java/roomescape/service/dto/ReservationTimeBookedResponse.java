package roomescape.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import roomescape.domain.reservation.ReservationTime;

public record ReservationTimeBookedResponse(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime startAt,
        boolean alreadyBooked
) {

    public static ReservationTimeBookedResponse of(ReservationTime time, boolean alreadyBooked) {
            return new ReservationTimeBookedResponse(time.getId(), time.getStartAt(), alreadyBooked);
    }
}
