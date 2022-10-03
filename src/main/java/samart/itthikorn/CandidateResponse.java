package samart.itthikorn;

import java.time.format.DateTimeFormatter;

public class CandidateResponse {
    private Long id;
    private String name;
    private String dob;
    private String bioLink;
    private String imageLink;
    private String policy;
    private int votedCount;

    public CandidateResponse(Long id, String name, String dob, String bioLink, String imageLink, String policy, int votedCount) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.bioLink = bioLink;
        this.imageLink = imageLink;
        this.policy = policy;
        this.votedCount = votedCount;
    }

    public static CandidateResponse of (CandidateEntity entity) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String dobStr = entity.dob.format(dateFormat);
        return new CandidateResponse(entity.id, entity.name, dobStr, entity.bioLink, entity.imageLink, entity.policy, entity.votedCount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBioLink() {
        return bioLink;
    }

    public void setBioLink(String bioLink) {
        this.bioLink = bioLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public int getVotedCount() {
        return votedCount;
    }

    public void setVotedCount(int votedCount) {
        this.votedCount = votedCount;
    }
}
