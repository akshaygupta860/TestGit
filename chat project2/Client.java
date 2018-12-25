import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
class Client extends WindowAdapter implements ActionListener,MouseListener
{	
	static String user_name,private_message1;
	static JFrame frame;
	static JList list;
	static DefaultListModel defaultListModel;
	static JTextArea textarea;
	static JTextField textfield;
	JScrollPane scrollpane;
	static Socket socket;
	static String str1,client_name,sender,receiver;
	static PrintWriter out;
	static String arr[]=new String[3];
	static boolean bool=true,bool_name=true,bool1=true,boolp=true;
	JPanel panel;
	JButton button;
	static char ch;
	static Vector vector_Private_frame;
	static Vector vector_Private_username;
	static Vector vector_Private_textarea;
	public Client()
	{	
	//	System.out.println("CLIENT");
		frame=new JFrame();
		frame.setSize(500,500);
		frame.addWindowListener(this);
		user_name=JOptionPane.showInputDialog(frame,"Enter name","please",JOptionPane.INFORMATION_MESSAGE);
		bool_name=false;
		System.out.println("username"+user_name);
		defaultListModel=new DefaultListModel();
		list=new JList(defaultListModel);
		list.addMouseListener(this);
		panel=new JPanel();
		button= new JButton("Photos");//The default Layout of frame is BorderLayout.
		textfield=new JTextField(20);
		textfield.addActionListener(this);
		textarea=new JTextArea();
		scrollpane=new  JScrollPane(textarea);
		if(user_name.trim()!="" )
		{
		
			frame.setVisible(true);
		}
		else
		{
			System.exit(1);
		}
		panel.add(textfield);
		panel.add(button);
		frame.add(panel,"South");
		frame.add(list,"East");
		frame.add(scrollpane);
		frame.setTitle(user_name);
		textarea.setBackground(Color.magenta);
		textarea.setEnabled(false);
		try
		{
			InetAddress address=InetAddress.getByName("localhost");
			socket=new Socket(address,65535);
			out=new PrintWriter(socket.getOutputStream(),true);
			out.println("*"+user_name);
			System.out.println("sent");
		}
		catch(Exception e)
		{
			
		}
		vector_Private_frame=new Vector();
		vector_Private_username=new Vector();
		vector_Private_textarea=new Vector();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{	
	        String str2=e.getActionCommand();
			str1=textfield.getText();	
			out.println(str1);
			textfield.setText("");
	}
	public void mouseClicked(MouseEvent mous_event)
	{	
		sender=frame.getTitle();
		receiver=(String)list.getSelectedValue();
	//	PrivateChat privatechat=new PrivateChat();
		System.out.println("RECEIVERrrrrr\n"+receiver);;
		if(!vector_Private_username.contains(receiver))
		{	
			PrivateChat privatechat=new PrivateChat();
			vector_Private_frame.add(privatechat.frame);
			vector_Private_username.add(receiver);
			vector_Private_textarea.add(privatechat.textarea);
		}
		else
		{
			JFrame frame=(JFrame)vector_Private_frame.get(vector_Private_username.indexOf(receiver));
			JTextArea textarea=(JTextArea)vector_Private_textarea.get(vector_Private_username.indexOf(receiver));
			System.out.println("receiver="+receiver);
			frame.setVisible(true);
		//	textarea.setText(textarea.getText()+"\n"+private_message1);
		}
	}
	public void mouseEntered(MouseEvent mouse_event1)
	{
		
	}
	public void mouseExited(MouseEvent mouse_event1)
	{
		
	}
	public void mousePressed(MouseEvent mouse_event1)
	{
		
	}
	public void mouseReleased(MouseEvent mouse_event1)
	{
		
	}
	public void windowClosing(WindowEvent y)
	{	
		System.out.println("WINDOW CLOSING");
		String temp=frame.getTitle();
		temp="$"+temp;
		System.out.println("temp="+temp);
		out.println(temp);//$ stand for log out means those clients wants to logout
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[])
	{	
		Client main=new Client();
		try
		{
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int count=1;		
			while(true)
			{
				String str1=in.readLine();
				if(!str1.equals(""))
				ch=str1.charAt(0);
				System.out.println("str1================================================="+str1);			
				System.out.println("ch=================================================="+ch);
				bool1=true;
				String temp3;
				
				if(ch == '$')
				{
					temp3=str1.substring(1,str1.length());
					System.out.println("Log out user="+temp3);
					if(defaultListModel.contains(temp3))
					{	
						defaultListModel.removeElement(temp3);
						System.out.println(defaultListModel.removeElement(temp3));
					}
					//	defaultListModel.clear();
				}
				else if(ch == '*')
				{	     
					str1=str1.substring(1,str1.length());
					if(!user_name.equals(str1))
					{
						boolean bool=defaultListModel.contains(str1);
						if(bool==false)
						defaultListModel.addElement(str1);
					}
					bool1=false;
				}
				else if(ch == 'p')
				{	
				 	System.out.println("Enter in the private block");
					System.out.println("str1"+str1);
					arr=str1.split("@");
					receiver=arr[1];
//					sender=arr[0];
					String temp=arr[0];
					sender=temp.substring(7,temp.length());
					System.out.println("sender"+sender);
				//	private_message1=arr[2];
					private_message1=str1.substring(str1.lastIndexOf("@")+1,str1.length());
					System.out.println("receiver"+receiver);
					for(int i=0;i<vector_Private_username.size();i++)
					{
						if(receiver.equals(vector_Private_username.get(i)))
						{	
							System.out.println("user name vector contain ");
							System.out.println("\n"+vector_Private_username.get(i));
							boolp=false;
						}
					}
					if(boolp==true)
					{	
						System.out.println("Enter in the\n");
						System.out.println("Sender="+sender);
						PrivateChat privatechat=new PrivateChat();
					 	privatechat.frame.setTitle(sender);////////////////////////////////////
				 	 	//	private_message1=arr[2];
					 	System.out.println("private_message1="+private_message1);
					 	privatechat.textarea.setText(private_message1+"\n");
					 	vector_Private_frame.add(privatechat.frame);
					 	vector_Private_textarea.add(privatechat.textarea);
						vector_Private_username.add(receiver);
					}
					if(boolp==false)
					{	
						JFrame frame=(JFrame)vector_Private_frame.get(vector_Private_username.indexOf(receiver));
						JTextArea textarea=(JTextArea)vector_Private_textarea.get(vector_Private_username.indexOf(receiver));
						System.out.println("receiver="+receiver);
						frame.setVisible(true);
						textarea.setText(textarea.getText()+"\n"+private_message1);
						//frame.setVisible(true);
						boolp=true;
					}
					str1="";
			 	}
				else if(!str1.equalsIgnoreCase("END"))
				{
					if(bool1==true)
					textarea.setText(textarea.getText()+"\n"+str1);
				}
				else
				break;
			}
		}
		catch(Exception t){t.printStackTrace();}
	}
}