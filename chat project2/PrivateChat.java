import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class PrivateChat implements ActionListener
{	
	JFrame frame;
	JTextField textfield;
	JTextArea textarea;
	JScrollPane scrollpane;
	String client_name;
	public 	PrivateChat()
	{
			frame=new JFrame();
			frame.setSize(200,200);
			textfield =new JTextField();
			textfield.addActionListener(this);
			frame.add(textfield,"South");
			textarea=new JTextArea();
			textarea.setBackground(Color.magenta);
			textarea.setEnabled(false);
			scrollpane =new JScrollPane(textarea);
			frame.add(scrollpane);
			client_name=(String)Client.list.getSelectedValue();
			frame.setTitle(client_name);
			frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{		
		 	String str2=e.getActionCommand();
			System.out.println("enter");
			System.out.println(str2);
			String str1=textfield.getText();	
		//	textarea.setText(textarea.getText()+"\n"+str1);
			System.out.println("sender="+Client.sender);
			System.out.println("receiverer="+Client.receiver);
			Client.out.println("private"+Client.sender+"@"+Client.receiver+"@"+textfield.getText());
		//	System.out.println("private"+Client.sender+"@"+Client.receiver+"@"+PrivateChat.textfield.getText());
		//	System.out.println("PrivateChat.textfield.getText()="+PrivateChat.textfield.getText());
			textfield.setText("");
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	