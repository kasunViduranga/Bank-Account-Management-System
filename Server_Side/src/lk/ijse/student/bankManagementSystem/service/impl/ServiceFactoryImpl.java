package lk.ijse.student.bankManagementSystem.service.impl;

import lk.ijse.student.bankManagementSystem.service.ServiceFactory;
import lk.ijse.student.bankManagementSystem.service.custom.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public ServiceFactoryImpl() throws RemoteException {
    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInstance() throws Exception {
        if (serviceFactory == null){
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case TRANSACTION:
                return (T) new TransactionServiceImpl();
            case ACCOUNT:
                return (T) new AccountServiceImpl();
            case ACCOUNTTYPE:
                return (T) new AccountTypeServiceImpl();
            case CUSTOMERACCOUNTDETAILS:
                return (T) new CustomerAccountDetailsServiceImpl();
            default:
                return null;
        }
    }
}
