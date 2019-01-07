package lk.ijse.student.bankManagementSystem.service.custom.impl;

import lk.ijse.student.bankManagementSystem.business.BusinessFactory;
import lk.ijse.student.bankManagementSystem.business.custom.CustomerBusiness;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerDTO;
import lk.ijse.student.bankManagementSystem.service.custom.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {

    CustomerBusiness customerBusiness;
    private static ArrayList<Observer> allCustomerObservers = new ArrayList<>();


    public CustomerServiceImpl() throws Exception {
        customerBusiness=BusinessFactory.getInstance().getBusinessFOR(BusinessFactory.BusinessTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CommenDTO dto) throws Exception {
        return customerBusiness.addCustomer(dto);
    }

    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        return customerBusiness.searcCustomer(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        return customerBusiness.getAllCustomers();
    }

    @Override
    public void register(lk.ijse.student.bankManagementSystem.observer.Observer observer) throws Exception {

    }

    @Override
    public void unregister(lk.ijse.student.bankManagementSystem.observer.Observer observers) throws Exception {

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
