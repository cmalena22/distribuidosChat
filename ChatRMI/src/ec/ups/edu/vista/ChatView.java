package ec.ups.edu.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.xml.ws.handler.MessageContext.Scope;

import ec.edu.ups.chat.Message;
import ec.edu.ups.chat.chatInterface;

public class ChatView  extends JFrame implements MouseListener, KeyListener  {

	String username;
    chatInterface chat;
    List<Message> msgs = new ArrayList<>();
    List<String> users = new ArrayList<>();

    //
    private JButton  sendButton,logoutButton ;
    private JTextField chatInputText;
    private JTextPane chatListTextArea;
    private JLabel angLabel,glsLabel,hpyLabel, jLabel1,nameLabel, jLabel2;
	private JPanel jPanel1, jPanel2,jPanel3, jPanel5;
	private JScrollPane jScrollPane3,jScrollPane2;
	private JTextArea userListTextArea ;
	 public ChatView(chatInterface chat, String username) throws RemoteException {
		  initComponents();
	        this.chat = chat;
	        this.username = username;
	        chat.login(username);
	        execute();
	 }
	 private void initComponents() {
		 
		 
	       jPanel2 = new JPanel();
	        jPanel1 = new JPanel();
	        nameLabel = new  JLabel();
	        sendButton = new  JButton();
	        logoutButton = new JButton();
	        chatInputText = new JTextField();
	        jScrollPane3 = new JScrollPane();
	        chatListTextArea = new JTextPane();
	        jPanel3 = new  JPanel();
	        jScrollPane2 = new  JScrollPane();
	        userListTextArea = new JTextArea();
	        jLabel2 = new  JLabel();
	        jPanel5 = new  JPanel();
	        
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        setBackground(new Color(219, 109, 252));
	        setResizable(false);
	        
	        jPanel2.setBackground(new Color(194, 181, 252));
	        jPanel1.setBackground(new Color(194, 181, 252));
	        jPanel1.setBorder(BorderFactory.createLineBorder(new Color(80, 87, 169)));
	        
	        
	        nameLabel.setFont(new Font("DejaVu Sans", 1, 24)); // NOI18N
	        nameLabel.setForeground(new Color(55, 178, 204));
	        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        nameLabel.setText("name");
	        jPanel1.add(nameLabel);

	 }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
