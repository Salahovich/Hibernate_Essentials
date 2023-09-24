package example.Model;

import example.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name= "student")
public class Student {

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
    @NotEmpty
    private String lastName;
    @Column(name = "age")
    @Range(min = 18, max = 40)
    private int age;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "email", unique = true)
    @Email
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "national_id")
    private String nationalId;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
    @Transient
    private String fullName;
    public Student(){}

    public Student(UUID id, String firstName, String lastName, int age, Gender gender, String email, String phoneNumber, String nationalId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
    }

    public Student(String firstName, String lastName, int age, Gender gender, String email, String phoneNumber, String nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String national_id) {
        this.nationalId = national_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", courses=" + courses +
                ", fullName='" + fullName + '\'' +
                '}' + "\n";
    }

    @PrePersist
    public void logNewStudentAttempt() {
        System.out.println("Attempting to add new user with firstName: " + firstName);
    }

    @PostPersist
    public void logNewStudentAdded() {
        System.out.println("Added user '" + firstName + "' with ID: " + id);
    }

    @PreRemove
    public void logStudentRemovalAttempt() {
        System.out.println("Attempting to delete user: " + firstName);
    }

    @PostRemove
    public void logStudentRemoval() {
        System.out.println("Deleted user: " + firstName);
    }

    @PreUpdate
    public void logStudentUpdateAttempt() {
        System.out.println("Attempting to update user: " + firstName);
    }

    @PostUpdate
    public void logStudentUpdate() {
        System.out.println("Updated user: " + firstName);
    }

    @PostLoad
    public void logStudentLoad() {
        fullName = firstName + " " + lastName;
    }
}
