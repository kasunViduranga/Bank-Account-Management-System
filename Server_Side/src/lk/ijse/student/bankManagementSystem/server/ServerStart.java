package lk.ijse.student.bankManagementSystem.server;

import lk.ijse.student.bankManagementSystem.service.impl.ServiceFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
    public static void main(String[] args){
        try {
            Registry registry = LocateRegistry.createRegistry(6050);
            registry.rebind("bank",ServiceFactoryImpl.getInstance());
            System.out.println("Server Started");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
