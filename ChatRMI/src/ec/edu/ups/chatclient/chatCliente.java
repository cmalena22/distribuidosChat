package ec.edu.ups.chatclient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ec.edu.ups.chat.chatInterface;
import ec.ups.edu.vista.WelcomeView;

public class chatCliente {

	public static void main(String[] args) {
		
		try {        
            Registry registry = LocateRegistry.getRegistry("localhost", 9999);
           chatInterface chat = (chatInterface)registry.lookup("chatServer");  //getting a remote reference       
            new WelcomeView(chat);  //pass the reference as a parameter 
        } catch (Exception e) {
           e.printStackTrace();
        }
	}

}
