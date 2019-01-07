package lk.ijse.student.bankManagementSystem.business.custom;

import lk.ijse.student.bankManagementSystem.business.SuperBussiness;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerDTO;

import java.util.List;

public interface CustomerBusiness extends SuperBussiness {

    public boolean addCustomer(CommenDTO dto) throws Exception;

    public boolean deleteCustomer(CustomerDTO dto) throws Exception;

    public CustomerDTO searcCustomer(String id) throws Exception;

    public boolean updateCustomer(CustomerDTO dto) throws Exception;

    public List<CustomerDTO> getAllCustomers()throws Exception;
}
