package roomescape.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.reservation.dto.WaitingRank;

public record WaitingRankResponse(
        Long id,
        String name,
        String theme,
        LocalDate date,
        LocalTime startAt,
        Long rank
) {
    public static WaitingRankResponse from(WaitingRank waitingRank) {
        return new WaitingRankResponse(
                waitingRank.getId(),
                waitingRank.getMemberName(),
                waitingRank.getThemeName(),
                waitingRank.getDate(),
                waitingRank.getStartAt(),
                waitingRank.getRank()
        );
    }
}
