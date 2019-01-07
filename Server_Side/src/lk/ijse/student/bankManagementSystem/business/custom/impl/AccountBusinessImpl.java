package lk.ijse.student.bankManagementSystem.business.custom.impl;

import lk.ijse.student.bankManagementSystem.business.custom.AccountBusiness;
import lk.ijse.student.bankManagementSystem.business.custom.CustomerBusiness;
import lk.ijse.student.bankManagementSystem.dto.AccountDTO;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerDTO;
import lk.ijse.student.bankManagementSystem.entity.Account;
import lk.ijse.student.bankManagementSystem.entity.AccountType;
import lk.ijse.student.bankManagementSystem.entity.Customer;
import lk.ijse.student.bankManagementSystem.entity.CustomerAccountDetail;
import lk.ijse.student.bankManagementSystem.repository.RepoFactory;
import lk.ijse.student.bankManagementSystem.repository.custom.AccountRepo;
import lk.ijse.student.bankManagementSystem.repository.custom.CustomerAccountDetailsRepo;
import lk.ijse.student.bankManagementSystem.repository.custom.CustomerRepo;
import lk.ijse.student.bankManagementSystem.resources.HibUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AccountBusinessImpl implements AccountBusiness {

    AccountRepo accountRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.ACCOUNT);
    CustomerAccountDetailsRepo customerAccountDetailsRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.CUSTOMERACCOUNTDETAIL);
    CustomerRepo customerRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.CUSTOMER);
    public AccountBusinessImpl() {

    }

    @Override
    public boolean addAccount(CommenDTO dto) throws Exception {
        try (Session session=HibUtil.getSessionFactory().openSession()) {

            Customer customer1=new Customer();
            customer1.setCusID_PK(dto.getCustomerDTO().getCusID());
            AccountType accountType=session.get(AccountType.class,dto.getAccountDTO().getAccTypeID());
            Account account1=new Account();
            account1.setAccNum_PK(dto.getAccountDTO().getAccNum());
            customerRepo.setSession(session);
            customerAccountDetailsRepo.setSession(session);
            accountRepo.setSession(session);
            session.getTransaction().begin();
            accountRepo.add(new Account(dto.getAccountDTO().getAccNum(),dto.getAccountDTO().getOpenDate(),dto.getAccountDTO().getOpenTime(),
                    dto.getAccountDTO().getTotal(),dto.getAccountDTO().getNic(),accountType));
            customerRepo.add(new Customer(dto.getCustomerDTO().getCusID(),dto.getCustomerDTO().getFirstName(),dto.getCustomerDTO().getLastName(),
                    dto.getCustomerDTO().getAddress(),dto.getCustomerDTO().getOwnerType(),dto.getCustomerDTO().getDob(),dto.getCustomerDTO().getNic(),
                    dto.getCustomerDTO().getGender(),dto.getCustomerDTO().getTell()));

            session.save(new CustomerAccountDetail(customer1,account1));
            session.getTransaction().commit();
            }
            return true;

    }

    @Override
    public boolean deleteAccount(AccountDTO dto) throws Exception {
        return false;
    }

    @Override
    public AccountDTO searchAccount(String id) throws Exception {
        Session session = HibUtil.getSessionFactory().openSession();
        accountRepo.setSession(session);
        session.getTransaction().begin();
        Account serch = accountRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new AccountDTO(serch.getAccNum_PK(), serch.getOpenDate(), serch.getOpenTime(), serch.getTotal(),serch.getAccountType().getAccTypeID_PK(), serch.getNic());
    }

    @Override
    public boolean updateAccount(AccountDTO dto) throws Exception {
        Session session = HibUtil.getSessionFactory().openSession();
        accountRepo.setSession(session);
        session.getTransaction().begin();
        AccountType accountType=session.get(AccountType.class,dto.getAccTypeID());
        boolean response = accountRepo.update(new Account(dto.getAccNum(),dto.getOpenDate(),dto.getOpenTime(),dto.getTotal(),dto.getNic(),accountType));
        session.getTransaction().commit();
        session.close();
        return response;
    }
    @Override
    public List<AccountDTO> getAllAccount()throws Exception{
        ArrayList<AccountDTO> accountDTOS=new ArrayList<>();
        try (Session session=HibUtil.getSessionFactory().openSession()) {
            accountRepo.setSession(session);
            session.getTransaction().begin();
            List<Account> accounts = accountRepo.getAll();
            for (Account a : accounts) {
                accountDTOS.add(new AccountDTO(a.getAccNum_PK(), a.getOpenDate(), a.getOpenTime(), a.getTotal(),a.getAccountType().getAccTypeID_PK(), a.getNic()));
            }
            session.getTransaction().commit();
        }
        return accountDTOS;
    }

}
