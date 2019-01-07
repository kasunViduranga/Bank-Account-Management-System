package lk.ijse.student.bankManagementSystem.business.custom;

import lk.ijse.student.bankManagementSystem.business.SuperBussiness;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerAccountDetailDTO;

import java.util.List;

public interface CustomerAccountDetailsBusiness extends SuperBussiness {

    public boolean addCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception;

    public boolean deleteCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception;

    public AccountTypeDTO searchCustomerAccountDetails(String id) throws Exception;

    public boolean updateCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception;

    public List<CustomerAccountDetailDTO> getAllCustomerAccountDetails()throws Exception;
}
