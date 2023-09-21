package example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.UUID;

public class HibernateXMLDriver {
    public static void main(String[] args) {
        Session sessionOne = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
        Session sessionThree = HibernateUtil.getSessionAnnotationFactory().openSession();

        // your code is here

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }
}
