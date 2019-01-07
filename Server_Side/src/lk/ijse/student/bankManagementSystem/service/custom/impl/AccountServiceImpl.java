package lk.ijse.student.bankManagementSystem.service.custom.impl;

import lk.ijse.student.bankManagementSystem.business.BusinessFactory;
import lk.ijse.student.bankManagementSystem.business.custom.AccountBusiness;
import lk.ijse.student.bankManagementSystem.dto.AccountDTO;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.reservation.Impl.ReservationImpl;
import lk.ijse.student.bankManagementSystem.service.custom.AccountService;
import lk.ijse.student.bankManagementSystem.service.custom.AccountTypeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl extends UnicastRemoteObject implements AccountService {

    AccountBusiness accountBusiness;
    private static ArrayList<Observer> allAccountObserver = new ArrayList<>();
    private static ReservationImpl<AccountService> accountReservation = new ReservationImpl<>();


    public AccountServiceImpl() throws Exception {
        accountBusiness=BusinessFactory.getInstance().getBusinessFOR(BusinessFactory.BusinessTypes.ACCOUNT);
    }

    @Override
    public boolean addAccount(CommenDTO dto) throws Exception {
        boolean add = accountBusiness.addAccount(dto);
        if (add){
            notifyAllObservers("add");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(AccountDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateAccount(AccountDTO dto) throws Exception {
        if (accountReservation.reserve(dto.getAccTypeID(),this,true)){
            boolean isUpdate = accountBusiness.updateAccount(dto);
            if (isUpdate){
                if (accountReservation.checkState(dto.getAccTypeID(),this)){
                    accountReservation.release(dto.getAccTypeID(),this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AccountDTO searchAccount(String id) throws Exception {
        return accountBusiness.searchAccount(id);
    }

    @Override
    public List<AccountDTO> getAllAccount() throws Exception {
        return accountBusiness.getAllAccount();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allAccountObserver.add(observer);
    }

    @Override
    public void unregister(Observer observers) throws Exception {
        allAccountObserver.remove(observers);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer allObserver:allAccountObserver){
            new Thread(()->{
                try {
                    allObserver.update(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return accountReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}
