package lk.ijse.student.bankManagementSystem.service.custom.impl;

import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerAccountDetailDTO;
import lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.service.custom.CustomerAccountDetailsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerAccountDetailsServiceImpl extends UnicastRemoteObject implements CustomerAccountDetailsService {

    public CustomerAccountDetailsServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public AccountTypeDTO searchCustomerAccountDetails(String id) throws Exception {
        return null;
    }

    @Override
    public List<CustomerAccountDetailDTO> getAllCustomerAccountDetails() throws Exception {
        return null;
    }

    @Override
    public void register(Observer observer) throws Exception {

    }

    @Override
    public void unregister(Observer observers) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return false;
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}
