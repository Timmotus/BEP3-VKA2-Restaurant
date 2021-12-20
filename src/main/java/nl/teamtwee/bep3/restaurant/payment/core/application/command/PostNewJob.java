package nl.teamtwee.bep3.restaurant.payment.core.application.command;

public class PostNewJob {
    private final String company;
    private final String function;
    private final String description;

    public PostNewJob(String company, String function, String description) {
        this.company = company;
        this.function = function;
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public String getFunction() {
        return function;
    }

    public String getDescription() {
        return description;
    }
}
