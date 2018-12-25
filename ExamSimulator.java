import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
class ExamSimulator implements ActionListener
{	
	JFrame f,f1;
	String url;
	Connection con;
	Statement stmt;
	ResultSet rs;
	JTextArea ta;
	JProgressBar pbar;
	JPanel p,p1,p2,p5,p6,p7;
	JButton b1,b2,b3,b4,b5;
	JRadioButton rb1,rb2,rb3,rb4;
	JCheckBox cb1,cb2,cb3,cb4;
	JLabel jl,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11;
	GridBagConstraints gbc;
	int question=1, arr[],count=0;
	CardLayout cl;
	HashMap hashmap;
	ButtonGroup bg;
	boolean flag,flag1,flag2=true,review=true;
	int count4=0;
	public ExamSimulator() throws SQLException
	{	
	//	arr=new int[10];
		arr=new int[]{1,2,3,4,5,6,7,8,9,10};
	/*	for(int i=0;i<arr.length;i++)
		{
			arr[i]=(int)(Math.random()*10);
			while(true)
			{
				if(arr[i]==0)
				arr[i]=(int)(Math.random()*10);
				else
				break;
			}
		System.out.println("BeBack"+arr[i]);
		}
		for(int i=0;i<arr.length;i++)
		{
			for(int j=1;j<arr.length;j++)
			{	
				
					if(arr[i]==arr[j])
					{
						arr[j]=(int)(Math.random()*10);	
					}
			}
			System.out.println("After"+arr[i]);
		}*/
		f=new JFrame();
		f1=new JFrame();
		f1.setSize(250,300);
		f.setSize(400,500);
		p5=new JPanel();
		p5.setLayout(cl=new CardLayout());
		p=new JPanel();
		p2=new JPanel();                                                                     
		p.setLayout(new GridBagLayout());
		p2.setLayout(new GridBagLayout());
		gbc=new GridBagConstraints();
		p1=new JPanel();
		f.add(p1,"South");
		b1=new JButton("Previous");
		b1.setEnabled(false);
		b1.addActionListener(this);
		p1.add(b1);
		b2=new JButton("Next");
		b2.addActionListener(this);
		p1.add(b2);
		b3=new JButton("Submit");
		b3.addActionListener(this);
		p1.add(b3);
		bg=new ButtonGroup();
		hashmap=new HashMap();
		jl=new JLabel();
		jl1=new JLabel();
		jl2=new JLabel();
		jl3=new JLabel();
		jl4=new JLabel();
		jl5=new JLabel();
		jl6=new JLabel();
		jl7=new JLabel();
		jl8=new JLabel();
		jl9=new JLabel();
		jl10=new JLabel();
		jl11=new JLabel();
		//DATABASE CODE//DATABASE CODE//DATABASE CODE//DATABASE CODE//DATABASE CODE//DATABASE CODE
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		url="jdbc:mysql://localhost:3306/exam?user=root&password=root";
		con=DriverManager.getConnection(url);
		stmt=con.createStatement();
		rs=stmt.executeQuery("select * from simulator");
		p5.add(p,"Not Multiple");
		p5.add(p2,"Multiple");
		f.add(p5,"Center");
	//	System.out.println("count"+count);
		rs.absolute(arr[0]);
		System.out.println(rs.getString("Q.No")+"\t"+rs.getString("Question")+"\t"+rs.getString("A")+"\t"+rs.getString("B")+"\t"+rs.getString("C")+"\t"+rs.getString("D")+"\t"+rs.getString("Answer")+"\t"+rs.getString("Multiple"));
		notMultiple();
		multiple();
		
		f.setVisible(true);
		
	}                                                                                                                                                 
	public static void main(String args[])throws Exception
	{
		ExamSimulator es=new ExamSimulator();
	}
	public void actionPerformed(ActionEvent e) 
	{                                                    
		try
		{
			String str=e.getActionCommand();
			if(str.equals("Next"))
			{	
				next();
			}	
			if(str.equals("Previous"))
			{	
				previous();

			}
			if(str.equals("Submit"))
			{	
				putHashMap();
				
				b4=new JButton("Review");
				b4.addActionListener(this);
				b5=new JButton("Exit");
				b5.addActionListener(this);
				p6=new JPanel();
				p6.add(b4);		p6.add(b5);
				p7=new JPanel();
				pbar=new JProgressBar(JProgressBar.HORIZONTAL);
				pbar.setStringPainted(true);
				p7.add(pbar);
				f1.add(p7);
				int count2=1;
			//	int count4=0;
				for(int i=0;i<arr.length;i++)
				{
					String st=(String)hashmap.get(count2);
					rs.absolute(i+1);
					System.out.println("st="+st);
					if(st!=null)
					{
						System.out.println("Answer="+rs.getString("Answer"));
						if(st.equals(rs.getString("Answer")))
						{	
							
							count4=count4+10;
							System.out.println("count4="+count4);
							pbar.setValue(count4);
						}
					
					}
					count2++;
				}
			
			
				f1.add(p6,"South");
				f1.setVisible(true);
				b3.setEnabled(false);
			}
			if(str.equals("Exit"))
			System.exit(1);
			
			if(str.equals("Review"))
			{	
				 review=false;	
				 f1.setVisible(false);
				 rs.absolute(arr[0]);
				 question=1;
				 next();
				 previous();
				 
				
				
			}
		}
		catch(Exception r)
		{
				r.printStackTrace();
		}
		
	}	
	public void notMultiple()
	{	
			try
			{
				//	System.out.println("Not Multiple");
					jl1.setText("Question "+question+".");
					gbc.gridx=0; 	gbc.gridy=0;
					p.add(jl1,gbc);
					jl.setText(rs.getString("Question"));
					gbc.gridx=1; 	gbc.gridy=0;
					p.add(jl,gbc);
				//	System.out.println("First");
					JPanel panel1=new JPanel();
					jl8.setText("A");
					panel1.add(jl8);
					Insets in=new Insets(10,10,10,10);
					gbc.insets=in;
					rb1=new JRadioButton(rs.getString("A"));
					gbc.gridx=1; 	gbc.gridy=1;
					bg.add(rb1);
					panel1.add(rb1);
					p.add(panel1,gbc);
					JPanel panel2=new JPanel();
					jl9.setText("B");
					panel2.add(jl9);
						gbc.insets=in;
					rb2=new JRadioButton(rs.getString("B"));
					gbc.gridx=1; 	gbc.gridy=2;
					bg.add(rb2);
					panel2.add(rb2);
					p.add(panel2,gbc);
					JPanel panel3=new JPanel();
					jl10.setText("C");
					panel3.add(jl10);
						gbc.insets=in;
					rb3=new JRadioButton(rs.getString("C"));
					gbc.gridx=1; 	gbc.gridy=3;
					bg.add(rb3);
					panel3.add(rb3);
					p.add(panel3,gbc);
					JPanel panel4=new JPanel();
					jl11.setText("D");
					panel4.add(jl11);
					gbc.insets=in;
					rb4=new JRadioButton(rs.getString("D"));
					gbc.gridx=1; 	gbc.gridy=4;
					bg.add(rb4);
					panel4.add(rb4);
					p.add(panel4,gbc);
					if(rs.getString("Multiple").equals("No"))
					cl.show(p5,"Not Multiple");
			}
			catch(Exception y){}
		}
		public void multiple()
		{	
			try
			{
			//	System.out.println("Multiple");
				flag=true;
				jl2.setText("Question "+question+".");
				gbc.gridx=0; 	gbc.gridy=0;
				p2.add(jl2,gbc);
				jl3.setText(rs.getString("Question"));
				gbc.gridx=1; 	gbc.gridy=0;
				p2.add(jl3,gbc);
			//	System.out.println("Second");
				JPanel panel=new JPanel();
				jl4.setText("A");
				panel.add(jl4);
				cb1=new JCheckBox(rs.getString("A"));
				gbc.gridx=1; 	gbc.gridy=1;
				panel.add(cb1);
				p2.add(panel,gbc);
				
				JPanel panel1=new JPanel();
				jl5.setText("B");
				gbc.gridx=0; 	gbc.gridy=2;
				panel1.add(jl5);
				cb2=new  JCheckBox(rs.getString("B"));
				gbc.gridx=1; 	gbc.gridy=2;
				panel1.add(cb2);
				p2.add(panel1,gbc);
			
				JPanel panel2=new JPanel();
				jl6.setText("C");
				panel2.add(jl6);
				cb3=new  JCheckBox(rs.getString("C"));
				gbc.gridx=1; 	gbc.gridy=3;
				panel2.add(cb3);
				p2.add(panel2,gbc);
			
				JPanel panel3=new JPanel();
				jl7.setText("D");
				gbc.gridx=0; 	gbc.gridy=4;
				panel3.add(jl7);
				cb4=new  JCheckBox(rs.getString("D"));
				gbc.gridx=1; 	gbc.gridy=5;
				panel3.add(cb4);
				p2.add(panel3,gbc);
				if(rs.getString("Multiple").equals("Yes"))
				cl.show(p5,"Multiple");
			}
			catch(Exception l){}
		}	
		public void putHashMap()throws Exception
		{
					if(rs.getString("Multiple").equals("No"))
					{	
					
						if(rb1.isSelected())
						{
							hashmap.put(question,"A");
						//	System.out.println("RB1");
						}
						if(rb2.isSelected())
						{
							hashmap.put(question,"B");
					//	System.out.println("RB2");
						}
						if(rb3.isSelected())
						hashmap.put(question,"C");
						if(rb4.isSelected())
						hashmap.put(question,"D");
					}
					else
					{	
						String s1="";	
						if(cb1.isSelected())
						{	
							s1="A";
							hashmap.put(question,s1);
						}
						if(cb2.isSelected())
						{	
							s1=s1+"B";
							hashmap.put(question,s1);
						}
						if(cb3.isSelected())
						{	
							s1=s1+"C";
							hashmap.put(question,s1);
						}
						if(cb4.isSelected())
						{	
							s1=s1+"D";
							hashmap.put(question,s1);
						}
					System.out.println("s1="+s1);
					}
		}	
		public void setHashMap()throws Exception
		{
					if(rs.getString("Multiple").equals("No"))
					{	
					//	System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+hashmap.get(question));
					
						 String s=(String)hashmap.get(question);
						 
					//	 System.out.println("question="+question);
						 if(s!=null)
						 {	
						 	System.out.println("s="+s);
					//	 	System.out.println("777777777777777777777777");
						 	 if(s.equals("A"))
							 rb1.setSelected(true);
							 if(s.equals("B"))
							 rb2.setSelected(true);
							 if(s.equals("C"))
							 rb3.setSelected(true);
							 if(s.equals("D"))
							 rb4.setSelected(true);
						 }
					
					}
					else
					{	
					//	System.out.println("Enteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeer");
						String str1=(String)hashmap.get(question);
						char arr1[];
							System.out.println("str1="+str1);
						if(str1!=null)
						{
						
							arr1=str1.toCharArray();
							for(int i=0;i<arr1.length;i++)
							{
								String s2=arr1[i]+"";
								 if(s2.equals("A"))
								 cb1.setSelected(true);
								 if(s2.equals("B"))
								 cb2.setSelected(true);
								 if(s2.equals("C"))
								 cb3.setSelected(true);
								 if(s2.equals("D"))
								 cb4.setSelected(true);
							}
						}
					}
		}
		public void next()throws Exception
		{
						putHashMap();
						count++;
					/*	if(arr.length>=count && count>0 )
							{
							
								System.out.println("count="+count);
								rs.absolute(arr[count]);
							}*/
						b1.setEnabled(true);
						if(rs.next())
						{	
							
							System.out.println(rs.getString("Q.No")+"\t"+rs.getString("Question")+"\t"+rs.getString("A")+"\t"+rs.getString("B")+"\t"+rs.getString("C")+"\t"+rs.getString("D")+"\t"+rs.getString("Answer")+"\t"+rs.getString("Multiple"));
							question++;
						
							bg.clearSelection();
							cb1.setSelected(false);
							cb2.setSelected(false);
							cb3.setSelected(false);
							cb4.setSelected(false);
							setHashMap();
					
							if(rs.getString("Multiple").equals("No"))
							{		
							
								jl.setText(rs.getString("Question"));
								jl1.setText("Question"+question+".");
								rb1.setText(rs.getString("A"));
								rb2.setText(rs.getString("B"));
								rb3.setText(rs.getString("C"));
								rb4.setText(rs.getString("D"));
								cl.show(p5,"Not Multiple");
							}
							else
							{	
							
								jl2.setText("Question "+question+".");
								jl3.setText(rs.getString("Question"));
								cb1.setText(rs.getString("A"));
								cb2.setText(rs.getString("B"));
								cb3.setText(rs.getString("C"));
								cb4.setText(rs.getString("D"));
								cl.show(p5,"Multiple");
								
							}
							highlight();

							
						}
						if(question>=arr.length)
						{
								b2.setEnabled(false);
							//	System.out.println("55555555555555555");
								
						}
		}
		public void previous()throws Exception
		{
							//	System.out.println("question"+question);
			//		System.out.println(rs.isFirst());
					b2.setEnabled(true);
				/*	if(arr.length>=count && count>0 )
					{
						count--;
						System.out.println("count="+count);
							rs.absolute(arr[count]);
						}*/
						
					putHashMap();
					bg.clearSelection();
					cb1.setSelected(false);
					cb2.setSelected(false);
					cb3.setSelected(false);
					cb4.setSelected(false);	
					if(rs.previous())
					{		
						
							question--;
							setHashMap();
							if(rs.getString("Multiple").equals("No"))
							{	
								jl.setText(rs.getString("Question"));
								jl1.setText("Question"+question+".");
								rb1.setLabel(rs.getString("A"));
								rb2.setLabel(rs.getString("B"));
								rb3.setLabel(rs.getString("C"));
								rb4.setLabel(rs.getString("D"));
								cl.show(p5,"Not Multiple");
							}
							else
							{	
								jl2.setText("Question"+question+".");
								jl3.setText(rs.getString("Question"));
								cb1.setLabel(rs.getString("A"));
								cb2.setLabel(rs.getString("B"));
								cb3.setLabel(rs.getString("C"));
								cb4.setLabel(rs.getString("D"));
								cl.show(p5,"Multiple");
							}
							highlight();			
					}
					if(question<=1)
					{
						b1.setEnabled(false);
					
					
					}
		//		}
			/*	if(arr.length>=count && count>0 )
				{
					count--;
					System.out.println("count="+count);
					rs.absolute(arr[count]);
				}*/
		}
		public void highlight()throws Exception
		{
				if(review==false)
				{
						String s5=(String)rs.getString("Answer");
					
					/*	rb1.setBackground(Color.black);
					 	rb2.setBackground(Color.black);
					 	rb3.setBackground(Color.black);
					 	rb4.setBackground(Color.black);*/
					 		rb1.setBackground(p.getBackground());
					 	rb2.setBackground(p.getBackground());
					 	rb3.setBackground(p.getBackground());
					 	rb4.setBackground(p.getBackground());
						if(rs.getString("Multiple").equals("No"))
						{
					
							if(s5.equals("A"))
							{
									rb1.setBackground(Color.green);
									System.out.println("RB1");
							}	
					 				
							 if(s5.equals("B"))
							 {
							 		rb2.setBackground(Color.green);
							 		System.out.println("RB2");
							 }
							 if(s5.equals("C"))
							 {
							 		rb3.setBackground(Color.green);
							 		System.out.println("RB3");
							 }
							 if(s5.equals("D"))
							 {
							 		rb4.setBackground(Color.green);
							 		System.out.println("RB4");
							 }
							 		
						}
						else
						{
						//	char arr3[]=s5.toCharArray();
							int i=0;
							rb1.setBackground(Color.black);
						 	rb2.setBackground(Color.black);
						 	rb3.setBackground(Color.black);
						 	rb4.setBackground(Color.black);
						 	
						//	while(arr3.length!=i)
							while(s5.length()!=i)
							{
								String s8=s5.charAt(i)+"";
								if(s8.equals("A"))
								cb1.setBackground(Color.green);
								if(s8.equals("B"))
								cb2.setBackground(Color.green);
								if(s8.equals("C"))
								cb3.setBackground(Color.green);
								if(s8.equals("D"))
								cb4.setBackground(Color.green);
									
								i++;
							}
						}
				cb1.setEnabled(false);
					cb2.setEnabled(false);
					cb3.setEnabled(false);
					cb4.setEnabled(false);
					rb1.setEnabled(false);
					rb2.setEnabled(false);
					rb3.setEnabled(false);
					rb4.setEnabled(false);
			
				}
				
							
		}
					
		
}