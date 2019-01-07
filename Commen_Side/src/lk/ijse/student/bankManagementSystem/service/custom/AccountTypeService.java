package lk.ijse.student.bankManagementSystem.service.custom;

import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.observer.Subject;
import lk.ijse.student.bankManagementSystem.reservation.Reservations;
import lk.ijse.student.bankManagementSystem.service.SuperService;

import java.util.List;

public interface AccountTypeService extends SuperService, Reservations, Subject{

    public boolean addAccountType(AccountTypeDTO dto)throws Exception;
    public boolean deleteAccountType(AccountTypeDTO dto)throws Exception;
    public boolean updateAccountType(AccountTypeDTO dto)throws Exception;
    public AccountTypeDTO searchAccountType(String id)throws Exception;
    public List<AccountTypeDTO> getAllAccountType()throws Exception;
}
