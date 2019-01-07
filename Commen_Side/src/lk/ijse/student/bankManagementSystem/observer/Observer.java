package lk.ijse.student.bankManagementSystem.observer;

import java.rmi.Remote;

public interface Observer extends Remote {
    public void update (String message) throws Exception;
}
