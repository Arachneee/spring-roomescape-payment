package roomescape.domain.reservation.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import roomescape.domain.reservation.WaitingMember;
import roomescape.domain.reservation.dto.WaitingRank;
import roomescape.domain.reservation.dto.WaitingReadOnly;

public interface WaitingMemberRepository extends JpaRepository<WaitingMember, Long> {

    @Query("""
            select new roomescape.domain.reservation.dto.WaitingReadOnly(
            w.id,
            w.member.name,
            w.reservation.date,
            w.reservation.time.startAt,
            w.reservation.theme.name
            )
            from WaitingMember w""")
    List<WaitingReadOnly> findAllReadOnly();

    @Query(value = """
        select wr.id as id, wr.member_name as memberName, wr.theme_name as themeName, wr.date as date, wr.start_at as startAt, wr.rank as rank
        from (
            select w.id as id, m.id as member_id, m.name as member_name, t.name as theme_name, r.date as date, rt.start_at as start_at, row_number() over (partition by w.reservation_id order by w.id) as rank
            from waiting_member w
            inner join reservation r on w.reservation_id = r.id
            inner join member m on w.member_id = m.id
            inner join theme t on r.theme_id = t.id
            inner join reservation_time rt on r.time_id = rt.id
            where r.date >= :date and w.is_deleted = false
        ) wr
        where wr.member_id = :memberId
    """, nativeQuery = true)
    List<WaitingRank> findRankByMemberAndDateGreaterThanEqual(Long memberId, LocalDate date);
}
