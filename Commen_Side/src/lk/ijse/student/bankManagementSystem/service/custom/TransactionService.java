package lk.ijse.student.bankManagementSystem.service.custom;

import lk.ijse.student.bankManagementSystem.dto.TransactionDTO;
import lk.ijse.student.bankManagementSystem.observer.Subject;
import lk.ijse.student.bankManagementSystem.reservation.Reservations;
import lk.ijse.student.bankManagementSystem.service.SuperService;

import java.util.List;

public interface TransactionService extends SuperService, Reservations, Subject{

    public boolean addTransaction(TransactionDTO dto)throws Exception;
    public boolean deleteTransaction(TransactionDTO dto)throws Exception;
    public boolean updateTransaction(TransactionDTO dto)throws Exception;
    public TransactionDTO searchTransaction(String id)throws Exception;
    public List<TransactionDTO> getAllTransaction()throws Exception;
}
