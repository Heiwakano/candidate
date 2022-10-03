package samart.itthikorn;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "Candidate")
public class CandidateEntity extends PanacheEntity  {
    @Column(unique = true)
    public String name;
    @JsonbDateFormat(value = "MMMM d, yyyy")
    public LocalDate dob;
    public String bioLink;
    public String imageLink;
    public String policy;
    public int votedCount;

    public CandidateEntity(String name, LocalDate dob, String bioLink, String imageLink, String policy, int votedCount) {
        this.name = name;
        this.dob = dob;
        this.bioLink = bioLink;
        this.imageLink = imageLink;
        this.policy = policy;
        this.votedCount = votedCount;
    }

    public CandidateEntity(String name, LocalDate dob, String bioLink, String imageLink, String policy) {
        this(name, dob, bioLink, imageLink, policy, 0);
    }

    public static CandidateEntity of (Candidate candidate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate dob = LocalDate.parse(candidate.getDob(), dateFormat);
        return new CandidateEntity(candidate.getName(), dob, candidate.getBioLink(), candidate.getImageLink(), candidate.getPolicy());
    }

    public CandidateEntity() {

    }
}
