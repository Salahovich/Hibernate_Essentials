package example.Model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name="youtube")
    @URL
    private String youtube;
    @Column(name ="hobby")
    private String hobby;

    public InstructorDetails(){}

    public InstructorDetails(UUID id, String youtube, String hobby) {
        this.id = id;
        this.youtube = youtube;
        this.hobby = hobby;
    }

    public InstructorDetails(String youtube, String hobby) {
        this.youtube = youtube;
        this.hobby = hobby;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", youtube='" + youtube + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
