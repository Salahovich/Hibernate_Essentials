package example;

import example.Model.Course;
import example.Model.Instructor;
import example.Model.InstructorDetails;
import example.Model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionPropConfigFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Instructor.class);
            configuration.addAnnotatedClass(InstructorDetails.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionPropConfigFactory();
        return sessionJavaConfigFactory;
    }

}
