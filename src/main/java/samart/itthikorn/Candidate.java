package samart.itthikorn;

import javax.json.bind.annotation.JsonbCreator;

public class Candidate {
    private String name;
    private String dob;
    private String bioLink;
    private String imageLink;
    private String policy;

    @JsonbCreator
    public Candidate(String name, String dob, String bioLink, String imageLink, String policy) {
        this.name = name;
        this.dob = dob;
        this.bioLink = bioLink;
        this.imageLink = imageLink;
        this.policy = policy;
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
}
