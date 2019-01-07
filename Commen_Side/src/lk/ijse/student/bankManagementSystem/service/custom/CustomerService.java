package lk.ijse.student.bankManagementSystem.service.custom;

import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CommenDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerDTO;
import lk.ijse.student.bankManagementSystem.observer.Subject;
import lk.ijse.student.bankManagementSystem.reservation.Reservations;
import lk.ijse.student.bankManagementSystem.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService, Reservations, Subject{

    public boolean addCustomer(CommenDTO dto)throws Exception;
    public boolean deleteCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public CustomerDTO searchCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomer()throws Exception;
}
