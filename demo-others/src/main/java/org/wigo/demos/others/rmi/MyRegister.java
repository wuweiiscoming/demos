package org.wigo.demos.others.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class MyRegister {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        MyRemote service = new MyRemoteImpl();
        Naming.rebind("RemoteHello", service);
    }
}
