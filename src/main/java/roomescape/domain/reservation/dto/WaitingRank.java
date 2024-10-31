package roomescape.domain.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface WaitingRank {

    Long getId();

    String getMemberName();

    String getThemeName();

    LocalDate getDate();

    LocalTime getStartAt();

    Long getRank();
}
