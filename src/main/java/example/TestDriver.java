package example;

import example.Model.Course;
import example.Model.Instructor;
import example.Model.Student;
import example.enums.CourseLevel;
import example.enums.Gender;
import jakarta.persistence.Tuple;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestDriver {
    public record StudentSummary(String firstName, Integer age) {}

    // lifeCycle Events
    public static void persistEvent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        Student student = new Student("Mahmoud", "Adams", 36, Gender.MALE, "sdfdsf@gmail.com","Fdsfsdfds","Ffsdfdsf");
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

    // relationships
    public static void getCoursesOfStudent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Student student = session.get(Student.class, UUID.fromString("b1cf0c7c-bc5a-4c66-87dd-1ea502061dd5"));
        System.out.println(student);
        System.out.println(student.getCourses());

        session.getTransaction().commit();
        session.close();
    }
    public static void getStudentsOfCourse(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Course course = session.get(Course.class, UUID.fromString("cce54af2-520f-11ee-9453-00be4330ee23"));
        System.out.println(course);
        System.out.println(course.getStudents());

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
    public static void theFromClause(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> list = query.list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClause(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<String> query = session.createQuery("select stu.firstName from Student stu", String.class);
        List<String> list = query.list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion1(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        List<Object[]> result = session.createQuery("select stu.firstName, stu.gender, stu.age from Student stu", Object[].class).
                                getResultList();

        for(Object[] row : result){
            String firstName = (String) row[0];
            Gender gender = (Gender) row[1];
            int age = (int) row[2];
            System.out.println(firstName + ", " + gender + ", " + age);
        }

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion2() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        List<Tuple> result = session.createQuery("select stu.firstName, stu.gender, stu.age from Student stu", Tuple.class).
                getResultList();

        // must specify aliases for columns or access it by index starting from zero
        for (Tuple tuple : result) {
            String firstName = tuple.get(0, String.class);
            Gender gender = tuple.get(1, Gender.class);
            int age = tuple.get(2, Integer.class);
            System.out.println(firstName + ", " + gender + ", " + age);
        }

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion3() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var result = session.createQuery("select stu.firstName as fn, stu.gender as ge, stu.age as age from Student stu", Map.class).
                getResultList();

        for (var map : result) {
            String firstName = (String) map.get("fn");
            Gender gender =(Gender) map.get("ge");
            int age = (Integer) map.get("age");
            System.out.println(firstName + ", " + gender + ", " + age);
        }

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion4() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var result = session.createQuery("select stu.firstName, stu.gender, stu.age from Student stu", List.class).
                getResultList();

        for (var list : result) {
            String firstName = (String) list.get(0);
            Gender gender =(Gender) list.get(1);
            int age = (Integer) list.get(2);
            System.out.println(firstName + ", " + gender + ", " + age);
        }

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion5() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var result = session.createQuery("select stu.firstName, stu.age from Student stu", StudentSummary.class).
                getResultList();

        for (var studentSummary : result) {
            String firstName = studentSummary.firstName();
            int age = studentSummary.age();
            System.out.println(firstName + ", " + age);
        }

        session.getTransaction().commit();
        session.close();
    }
    public static void theSelectClauseWithMultiplePropertiesVersion6() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var result = session.createQuery("from java.lang.Object").
                getResultList();

        session.getTransaction().commit();
        session.close();
    }
    public static void theUpdateClause() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var query = session.createQuery("update Student s set s.firstName=:newName where firstName=:oldName");
        query.setParameter("newName", "Muhammad");
        query.setParameter("oldName", "Ahmed");
        int affectedRows = query.executeUpdate();
        System.out.println("affected rows = " + affectedRows);
        session.getTransaction().commit();
        session.close();
    }
    public static void deleteStudent(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query query = session.createQuery("delete from Student WHERE firstName=:name");
        query.setParameter("name", "Ahmed");
        System.out.println(query.executeUpdate());
        session.getTransaction().commit();
        session.close();
    }
    public static void theInsertClause() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var query = session.createQuery("insert into InstructorDetails (youtube, hobby) values ('youtube.com/apple', 'Football')");
        int affectedRows = query.executeUpdate();
        System.out.println("affected rows = " + affectedRows);
        session.getTransaction().commit();
        session.close();
    }
    public static void theInsertSelectClauseVersion2() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var query = session.createQuery("insert into Student (id, firstName, lastName, gender, age, email, phoneNumber, nationalId) " +
                "select id, firstName, lastName, 'MALE', 20, email, phoneNumber, '654546457' from Instructor where firstName = :name").
                setParameter("name", "Mahmoud");
        int affectedRows = query.executeUpdate();
        System.out.println("affected rows = " + affectedRows);
        session.getTransaction().commit();
        session.close();
    }
    public static void theJoinClauseVersion1() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        // This looks nice and familiar, but it’s not the most common sort of join in HQL or JPQL.
        session.beginTransaction();
        var result = session.createQuery("select course.name, course.startDate, instructor.firstName " +
                "from Course course " +
                "join Instructor instructor " +
                "on instructor = course.instructor", Tuple.class).list();

        for (Tuple tuple : result) {
            String firstName = tuple.get(0, String.class);
            LocalDateTime time = tuple.get(1, LocalDateTime.class);
            String instructor = tuple.get(2, String.class);
            System.out.println(firstName + ", " + time + ", " + instructor);
        }
        session.getTransaction().commit();
        session.close();
    }
    public static void theJoinClauseVersion2() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        // This looks nice and familiar, but it’s not the most common sort of join in HQL or JPQL.
        session.beginTransaction();
        var result = session.createQuery("select course.name, course.startDate, course.instructor.firstName " +
                "from Course course " +
                "join course.instructor"
                , Tuple.class).list();

        for (Tuple tuple : result) {
            String firstName = tuple.get(0, String.class);
            LocalDateTime time = tuple.get(1, LocalDateTime.class);
            String instructor = tuple.get(2, String.class);
            System.out.println(firstName + ", " + time + ", " + instructor);
        }
        session.getTransaction().commit();
        session.close();
    }
    public static void theCountClause() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        // This looks nice and familiar, but it’s not the most common sort of join in HQL or JPQL.
        session.beginTransaction();
        var result = session.createQuery("select count(*) from Student").getSingleResult();
        System.out.println(result);
        session.getTransaction().commit();
        session.close();
    }
    public static void theMaxClause() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        // This looks nice and familiar, but it’s not the most common sort of join in HQL or JPQL.
        session.beginTransaction();
        var result = session.createQuery("select max(age) from Student").getSingleResult();
        System.out.println(result);
        session.getTransaction().commit();
        session.close();
    }
    public static void theSumClause() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        // This looks nice and familiar, but it’s not the most common sort of join in HQL or JPQL.
        session.beginTransaction();
        var result = session.createQuery("select sum(age) from Student").getSingleResult();
        System.out.println(result);
        session.getTransaction().commit();
        session.close();
    }
    public static void theOrderByClauseASC(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<String> query = session.createQuery("select stu.firstName from Student stu order by firstName", String.class);
        List<String> list = query.list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
    }
    public static void theOrderByClauseDESC(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        Query<String> query = session.createQuery("select stu.firstName from Student stu order by firstName DESC", String.class);
        List<String> list = query.list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
    }
    public static void theNPlusOne(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var list = session.createQuery("from Instructor", Instructor.class).list();

        for(var instructor : list)
            System.out.println(instructor.getCourses());

        session.getTransaction().commit();
        session.close();
    }
    public static void theNPlusOneSolution(){
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        session.beginTransaction();
        var list = session.createQuery("from Instructor i left join fetch i.courses", Instructor.class).list();

        for(var instructor : list)
            System.out.println(instructor.getCourses());

        session.getTransaction().commit();
        session.close();
    }
    public static void main(String[] args) {
        theNPlusOne();
        HibernateUtil.getSessionJavaConfigFactory().close();
    }
}
