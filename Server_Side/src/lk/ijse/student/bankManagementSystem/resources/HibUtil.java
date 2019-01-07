package lk.ijse.student.bankManagementSystem.resources;

import lk.ijse.student.bankManagementSystem.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibUtil {

    private static SessionFactory sessionFactory = buildSessionFactor();

    private static SessionFactory buildSessionFactor() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties("lk/ijse/student/bankManagementSystem/resources/hibernate.properties").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(CustomerAccountDetail.class)
                .addAnnotatedClass(AccountType.class)
                .addAnnotatedClass(Transaction.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
