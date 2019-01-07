package lk.ijse.student.bankManagementSystem.entity;

import lk.ijse.student.bankManagementSystem.resources.HibUtil;
import org.hibernate.Session;

public class abc {
    public static void main(String[] args){
        try(Session session=HibUtil.getSessionFactory().openSession()){}
    }
}
