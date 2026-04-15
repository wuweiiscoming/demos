package org.wigo.demos.others.rmi;

import java.rmi.Remote;

public interface MyRemote extends Remote {

    public String sayHello();
}
