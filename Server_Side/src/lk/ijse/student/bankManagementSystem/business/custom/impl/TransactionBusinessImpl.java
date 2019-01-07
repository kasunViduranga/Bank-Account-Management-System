package lk.ijse.student.bankManagementSystem.business.custom.impl;

import lk.ijse.student.bankManagementSystem.business.custom.TransactionBusiness;
import lk.ijse.student.bankManagementSystem.dto.TransactionDTO;
import lk.ijse.student.bankManagementSystem.entity.Transaction;
import lk.ijse.student.bankManagementSystem.repository.RepoFactory;
import lk.ijse.student.bankManagementSystem.repository.custom.TransactionRepo;
import lk.ijse.student.bankManagementSystem.resources.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class TransactionBusinessImpl implements TransactionBusiness {

    TransactionRepo transactionRepo;

    public TransactionBusinessImpl() {
        transactionRepo=RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.TRANSACTION);
    }

    @Override
    public boolean addTransaction(TransactionDTO dto) throws Exception {
        Session session=HibUtil.getSessionFactory().openSession();
        transactionRepo.setSession(session);
        session.getTransaction().begin();
        boolean response=transactionRepo.add(new Transaction(dto.getTranId(), dto.getTranType(), dto.getAccNum(), dto.getTranFee(), dto.getTranDate(), dto.getTranTime(), dto.getTranPersNIC()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean deleteTransaction(TransactionDTO dto) throws Exception {
        return false;
    }

    @Override
    public TransactionDTO searchTransaction(String id) throws Exception {
        return null;
    }

    @Override
    public boolean updateTransaction(TransactionDTO dto) throws Exception {
        return false;
    }

    @Override
    public List<TransactionDTO> getAllTransaction() throws Exception {
        return null;
    }
}
