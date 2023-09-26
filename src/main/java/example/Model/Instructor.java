package example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "first_name")
    @NotNull
    @NotBlank
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @NotBlank
    private String lastName;
    @Column(name = "email", unique = true)
    @Email
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private InstructorDetails details;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Course> courses;
    public Instructor(){}
    public Instructor(String firstName, String lastName, String email, String phoneNumber, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }
    public Instructor(UUID id, String firstName, String lastName, String email, String phoneNumber, String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public InstructorDetails getDetails() {
        return details;
    }

    public void setDetails(InstructorDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
