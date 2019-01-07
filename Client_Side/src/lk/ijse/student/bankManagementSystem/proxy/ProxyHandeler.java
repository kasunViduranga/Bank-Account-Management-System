package lk.ijse.student.bankManagementSystem.proxy;

import lk.ijse.student.bankManagementSystem.service.ServiceFactory;
import lk.ijse.student.bankManagementSystem.service.custom.*;
import sun.print.resources.serviceui_zh_TW;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandeler implements ServiceFactory {

    private static ProxyHandeler proxyHandeler;
    private AccountTypeService accountTypeService;
    private AccountService accountService;
    private CustomerService customerService;
    private TransactionService transactionService;
    private CustomerAccountDetailsService customerAccountDetailsService;

    public static ProxyHandeler getIstance() throws Exception{
        if(proxyHandeler == null){
            proxyHandeler = new ProxyHandeler();
        }
        return proxyHandeler;
    }

    public ProxyHandeler()  {

        try {
            ServiceFactory  serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:6050/bank");
            accountTypeService=serviceFactory.getSuperService(ServiceTypes.ACCOUNTTYPE);
            accountService=serviceFactory.getSuperService(ServiceTypes.ACCOUNT);
            customerService=serviceFactory.getSuperService(ServiceTypes.CUSTOMER);
            transactionService=serviceFactory.getSuperService(ServiceTypes.TRANSACTION);
            customerAccountDetailsService=serviceFactory.getSuperService(ServiceTypes.CUSTOMERACCOUNTDETAILS);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case ACCOUNTTYPE:
                return (T) accountTypeService;
            case ACCOUNT:
                return (T) accountService;
            case TRANSACTION:
                return (T) transactionService;
            case CUSTOMER:
                return (T) customerService;
            default:
                return null;
        }
    }
}
