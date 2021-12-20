package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web;

import nl.teamtwee.bep3.restaurant.payment.core.application.JobsCommandHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.JobsQueryHandler;
import nl.hu.bep3.jobboard.jobs.core.application.command.*;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.*;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.FindJobsByKeyword;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetJobById;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.ListJobs;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Job;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.JobNotFound;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.ChangeJobDescriptionRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.ChangeJobFunctionRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.KeywordRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.PostJobRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/*
    Note that we are combining ideas of REST and CQRS
    in a task-based API.
    We are still resource-centric, but allow verb-like
    commands to be added as sub-resources using POST.

    See: https://codeopinion.com/is-a-rest-api-with-cqrs-possible/
 */
@RestController
@RequestMapping("/jobs")
public class JobsController {
    private final JobsCommandHandler commandHandler;
    private final JobsQueryHandler queryHandler;

    public JobsController(JobsCommandHandler commandHandler, JobsQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public Job registerJob(@Valid @RequestBody PostJobRequest request) {
        return this.commandHandler.handle(
                new PostNewJob(request.company, request.function, request.description)
        );
    }

    @PostMapping("/{id}/change-function")
    public Job renameJob(@PathVariable UUID id, @Valid @RequestBody ChangeJobFunctionRequest request) {
        return this.commandHandler.handle(new ChangeJobFunction(id, request.function));
    }

    @PostMapping("/{id}/change-description")
    public Job changeEmail(@PathVariable UUID id, @Valid @RequestBody ChangeJobDescriptionRequest request) {
        return this.commandHandler.handle(new ChangeJobDescription(id, request.description));
    }

    @PostMapping("/{id}/keyword")
    public Job addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Job removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @GetMapping("/{id}")
    public Job findJobById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetJobById(id));
    }

    @GetMapping(params = {"keyword"})
    public List<Job> findJobsByKeyword(
            @RequestParam String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(
                new FindJobsByKeyword(keyword, orderBy, direction)
        );
    }

    @GetMapping()
    public List<Job> findJobs(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListJobs(orderBy, direction));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleCandidateNotFound(JobNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
