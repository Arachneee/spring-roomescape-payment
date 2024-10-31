package roomescape.concurrent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ConcurrentTest {

    private static final String BASE_URI = "http://localhost:8080";
    private static final int CONCURRENT_REQUEST_COUNT = 100;

    Long reservationId;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;

        String requestBody = "{ \"date\": \"2026-01-10\""
                             + ", \"timeId\": " + 1
                             + ", \"themeId\": " + 1
                             + " }";

        Response post = RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .body(requestBody)
                .post("/reservations");

        reservationId = Long.parseLong(post.getHeader("Location"));
    }

    @AfterEach
    void tearDown() {
        RestAssured.baseURI = BASE_URI;

        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .delete("/reservations/" + reservationId);
    }

    @Disabled
    @Test
    void 동시_사용_요청() throws InterruptedException {
        AtomicInteger bookedCount = new AtomicInteger(0);
        AtomicInteger waitingCount = new AtomicInteger(0);
        AtomicBoolean requestStart = new AtomicBoolean(false);

        ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENT_REQUEST_COUNT);
        for (int i = 7; i < 7 + CONCURRENT_REQUEST_COUNT; i++) {
            int j = i;
            executorService.submit(() -> reservate(waitingCount, bookedCount, requestStart, j));
        }

        Thread.sleep(1000L);    // 스레드에 실행 요청 후 1초간 대기한 후 요청을 시작하도록 변경한다.
        requestStart.set(true);

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        assertAll(
                () -> assertThat(bookedCount.get()).isEqualTo(1),
                () -> assertThat(waitingCount.get()).isEqualTo(99)
        );
    }

    private static void reservate(AtomicInteger waitingCount, AtomicInteger bookedCount, AtomicBoolean requestStart, int memberId) {
        while (requestStart.get() == false) {
        }
        String requestBody = "{ \"date\": \"2026-01-10\""
                             + ", \"timeId\": " + 1
                             + ", \"themeId\": " + 1
                             + ", \"amount\": " + 1000
                             + " }";
        Response response = RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .header("ID", memberId)
                .body(requestBody)
                .post("/reservations/booked")
                .then()
                .extract().response();

        String location = response.getHeader("Location");

        if (location.contains("booked")) {
            bookedCount.incrementAndGet();
        }
        if (location.contains("waiting")) {
            waitingCount.incrementAndGet();
        }
    }
}
