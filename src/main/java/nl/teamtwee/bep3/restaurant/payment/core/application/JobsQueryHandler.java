package nl.teamtwee.bep3.restaurant.payment.core.application;

import nl.teamtwee.bep3.restaurant.payment.core.application.query.FindJobsByKeyword;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetJobById;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.ListJobs;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Job;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.JobNotFound;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.JobRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsQueryHandler {
    private final JobRepository repository;

    public JobsQueryHandler(JobRepository repository) {
        this.repository = repository;
    }

    public Job handle(GetJobById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new JobNotFound(query.getId().toString()));
    }

    public List<Job> handle(FindJobsByKeyword query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByKeywordsEquals(query.getKeyword(), sort);
    }

    public List<Job> handle(ListJobs query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
