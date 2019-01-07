package lk.ijse.student.bankManagementSystem.service.custom;

import lk.ijse.student.bankManagementSystem.dto.AccountDTO;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.observer.Subject;
import lk.ijse.student.bankManagementSystem.reservation.Reservations;
import lk.ijse.student.bankManagementSystem.service.SuperService;

import java.util.List;

public interface AccountService extends SuperService, Reservations, Subject{

    public boolean addAccount(CommenDTO dto)throws Exception;
    public boolean deleteAccount(AccountDTO dto)throws Exception;
    public boolean updateAccount(AccountDTO dto)throws Exception;
    public AccountDTO searchAccount(String id)throws Exception;
    public List<AccountDTO> getAllAccount()throws Exception;
}
