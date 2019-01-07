package lk.ijse.student.bankManagementSystem.dto;

public class CommenDTO implements SuperDTO{

    private AccountDTO accountDTO;
    private CustomerDTO customerDTO;
    private CustomerAccountDetailDTO customerAccountDetailDTO;

    public CommenDTO() {
    }

    public CommenDTO(AccountDTO accountDTO, CustomerDTO customerDTO, CustomerAccountDetailDTO customerAccountDetailDTO) {
        this.accountDTO = accountDTO;
        this.customerDTO = customerDTO;
        this.customerAccountDetailDTO = customerAccountDetailDTO;
    }

    public CommenDTO(AccountDTO accountDTO, CustomerAccountDetailDTO customerAccountDetailDTO) {
        this.accountDTO = accountDTO;
        this.customerAccountDetailDTO = customerAccountDetailDTO;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public CustomerAccountDetailDTO getCustomerAccountDetailDTO() {
        return customerAccountDetailDTO;
    }

    public void setCustomerAccountDetailDTO(CustomerAccountDetailDTO customerAccountDetailDTO) {
        this.customerAccountDetailDTO = customerAccountDetailDTO;
    }
}
