package lk.ijse.student.bankManagementSystem.business;

import lk.ijse.student.bankManagementSystem.business.custom.impl.AccountBusinessImpl;
import lk.ijse.student.bankManagementSystem.business.custom.impl.AccountTypeBusinessImpl;
import lk.ijse.student.bankManagementSystem.business.custom.impl.CustomerBusinessImpl;
import lk.ijse.student.bankManagementSystem.business.custom.impl.TransactionBusinessImpl;

public class BusinessFactory {

    private static BusinessFactory businessFactory;

    public static BusinessFactory getInstance(){
        if(businessFactory == null){
            businessFactory = new BusinessFactory();
        }
        return businessFactory;
    }

    public enum BusinessTypes{
        CUSTOMER,TRANSACTION,ACCOUNTTYPE,ACCOUNT
    }

    public <T> T getBusinessFOR(BusinessTypes types){
        switch (types){
            case CUSTOMER:
                return (T) new CustomerBusinessImpl();
            case TRANSACTION:
                return (T) new TransactionBusinessImpl();
            case ACCOUNTTYPE:
                return (T) new AccountTypeBusinessImpl();
            case ACCOUNT:
                return (T) new AccountBusinessImpl();
            default:
                return null;
        }
    }
}
