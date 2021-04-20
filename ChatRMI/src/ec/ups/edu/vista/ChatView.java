package ec.ups.edu.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	        
	        sendButton.setFont(new Font("Tahoma", 1, 12)); // NOI18N
	        sendButton.setForeground(new Color(55, 178, 204));
	        sendButton.setText("Send");
	        sendButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(55, 178, 204)));
	        sendButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                sendButtonActionPerformed(evt);
	            }
	        });
	        jPanel1.add(sendButton);
	        jPanel1.add(chatInputText);
	        
	        chatListTextArea.setEditable(false);
	        chatListTextArea.setContentType("text/html"); // NOI18N
	        chatListTextArea.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <p style=\"margin-top: 0\">\n      <h1 color='red'>Viji</h1>\n    </p>\n  </body>\n</html>\n");
	        jScrollPane3.setViewportView(chatListTextArea);
	        
	        jPanel1.add(jScrollPane3);
	        jPanel2.add(jPanel1);
	 }
	 
	 private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
	        sendNewMessage();
	    }//GEN-LAST:event_sendButtonActionPerformed

	    private void execute() {
	        userListTextArea.setLineWrap(true);     // to remove horizontal scrolling bar 
	        setTitle("Public Chat");     
	        this.nameLabel.setText(username);
	        this.setVisible(true);
	        setLocationRelativeTo(null);            // to visible the GUI in the middle of the screen
	        Thread t1 = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                while (true) {
	                    refresh();                  // this thread is used to refresh the chat window by every second 
	                    try {                       // to display whole chat list
	                        Thread.sleep(1000);
	                    } catch (InterruptedException ex) {
	                        Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
	                    }
	                }
	            }
	        });
	        t1.start();
	    }
	    
	    public void refresh() {
	        try {
	            displayChatList();
	            displayUserList();
	        } catch (RemoteException ex) {
	            Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    private void displayUserList() throws RemoteException {
	        userListTextArea.setText("");
	        users = chat.getAllUsers();
	        for (String u : users) {
	            if (!u.equals(username)) {             
	                userListTextArea.append(" " + u + "\n");             
	            }
	        }
	    }
	    
	    public void displayChatList() throws RemoteException {
	        chatListTextArea.setText("");
	        msgs = chat.getAllMessages();
	        String doc = "<html><body><table>";
	        for (Message m : msgs) {
	            if (!((m.getUsername().equals(username)) && (m.getType().equals("join")))) {             
	                String smileyName = m.getSmiley();               
	                Date date = m.getDate();
	                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	                String a = dateFormat.format(date);
	                SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	                SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
	                try {
	                    Date time = parseFormat.parse(a);
	                    if (smileyName == null) {
	                        doc += "<tr><td>" 
	                                + printFormat.format(time) 
	                                + "</td><td><font color='rgb(55,178,204)'><b>" 
	                                + m.getUsername() 
	                                + "</b></font></td><td> " 
	                                + m.getMsg() 
	                                + "</td></tr>";
	                    } else {                  
	                        doc += "<tr><td>" 
	                                + printFormat.format(time) 
	                                + "</td><td><font color='rgb(55,178,204)'><b>" 
	                                + m.getUsername()  
	                                + "</b></font></td><td><img src= '" 
	                                + this.getClass().getResource("/images/"+smileyName) 
	                                + "' width=50 height=50 /> </td></tr>";
	                    }
	                } catch (ParseException ex) {
	                    Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
	                }                        
	            }
	        }
	        doc += "</table></body></html>";
	        chatListTextArea.setText(doc);
	    }

	    
	    private void sendNewMessage() {
	        String inputMessage = chatInputText.getText();
	        chatInputText.setText("");
	        Message message = new Message();
	        message.setUsername(username);
	        message.setMsg(inputMessage);
	        message.setType("client");
	        message.setDate(new Date());
	        try {
	            chat.sendMessage(message);
	        } catch (RemoteException ex) {
	            Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
	        }
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
