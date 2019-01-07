package lk.ijse.student.bankManagementSystem.business.custom.impl;

import lk.ijse.student.bankManagementSystem.business.custom.CustomerBusiness;
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

public class CustomerBusinessImpl implements CustomerBusiness {

    AccountRepo accountRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.ACCOUNT);
    CustomerAccountDetailsRepo customerAccountDetailsRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.CUSTOMERACCOUNTDETAIL);
    CustomerRepo customerRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CommenDTO dto) throws Exception {
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

            session.save(new CustomerAccountDetail(customer1,account1));
            session.getTransaction().commit();
        }
        return true;

    }




    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws Exception {
        return false;
    }

    @Override
    public CustomerDTO searcCustomer(String id) throws Exception {
        Session session=HibUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        session.getTransaction().begin();
        Customer serch=customerRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new CustomerDTO(serch.getCusID_PK(),serch.getFirstName(),serch.getLastName(),serch.getAddress(),serch.getOwnerType(),serch.getDob(),serch.getNic(),serch.getGender(),serch.getTell());
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return false;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        try (Session session=HibUtil.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.getTransaction().begin();
            List<Customer> customers=customerRepo.getAll();
            for (Customer c:customers){
                customerDTOS.add(new CustomerDTO(c.getCusID_PK(),c.getFirstName(),c.getLastName(),c.getAddress(),c.getOwnerType(),c.getDob(),c.getNic(),c.getGender(),c.getTell()));
            }
            session.getTransaction().commit();
        }
        return customerDTOS;
    }
}
