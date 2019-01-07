package lk.ijse.student.bankManagementSystem.observer;

import java.rmi.Remote;

public interface Subject extends Remote {
    public void register(Observer observer)throws Exception;
    public void unregister(Observer observers)throws Exception;
    public void notifyAllObservers(String message)throws Exception;
}
