package lk.ijse.student.bankManagementSystem.service.custom;

import javafx.beans.Observable;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.dto.CustomerAccountDetailDTO;
import lk.ijse.student.bankManagementSystem.observer.Subject;
import lk.ijse.student.bankManagementSystem.reservation.Reservations;
import lk.ijse.student.bankManagementSystem.service.SuperService;

import java.util.List;

public interface CustomerAccountDetailsService extends SuperService, Subject, Reservations {
    public boolean addCustomerAccountDetails(CustomerAccountDetailDTO dto)throws Exception;
    public boolean deleteCustomerAccountDetails(CustomerAccountDetailDTO dto)throws Exception;
    public boolean updateCustomerAccountDetails(CustomerAccountDetailDTO dto)throws Exception;
    public AccountTypeDTO searchCustomerAccountDetails(String id)throws Exception;
    public List<CustomerAccountDetailDTO> getAllCustomerAccountDetails()throws Exception;
}
