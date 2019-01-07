package lk.ijse.student.bankManagementSystem.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes{
        CUSTOMER,ACCOUNT,ACCOUNTTYPE,TRANSACTION,CUSTOMERACCOUNTDETAILS;
    }

    public <T> T getSuperService(ServiceTypes types)throws Exception;
}
