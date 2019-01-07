package lk.ijse.student.bankManagementSystem.repository;

import org.hibernate.Session;

public interface SuperRepository {
    public void setSession(Session session)throws Exception;
}
