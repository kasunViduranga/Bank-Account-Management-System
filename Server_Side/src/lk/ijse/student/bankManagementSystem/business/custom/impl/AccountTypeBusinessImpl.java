package lk.ijse.student.bankManagementSystem.business.custom.impl;

import lk.ijse.student.bankManagementSystem.business.custom.AccountTypeBusiness;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.entity.AccountType;
import lk.ijse.student.bankManagementSystem.repository.RepoFactory;
import lk.ijse.student.bankManagementSystem.repository.custom.AccountTypeRepo;
import lk.ijse.student.bankManagementSystem.resources.HibUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AccountTypeBusinessImpl implements AccountTypeBusiness {
   AccountTypeRepo accountTypeRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.ACCOUNTTYPE);

    public AccountTypeBusinessImpl() {
    }

    @Override
    public boolean addAccountType(AccountTypeDTO dto) throws Exception {
        Session session=HibUtil.getSessionFactory().openSession();
        accountTypeRepo.setSession(session);
        session.getTransaction().begin();
        boolean response=accountTypeRepo.add(new AccountType(dto.getAccTypeID(),dto.getAccType(),dto.getRate(),dto.getMinAmouOf1stDep(),dto.getCountOfOwners()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean deleteAccountType(AccountTypeDTO dto) throws Exception {
        Session session = HibUtil.getSessionFactory().openSession();
        accountTypeRepo.setSession(session);
        session.getTransaction().begin();
        boolean response = accountTypeRepo.delete(new AccountType(dto.getAccTypeID(),dto.getAccType(),dto.getRate(),dto.getMinAmouOf1stDep(),dto.getCountOfOwners()));
        session.getTransaction().commit();
        return response;
    }

    @Override
    public AccountTypeDTO searchAccountType(String id) throws Exception {
        Session session = HibUtil.getSessionFactory().openSession();
        accountTypeRepo.setSession(session);
        session.getTransaction().begin();
        AccountType serch = accountTypeRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new AccountTypeDTO(serch.getAccTypeID_PK(),serch.getAccType(),serch.getRate(),serch.getMinAmouOf1stDep(),serch.getCountOfOwners());
    }

    @Override
    public boolean updateAccountType(AccountTypeDTO dto) throws Exception {
        Session session = HibUtil.getSessionFactory().openSession();
        accountTypeRepo.setSession(session);
        session.getTransaction().begin();
        boolean response = accountTypeRepo.update(new AccountType(dto.getAccTypeID(),dto.getAccType(),dto.getRate(),dto.getMinAmouOf1stDep(),dto.getCountOfOwners()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public List<AccountTypeDTO> getAllAccountType() throws Exception {
        ArrayList<AccountTypeDTO> accountTypeDTOS = new ArrayList<>();
        try (Session session = HibUtil.getSessionFactory().openSession()){
            accountTypeRepo.setSession(session);
            session.getTransaction().begin();
            List<AccountType> accountTypes= accountTypeRepo.getAll();
            for (AccountType a : accountTypes){
                accountTypeDTOS.add(new AccountTypeDTO(a.getAccTypeID_PK(),a.getAccType(),a.getRate(),a.getMinAmouOf1stDep(),a.getCountOfOwners()));
            }
            session.getTransaction().commit();
        }
        return accountTypeDTOS;
    }
}
