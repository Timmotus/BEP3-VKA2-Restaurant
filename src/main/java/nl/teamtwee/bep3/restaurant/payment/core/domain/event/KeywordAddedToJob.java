package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import java.util.UUID;

public class KeywordAddedToJob extends JobEvent {
    private final UUID job;
    private final String keyword;

    public KeywordAddedToJob(UUID job, String keyword) {
        this.job = job;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.job.added";
    }

    public UUID getJob() {
        return job;
    }

    public String getKeyword() {
        return keyword;
    }
}
