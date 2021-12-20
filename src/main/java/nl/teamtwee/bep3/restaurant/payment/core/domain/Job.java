package nl.teamtwee.bep3.restaurant.payment.core.domain;

import nl.teamtwee.bep3.restaurant.payment.core.domain.event.JobEvent;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.KeywordAddedToJob;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.KeywordRemovedFromJob;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Job {
    @Id
    private UUID id;
    @Indexed
    private String company;
    private String function;
    private String description;
    @Indexed
    private Set<String> keywords;
    @Transient
    private List<JobEvent> events = new ArrayList<>();

    public Job(String company, String function, String description) {
        this.id = UUID.randomUUID();
        this.company = company;
        this.function = function;
        this.description = description;
        this.keywords = new HashSet<>();
    }

    public void changeFunction(String function) {
        this.function = function;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new KeywordAddedToJob(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new KeywordRemovedFromJob(id, keyword));
    }

    public UUID getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public List<JobEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
