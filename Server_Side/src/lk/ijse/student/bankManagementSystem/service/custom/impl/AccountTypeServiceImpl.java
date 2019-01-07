package lk.ijse.student.bankManagementSystem.service.custom.impl;

import lk.ijse.student.bankManagementSystem.business.BusinessFactory;
import lk.ijse.student.bankManagementSystem.business.custom.AccountTypeBusiness;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.reservation.Impl.ReservationImpl;
import lk.ijse.student.bankManagementSystem.service.custom.AccountTypeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AccountTypeServiceImpl extends UnicastRemoteObject implements AccountTypeService {
    AccountTypeBusiness accountTypeBusiness = BusinessFactory.getInstance().getBusinessFOR(BusinessFactory.BusinessTypes.ACCOUNTTYPE);
    private static ArrayList<Observer> allAccountTypeObserver = new ArrayList<>();
    private static ReservationImpl<AccountTypeService>  accountTypeReservation = new ReservationImpl<>();

    public AccountTypeServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addAccountType(AccountTypeDTO dto) throws Exception {
        boolean add = accountTypeBusiness.addAccountType(dto);
        if (add){
            notifyAllObservers("add");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccountType(AccountTypeDTO dto) throws Exception {
                if (accountTypeReservation.reserve(dto.getAccTypeID(),this,true)){
            boolean deleteAccType=accountTypeBusiness.deleteAccountType(dto);
            if (deleteAccType){
                if (accountTypeReservation.checkState(dto.getAccTypeID(),this)){
                    accountTypeReservation.release(dto.getAccTypeID(),this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateAccountType(AccountTypeDTO dto) throws Exception {
        if (accountTypeReservation.reserve(dto.getAccTypeID(),this,true)){
            boolean isUpdate = accountTypeBusiness.updateAccountType(dto);
            if (isUpdate){
                if (accountTypeReservation.checkState(dto.getAccTypeID(),this)){
                    accountTypeReservation.release(dto.getAccTypeID(),this);
                }
            }
        }
        return false;
    }

    @Override
    public AccountTypeDTO searchAccountType(String id) throws Exception {
        return accountTypeBusiness.searchAccountType(id);
    }

    @Override
    public List<AccountTypeDTO> getAllAccountType() throws Exception {
        return accountTypeBusiness.getAllAccountType();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allAccountTypeObserver.add(observer);
    }

    @Override
    public void unregister(Observer observers) throws Exception {
        allAccountTypeObserver.remove(observers);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer allObserver:allAccountTypeObserver){
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
        return accountTypeReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return accountTypeReservation.release(id,this);
    }
}
