package lk.ijse.student.bankManagementSystem.business.custom;

import lk.ijse.student.bankManagementSystem.business.SuperBussiness;
import lk.ijse.student.bankManagementSystem.dto.TransactionDTO;

import java.util.List;

public interface TransactionBusiness extends SuperBussiness {

    public boolean addTransaction(TransactionDTO dto) throws Exception;

    public boolean deleteTransaction(TransactionDTO dto) throws Exception;

    public TransactionDTO searchTransaction(String id) throws Exception;

    public boolean updateTransaction(TransactionDTO dto) throws Exception;

    public List<TransactionDTO> getAllTransaction()throws Exception;
}
