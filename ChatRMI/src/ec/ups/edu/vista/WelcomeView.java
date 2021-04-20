package ec.ups.edu.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ec.edu.ups.chat.chatInterface;

public class WelcomeView  extends JFrame implements KeyListener {
	
	 String username;
	    chatInterface chat;
	    List<String> users = new ArrayList<>();
	private JPanel jPanel1;
	private JLabel jLabel1,jLabel2,jLabel3;
	private JTextField userNameInputText;
	private JButton loginButton;
	
	public WelcomeView(chatInterface chat) {  
		  initComponents();               // initializing JFrame's components
	        this.chat = chat;
	        userNameInputText.addKeyListener(this);
	        setVisible(true);
	        setLocationRelativeTo(null);    // to visible the GUI in the middle of the screen
		
	 }
	 private void initComponents() {
		    jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        userNameInputText = new JTextField();
	        jLabel3 = new JLabel();
	        loginButton = new JButton();
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      //  getContentPane().setLayout(new );
	        jPanel1.setBackground(new java.awt.Color(194, 181, 252));
	     //   jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
	        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
	        jLabel2.setForeground(new java.awt.Color(55, 178, 204));
	        jLabel2.setText("Enter Your Name");
	        jPanel1.add(jLabel2);
	        userNameInputText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(55, 178, 204)));
	        jPanel1.add(userNameInputText);
	 
	        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
	        jLabel3.setForeground(new java.awt.Color(55, 178, 204));
	        jLabel3.setText("Public Chat");
	        jPanel1.add(jLabel3);
	        
	        loginButton.setFont(new Font("Tahoma", 1, 12)); // NOI18N
	        loginButton.setForeground(new Color(55, 178, 204));
	        loginButton.setText("Login");
	        loginButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(55, 178, 204)));
	        loginButton.addActionListener(new java.awt.event.ActionListener() {
	           
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	                loginButtonActionPerformed(evt);
	            }
	        });
	        jPanel1.add(loginButton);

	        getContentPane().add(jPanel1);

	        pack();
	 }
	 private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
	        userLogin();
	    }
	 
	 
	 private void userLogin() {
	        username = userNameInputText.getText();
	        try {
	            users = chat.getAllUsers();
	        } catch (RemoteException ex) {
	            Logger.getLogger(WelcomeView.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        if (users.contains(username)) {
	            JOptionPane.showMessageDialog(null, "This user is already exists");
	            System.exit(0);
	        } else {
	            try {
	                new ChatView(chat, username);   // pass the reference "chat" and the logged user's name
	            } catch (RemoteException ex) {      // and create new ChatView
	                Logger.getLogger(WelcomeView.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            this.setVisible(false);
	        }
	    }
	 
	@Override
	public void keyPressed(KeyEvent e) {
		  if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // key event to the text field
	            userLogin();
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
