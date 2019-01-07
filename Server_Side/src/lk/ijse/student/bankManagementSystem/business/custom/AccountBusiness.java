package lk.ijse.student.bankManagementSystem.business.custom;

import lk.ijse.student.bankManagementSystem.business.SuperBussiness;
import lk.ijse.student.bankManagementSystem.dto.AccountDTO;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;

import java.util.List;

public interface AccountBusiness extends SuperBussiness {

    public boolean addAccount(CommenDTO dto) throws Exception;

    public boolean deleteAccount(AccountDTO dto) throws Exception;

    public AccountDTO searchAccount(String id) throws Exception;

    public boolean updateAccount(AccountDTO dto) throws Exception;

    public List<AccountDTO> getAllAccount()throws Exception;
}
