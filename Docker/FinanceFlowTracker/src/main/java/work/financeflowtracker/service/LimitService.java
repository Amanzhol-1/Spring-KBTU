package work.financeflowtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.financeflowtracker.entity.Limit;
import work.financeflowtracker.repository.LimitRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LimitService {
    private final LimitRepository limitRepository;

    @Autowired
    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public Limit setNewLimit(Limit limit) {
        LocalDate today = LocalDate.now();

        limit.setStartDate(today);

        if (limit.getEndDate().isBefore(today)) {
            throw new IllegalArgumentException("End date cannot be before today's date.");
        }

        if (limit.getId() != null) {
            throw new IllegalArgumentException("Updating existing limits is not allowed.");
        }

        return limitRepository.save(limit);
    }

    public List<Limit> findAllLimits() {
        return limitRepository.findAll();
    }

    public List<Limit> getLimitsByCategory(String category) {
        return limitRepository.findByLimitCategory(category);
    }
}
