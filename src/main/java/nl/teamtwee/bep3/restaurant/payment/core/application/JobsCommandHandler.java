package nl.teamtwee.bep3.restaurant.payment.core.application;

import nl.hu.bep3.jobboard.jobs.core.application.command.*;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.*;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Job;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.JobEvent;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.JobNotFound;
import nl.teamtwee.bep3.restaurant.payment.core.ports.messaging.JobEventPublisher;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobsCommandHandler {
    private final JobRepository repository;
    private final JobEventPublisher eventPublisher;

    public JobsCommandHandler(JobRepository repository, JobEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Job handle(PostNewJob command) {
        Job job = new Job(command.getCompany(), command.getFunction(), command.getDescription());

        this.publishEventsAndSave(job);

        return job;
    }

    public Job handle(ChangeJobFunction command) {
        Job job = this.getJobById(command.getId());
        job.changeFunction(command.getFunction());

        this.publishEventsAndSave(job);

        return job;
    }

    public Job handle(ChangeJobDescription command) {
        Job job = this.getJobById(command.getId());
        job.changeDescription(command.getDescription());

        this.publishEventsAndSave(job);

        return job;
    }

    public Job handle(AddKeyword command) {
        Job job = this.getJobById(command.getId());
        job.addKeyword(command.getKeyword());

        this.publishEventsAndSave(job);

        return job;
    }

    public Job handle(RemoveKeyword command) {
        Job job = this.getJobById(command.getId());
        job.removeKeyword(command.getKeyword());

        this.publishEventsAndSave(job);

        return job;
    }

    private Job getJobById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new JobNotFound(id.toString()));
    }

    private void publishEventsAndSave(Job job) {
        List<JobEvent> events = job.listEvents();
        events.forEach(eventPublisher::publish);
        job.clearEvents();

        this.repository.save(job);
    }
}
