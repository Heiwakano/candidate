package samart.itthikorn;

import samart.itthikorn.security.ResponseDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CandidateDAO {

    public List<CandidateEntity> listAll() {
        return CandidateEntity.listAll();
    }
    public Optional<CandidateEntity> findById(long id) {
        return Optional.ofNullable(CandidateEntity.findById(id));
    }

    public CandidateEntity create(Candidate candidate) {
        CandidateEntity entity = CandidateEntity.of(candidate);
        entity.persist();
        return entity;
    }

    public CandidateEntity update(Candidate candidate, long candidateId) {
        Optional<CandidateEntity> result = findById(candidateId);
        result.ifPresentOrElse(( update) -> {
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                LocalDate dob = LocalDate.parse(candidate.getDob(), dateFormat);
                update.dob = dob;
                update.name = candidate.getName();
                update.bioLink = candidate.getBioLink();
                update.policy = candidate.getPolicy();
                update.imageLink = candidate.getImageLink();
                update.persist();
            } catch (Exception e) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }, () -> {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        });
        return result.get();
    }

    public long delete(long candidateId) {
        long numCandidateDeleted = CandidateEntity.delete("id", candidateId);

        return numCandidateDeleted;
    }
}
