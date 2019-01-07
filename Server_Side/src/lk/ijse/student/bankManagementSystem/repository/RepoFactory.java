package lk.ijse.student.bankManagementSystem.repository;

import lk.ijse.student.bankManagementSystem.repository.custom.impl.*;

public class RepoFactory {

    public static RepoFactory repoFactory;

    public static RepoFactory getInstance(){
        if (repoFactory == null){
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }
    public enum RepoTypes{
        CUSTOMER,TRANSACTION,ACCOUNTTYPE,ACCOUNT,CUSTOMERACCOUNTDETAIL
    }

    public <T> T getRepositoryFor(RepoTypes types){
        switch (types){
            case CUSTOMER:
                return (T) new CustomerRepoImpl();
            case TRANSACTION:
                return (T) new TransactionRepoImpl();
            case ACCOUNTTYPE:
                return (T) new AccountTypeRepoImpl();
            case ACCOUNT:
                return (T) new AccountRepoImpl();
            case CUSTOMERACCOUNTDETAIL:
                return (T) new CustomerAccountDetailsRepoImpl();
            default:
                return null;
        }
    }
}
