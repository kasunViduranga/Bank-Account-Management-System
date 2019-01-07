package lk.ijse.student.bankManagementSystem.service.custom.impl;

import lk.ijse.student.bankManagementSystem.business.BusinessFactory;
import lk.ijse.student.bankManagementSystem.business.custom.TransactionBusiness;
import lk.ijse.student.bankManagementSystem.dto.TransactionDTO;
import lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.service.custom.TransactionService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TransactionServiceImpl extends UnicastRemoteObject implements TransactionService {

    TransactionBusiness transactionBusiness;

    public TransactionServiceImpl() throws Exception {
        transactionBusiness = BusinessFactory.getInstance().getBusinessFOR(BusinessFactory.BusinessTypes.TRANSACTION);
    }

    @Override
    public boolean addTransaction(TransactionDTO dto) throws Exception {
        return transactionBusiness.addTransaction(dto);
    }

    @Override
    public boolean deleteTransaction(TransactionDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateTransaction(TransactionDTO dto) throws Exception {
        return false;
    }

    @Override
    public TransactionDTO searchTransaction(String id) throws Exception {
        return null;
    }

    @Override
    public List<TransactionDTO> getAllTransaction() throws Exception {
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
