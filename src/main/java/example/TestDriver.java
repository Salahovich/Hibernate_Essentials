package example;

import example.Model.Course;
import example.Model.Instructor;
import example.Model.Student;
import example.enums.Gender;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class TestDriver {

    // lifeCycle Events
    public static void persistEvent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        Student student = new Student("John", "Adams", 36, Gender.MALE, "sdfdsf","Fdsfsdfds","Ffsdfdsf");
        session.beginTransaction();

        session.persist(student);
        session.getTransaction().commit();
        session.close();
    }
    public static void loadAndRemoveEvent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Student student = session.get(Student.class, UUID.fromString("b602bc2d-4ce8-4f33-942c-20c18759b993"));

        session.remove(student);
        session.getTransaction().commit();
        session.close();
    }

    // validation


    // relationships
    public static void getCoursesOfStudent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Student student = session.get(Student.class, UUID.fromString("fef5860f-9705-4965-a490-ca4fe65f21e4"));
        System.out.println(student);

        session.getTransaction().commit();
        session.close();
    }
    public static void getStudentsOfCourse(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Course course = session.get(Course.class, UUID.fromString("cce54af2-520f-11ee-9453-00be4330ee23"));
        System.out.println(course);

        session.getTransaction().commit();
        session.close();
    }
    public static void getInstructorCourses(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, UUID.fromString("42d7d5b3-ef90-44c2-812e-df9f32ccf646"));
        System.out.println(instructor);

        session.getTransaction().commit();
        session.close();
    }

    // Cascading
    public static void deleteInstructor(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, UUID.fromString("42d7d5b3-ef90-44c2-812e-df9f32ccf646"));
        session.remove(instructor);

        session.getTransaction().commit();
        session.close();
    }

    // HQL
    public static void selectAllStudents(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        List<Student> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }
    public static void deleteStudent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<Student> query = session.createQuery("delete from Student WHERE firstName=:name", Student.class);
        query.setParameter("name", "Ahmed");
        int affectedRows  = query.executeUpdate();
        System.out.println(affectedRows);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        HibernateUtil.getSessionJavaConfigFactory().close();
    }
}
