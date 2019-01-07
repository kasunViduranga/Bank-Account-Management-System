package lk.ijse.student.bankManagementSystem.business.custom.impl;

import lk.ijse.student.bankManagementSystem.business.custom.CustomerAccountDetailsBusiness;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerAccountDetailDTO;

import java.util.List;

public class CustomerAccountDetailsBusinessImpl implements CustomerAccountDetailsBusiness {
    @Override
    public boolean addCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public AccountTypeDTO searchCustomerAccountDetails(String id) throws Exception {
        return null;
    }

    @Override
    public boolean updateCustomerAccountDetails(CustomerAccountDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public List<CustomerAccountDetailDTO> getAllCustomerAccountDetails() throws Exception {
        return null;
    }
}
