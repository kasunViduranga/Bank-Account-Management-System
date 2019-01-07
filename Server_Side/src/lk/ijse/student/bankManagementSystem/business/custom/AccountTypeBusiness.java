package lk.ijse.student.bankManagementSystem.business.custom;

import lk.ijse.student.bankManagementSystem.business.SuperBussiness;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;

import java.util.List;

public interface AccountTypeBusiness extends SuperBussiness {

    public boolean addAccountType(AccountTypeDTO dto) throws Exception;

    public boolean deleteAccountType(AccountTypeDTO dto) throws Exception;

    public AccountTypeDTO searchAccountType(String id) throws Exception;

    public boolean updateAccountType(AccountTypeDTO dto) throws Exception;

    public List<AccountTypeDTO> getAllAccountType()throws Exception;
}
