import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import java.awt.datatransfer.Clipboard;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
 
class Notepad3 extends WindowAdapter implements ActionListener,KeyListener
{	
	JFrame f;
	JFrame f1,f2,f3,f4;
	JSplitPane sp,sp1;
	String s1,newstring=null,openstring=null,com,whole,s4,s5,s6,s7,s8,s9,s10,run,filepath,str2;
	JTabbedPane jtp;
	JMenuBar mb;
	JPanel p3,p2,p;
	JMenu m1,m2,m3,m4,m5;
	JTextArea ta,ta1,ta3,ta4;
	JFileChooser fc;
    FileInputStream fis;
    BufferedInputStream bis;
    FileOutputStream fos;
    Iterator iterator;
    BufferedOutputStream bos;
    Vector v,v1,v2;
    Clipboard cb;
    JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9,mi10,mi11,mi12,mi13,mi14,mi15,mi16;
	JScrollPane scrollpane,scrollpane1;
	BufferedReader br;
	boolean key=true,text=true,bool=false,check=true,boole=true;;
	JOptionPane jop;
	JTextField tf,tf2,tf3,tf4,tf5;;
	Runtime r;
	Process p1=null;
	File file,file1;
	Component c;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
	int y,x,i,count=0,count1=0,count3=0;
	JOptionPane  op;
	Object o;
	Pattern pa;
	Matcher ma;
	Notepad3()
	{	
		f=new JFrame();
		f.setSize(1000,1200);		
		f.setTitle("Akshay Creator");
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(this);
		f.setLayout(new BorderLayout());
		mb=new JMenuBar();
		f.setJMenuBar(mb);
		m1=new JMenu("File");
		m2=new JMenu("Edit");
		m3=new JMenu("Build");
		m4=new JMenu("Tab");
		m5=new JMenu("System");
		mb.add(m1);mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);
		mi1=new JMenuItem("New");
		mi1.addActionListener(this);
		mi2=new JMenuItem("Open");	
		mi2.addActionListener(this);
		mi3=new JMenuItem("Save");
		mi3.setEnabled(false);
		mi3.addActionListener(this);
		mi4=new JMenuItem("Save As");
		mi4.setEnabled(false);
		mi4.addActionListener(this);
		mi5=new JMenuItem("Exit");
		mi5.addActionListener(this);
		m1.add(mi1);  m1.add(mi2);       m1.add(mi3);     m1.add(mi4);  m1.addSeparator();  m1.add(mi5);
		mi6=new JMenuItem("Cut");
		mi6.setEnabled(false);
		mi6.addActionListener(this);
		mi7=new JMenuItem("Copy");
		mi7.setEnabled(false);
		mi7.addActionListener(this);
		mi8=new JMenuItem("Paste");
		mi8.setEnabled(false);
		mi8.addActionListener(this);
		mi9=new JMenuItem("Find");
		mi9.setEnabled(false);
		mi9.addActionListener(this);
		mi10=new JMenuItem("Find & Replace");	
		mi10.setEnabled(false);
		mi10.addActionListener(this);
		m2.add(mi6);m2.add(mi7);m2.add(mi8);     m2.addSeparator(); m2.add(mi9);               m2.add(mi10);		
		mi11=new JMenuItem("Compile");
		mi11.setEnabled(false);
		mi11.addActionListener(this);
		mi12=new JMenuItem("Run");
		mi12.setEnabled(false);
		mi12.addActionListener(this);
		m3.add(mi11); m3.add(mi12);
		mi13=new JMenuItem("Close Current Tab");
		mi13.setEnabled(false);
		m4.add(mi13);  
		mi13.addActionListener(this);
		mi14=new JMenuItem("Close All Tab");
		mi14.setEnabled(false);
		m4.add(mi14);
		mi14.addActionListener(this);
		mi15=new JMenuItem("Shutdown");
		m5.add(mi15);            
		mi15.addActionListener(this);
		mi16=new JMenuItem("Restart");
		m5.add(mi16);
		mi16.addActionListener(this);
		jtp=new JTabbedPane();
		scrollpane1=new JScrollPane(ta1=new JTextArea());
		ta1.setBackground(Color.black);
		v=new Vector();
		v1=new Vector();
		v2=new Vector();
		sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtp,scrollpane1);
		
		f.add(sp);
		f.setVisible(true);
		}
	public void actionPerformed(ActionEvent e)
	{	
		try
		{
		String str=e.getActionCommand();
		System.out.println(str);
		
		if(str.equals("New"))
		{	
		//	if(boole==true)	
		//	{
				f3=new JFrame();
				f3.setSize(350,200);
				f3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f3.addWindowListener(this);
				System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaFrame.setDefaultLookAndFeelDecorated(true)");
				f3.setLayout(new BorderLayout());
				f3.setBounds(250,150,330,150);
				//		f.add(f3);
				p3=new JPanel();
				f3.add(p3);	
				l3=new JLabel("Enter Name:");
				p3.add(l3);
				tf4=new JTextField(20);
				p3.add(tf4);
				l4=new JLabel("Location:     ");
				p3.add(l4);
				tf5=new JTextField(20);
				p3.add(tf5);
				tf5.setText("");
				tf5.setEnabled(false);
				p2=new JPanel();
				f3.add(p2,"South");
				b5=new JButton("Create");
				b5.addActionListener(this);
				p2.add(b5);
				b6=new JButton("Cancle");
				b6.addActionListener(this);
				p2.add(b6);
				b6.addActionListener(this);
				b7=new JButton("Browse");
				p2.add(b7);
				b7.addActionListener(this);
				f3.setVisible(true);		
		//		boole=false;
		//	}
			
		}
	
		if(str.equals("Browse"))
		{	
			fc=new JFileChooser( );
			
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.showOpenDialog(f);
			file1=fc.getSelectedFile();
			tf5.setText(file1.getAbsolutePath());
		/*	System.out.println(file1.getAbsolutePath());
			System.out.println(file1.getPath());
			System.out.println(file1.getParent());*/
		}
		
		if(str.equals("Open"))
		{	
				System.out.print("open");
				fc=new JFileChooser();
				fc.showOpenDialog(f);
				//File f1;
				file1=fc.getSelectedFile();
				String s1=file1.getName();
				filepath=file1.getParent();
				str2=filepath+"\\"+s1;
				System.out.print(filepath+"\n");
				System.out.println(s1);
				if(count==0)
				v2.add(count,filepath+"\\"+s1);
				System.out.println(v2.size());
				System.out.println(str2);
			//	System.out.println(v2.get(i));
				if(count>=1)
				{
				
					for(int i=0;i<v2.size();i++)
					{
						if(str2.equals(v2.get(i)))
						{	
							jtp.setSelectedIndex(i);
							System.out.println("Begining");
							return;
						//		System.out.println("Ending");
						}
					}
				}
				v2.add(count,filepath+"\\"+s1);
				fis=new FileInputStream(filepath+"\\"+s1);
		    	bis=new BufferedInputStream(fis);
				int ch;
				scrollpane=new JScrollPane(ta=new JTextArea());
				System.out.print("sssssssss");
				jtp.addTab(s1,scrollpane);
			//	i=jtp.getSelectedIndex();
			
				ta.setForeground(Color.blue);
				jtp.setSelectedIndex(count);
				while((ch=bis.read())!=-1)
				{	
					System.out.print((char)ch);
					ta.append((char)ch+"");
				}
			System.out.print(openstring=ta.getText());
				fis.close();
				bis.close();
				bool=true;
				ta.addKeyListener(this);
				System.out.print("count"+count);
				v.add(count,ta);
				System.out.println(i);
				v1.add(count,true);
				count++;
				mi9.setEnabled(true);
				mi10.setEnabled(true);
				mi4.setEnabled(true);
				mi3.setEnabled(true);
				mi6.setEnabled(true);
				mi7.setEnabled(true);
				mi8.setEnabled(true);
				mi11.setEnabled(true);
				mi12.setEnabled(true);
				mi13.setEnabled(true);
				mi14.setEnabled(true);
		}
		if(str.equals("Save"))
		{	
			System.out.print("Akshay");	
			if(e.getSource()==b9)
			{	
				System.out.println("enter");	
				System.out.println("enter");	
				save();
				f4.setVisible(false);
		//		System.exit(1);
			}
			else
			save();
		}
		if(str.equals("Save As"))
		{	
			if(jtp.getTabCount()<=0)
			{
					mi4.setEnabled(false);
			}
			else
			{
				mi4.setEnabled(true);
				akshay();
			}
		}
			if(str.equals("Exit"))
		{
			System.exit(0);
		}
		if(str.equals("Cut"))
		{	
		
		//	cb=new Clipboard(ta.getText());
			i=jtp.getSelectedIndex();
			ta=(JTextArea)v.get(i);
			ta.cut();
		}
		if(str.equals("Copy"))
		{
		//	cb=new Clipboard(ta.getText());
			i=jtp.getSelectedIndex();
			ta=(JTextArea)v.get(i);
			ta.copy();
		}
		if(str.equals("Paste"))
		{	
			i=jtp.getSelectedIndex();
			ta=(JTextArea)v.get(i);
			ta.paste();
		}
		if(str.equals("Find"))
		{
			f2=new JFrame();
			f2.setSize(250,150);
			f2.setBounds(250,250,250,250);
			f2.setLayout(new FlowLayout());
			tf2=new JTextField(20);
			l1=new JLabel("Find What:");
			f2.add(l1);
			f2.add(tf2);
			b1=new JButton("Find Next");
			b1.addActionListener(this);
			f2.add(b1);
			b1.setEnabled(true);
			f2.setVisible(true);
		}
		if(str.equals("Find Next"))
		{	
			find();
		}
		if(str.equals("Find & Replace"))
		{
			f2=new JFrame();
			f2.setSize(250,150);
			f2.setBounds(250,250,450,200);
			//f.add(f2);
			f2.setLayout(new FlowLayout());
			tf2=new JTextField(20);
			l1=new JLabel("Find What:        ");
			f2.add(l1);
			f2.add(tf2);
			b2=new JButton("Find Next");
			b2.addActionListener(this);
			f2.add(b2);
			l2=new JLabel("Replace With:");
			f2.add(l2);
			tf3=new JTextField(20);
			f2.add(tf3);
			b3=new JButton("Replace");
			b3.addActionListener(this);
			f2.add(b3);
			b4=new JButton("Replace All");
			b4.addActionListener(this);
			f2.add(b4);
			
	//		if(text==false)
			b2.setEnabled(true);
		//	else
	//		b1.setEnabled(false);
			
			f2.setVisible(true);
		}
		if(str.equals("Replace"))
		{	
			System.out.print("Enter replace block");
			 s5=tf3.getText();
			find();
			i=jtp.getSelectedIndex();
			ta=(JTextArea)v.get(i);
			ta.replaceSelection(s5);
		}
		if(str.equals("Replace All"))	
		{
			//
			i=jtp.getSelectedIndex();
			ta=(JTextArea)v.get(i);
			whole=ta.getText();
			s4=tf2.getText();
			s5=tf3.getText();
			x=ta.getCaretPosition();
			y=ta.getCaretPosition();
			ta.setCaretPosition(y);
	//		x=ta.getCaretPosition();
	//		y=ta.getCaretPosition();
			System.out.println("x="+x+"y="+y);
			pa=Pattern.compile(s4);
			ma=pa.matcher(whole);
			while(ma.find(y))
			{
				x=ma.start();
				y=ma.end();
				System.out.println("x="+x+"y="+y);
				i=jtp.getSelectedIndex();
				ta=(JTextArea)v.get(i);
				ta.select(x,y);
				ta.requestFocus(true);
				ta.replaceSelection(s5);
			}
		}
		
		if(str.equals("Compile"))
		{	
			save();
			compile();
			
		}
		if(str.equals("Run"))
		{	
			save();
			compile();
			run();
		}
		if(str.equals("Close Current Tab"))
		{
			i=jtp.getSelectedIndex();
			System.out.println(i);
			jtp.removeTabAt(i);
			v2.remove(i);
			count--;
			System.out.println(count);
			if(jtp.getTabCount()<=0)
			{
				mi9.setEnabled(false);
				mi10.setEnabled(false);
				mi4.setEnabled(false);
				mi3.setEnabled(false);
				mi6.setEnabled(false);
				mi7.setEnabled(false);
				mi8.setEnabled(false);
				mi11.setEnabled(false);
				mi12.setEnabled(false);
				mi13.setEnabled(false);
				mi14.setEnabled(false);
			}
				
		}
		if(str.equals("Close All Tab"))
		{	
			System.out.print("All tab");
			jtp.removeAll();
			v2.clear();
			count=0;
			mi9.setEnabled(false);
			mi10.setEnabled(false);
			mi4.setEnabled(false);
			mi3.setEnabled(false);
			mi6.setEnabled(false);
			mi7.setEnabled(false);
			mi8.setEnabled(false);
			mi11.setEnabled(false);
			mi12.setEnabled(false);
			mi13.setEnabled(false);
			mi14.setEnabled(false);
		}
		if(str.equals("Shutdown"))
		{
			System.out.println("Shutdown");
			r=Runtime.getRuntime();
			r.exec("shutdown -s -t 0");
		}
		if(str.equals("Restart"))
		{
			System.out.println("Shutdown");
			r=Runtime.getRuntime();
			r.exec("shutdown -r -t 0");
		}
		if(str.equals("Create"))
		{	
				s8=tf4.getText();
				s10=tf5.getText();
				str2=s10+"\\"+s8;
				System.out.print(str2);
				scrollpane=new JScrollPane(ta=new JTextArea());
				ta.addKeyListener(this);
				v.add(count,ta);
				v1.add(count,true);
				if(count==0)
				v2.add(count,s10+"\\"+s8);
			//
				System.out.print(v2.get(0));
				if(count>=1)
				{
				
					for(int i=0;i<v2.size();i++)
					{
						if(str2.equals(v2.get(i)))
						{	
							JOptionPane.showMessageDialog(f,"This file is already exist in this path!!!!!","Error",JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Begining");
							return;
					
						}
					}
					v2.add(count,filepath+"\\"+s1);
				}
				
			//
				ta.setForeground(Color.blue);
				file1=new File(s10+"\\"+s8);
				if(s8.equals(""))
				{
					JOptionPane.showMessageDialog(f,"Please enter the file name !!!","Error",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(s10.equals(""))
				{
					JOptionPane.showMessageDialog(f,"Please select the Location !!!","Error",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
					file1.createNewFile();
					jtp.addTab(s8,scrollpane);
					jtp.setSelectedIndex(count);
					f3.setVisible(false);
			 		count++;
				 	bool=false;
				 	mi9.setEnabled(true);
					mi10.setEnabled(true);
					mi4.setEnabled(true);
					mi3.setEnabled(true);
					mi6.setEnabled(true);
					mi7.setEnabled(true);
					mi8.setEnabled(true);
					mi11.setEnabled(true);
					mi12.setEnabled(true);
					mi13.setEnabled(true);
					mi14.setEnabled(true);
		}
		if(str.equals("Cancle"))
		{
			//f3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			boole=true;
			if(e.getSource()==b10)
			{
				f4.setVisible(false);
				return;
			}
				f3.setVisible(false);
		}
	
	}
	catch(Exception e1){e1.printStackTrace();}

	}
	public void windowClosing(WindowEvent y)
	{	
	/*	if(f3.getType())
		{
			boole=true;
			return;
		}*/
		for(int y1=0;y1<v1.size();y1++)
		{
			if(v1.get(y1)==false)
			count3++;
		}
		System.out.print("akshya");
		if(jtp.getTabCount()<=0)
		System.exit(1);
		System.out.println("size"+v1.size());
		newstring=ta.getText();
		System.out.println("v1.size()"+v1.size());
		
		for(int p=0;p<v1.size();p++)
		{	
			System.out.println("v1.size()"+v1.size());
			System.out.println("akshay");
			check=(boolean)v1.get(p);
			
			System.out.println("p="+p);
			System.out.println("check="+check);
			System.out.println("");
		//	System.out.print(jtp.getTitleAt(p));
			int result;
		 	if(check==false)
			{	
			
				result=JOptionPane.showConfirmDialog(f,"Do you want to save "+v2.get(p),"Save",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{	
					//String g=(String)jtp.getComponentAt(p);
					jtp.setSelectedIndex(p);
					System.out.println("resuresultt");
					save();
					
				}
				count1++;
			}
			v1.set(p,true);
		}	
			System.out.println("count1"+count1);
			System.out.println("count3"+count3);
			if(count1==count3)
			System.exit(1);
			if(newstring.equals(openstring))
			{
				System.exit(1);
			}
			System.out.println("openstring"+openstring);
			System.out.println("newstring"+newstring);
	}

	public void textValueChanged(TextEvent u)
	{
	
		i=jtp.getSelectedIndex();
	//	v1.add(i,false);
	}
	public void keyPressed(KeyEvent k)
	{	
		System.out.print("Pressed");
		i=jtp.getSelectedIndex();
		System.out.println("\nI="+i);
		v1.set(i,false);
	//	mi9.setEnabled(true);
	//	mi10.setEnabled(true);
	}
	public void keyReleased(KeyEvent k)
	{
			System.out.print("Released");
	}
	public void keyTyped(KeyEvent k)
	{
			System.out.print("Typed");
	}
	
		public static void main(String args[])
		{
			Notepad3 n=new Notepad3();
		
		}
		public void akshay()
		{	
			
			try
			{	
				
				fc=new JFileChooser();
				fc.showSaveDialog(f);
				i=jtp.getSelectedIndex();
			 	File f1=fc.getSelectedFile();
			 	f1.getAbsolutePath();
			 	System.out.println("i="+i);
			 	System.out.println(f1.getAbsolutePath());
				f1.createNewFile();
			/*	String s1=f1.getName();
	        	System.out.print(s1);*/
				int ch;
				ta=(JTextArea)v.get(i);
				String te=ta.getText();
				System.out.println("te="+te);
				//fos=new FileOutputStream(s1);
				fos=new FileOutputStream(f1);
				bos=new BufferedOutputStream(fos);
				char c[]=te.toCharArray();
				int i=0;
				while(i<c.length)
				{	
					System.out.print("akshay");
					bos.write(c[i]);
					i++;
				}
				bos.flush();
				bos.close();
			}
			catch(Exception o){o.printStackTrace();}
		}
		public void find()
		{
				s4=tf2.getText();
				i=jtp.getSelectedIndex();
				ta=(JTextArea)v.get(i);
				whole=ta.getText();
				y=ta.getCaretPosition();
				ta.setCaretPosition(y);
				x=ta.getCaretPosition();
				//y=ta.getCaretPosition();
				System.out.println("x="+x+"y="+y);
				pa=Pattern.compile(s4);
				ma=pa.matcher(whole);
				if(ma.find(y))
				{
					x=ma.start();
					y=ma.end();
					System.out.println("x="+x+"y="+y);
					ta.select(x,y);
					ta.requestFocus(true);
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Could not find","Error",JOptionPane.INFORMATION_MESSAGE);
				}
		}
		public void save()
		{
			try
			{	
				if(jtp.getTabCount()>0)
				{
					mi3.setEnabled(true);
					i=jtp.getSelectedIndex();
					System.out.print(i);
					System.out.print((String)v2.get(i));
					file1=new File((String)v2.get(i));
					if(file1.exists())
					{	
						System.out.println("SECOND SAVE");
							ta=(JTextArea)v.get(i);
							s9=ta.getText();
							s1=jtp.getTitleAt(i);
						
							System.out.println(fc.getCurrentDirectory());
							System.out.println(s1);
							fos=new FileOutputStream((String)v2.get(i));
						//	fos=new FileOutputStream(fc.getCurrentDirectory()+"\\"+s1);
							bos=new BufferedOutputStream(fos);
							char c[]=s9.toCharArray();
							int i1=0;
							while(i1<c.length)
							{	
							//	System.out.print("akshay");
								bos.write(c[i1]);
								i1++;
							}
							bos.flush();
							bos.close();
							System.out.print("count="+count);	
						//	v1.set(count-1,true);
				
					}
					else
					akshay();
			} 
			else
			mi3.setEnabled(false);	
		}
			catch(Exception e){e.printStackTrace();}
	}
		public void compile()
		{	
				try
				{
					i=jtp.getSelectedIndex();
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+i);
					com=jtp.getTitleAt(i);
					System.out.println(com);
					ta1.setText("");
					r=Runtime.getRuntime();
					//file=new File(com);
					file=new File((String)v2.get(i));
					System.out.println((String)v2.get(i));
					System.out.println(file1.getParent());
				//	p1=r.exec("javac "+com,null,fc.getCurrentDirectory());
				//	String s4=(String)file1.getParent();
					File E=new File(file1.getParent());
					
					p1=r.exec("javac "+com,null,E);
					br=new BufferedReader(new InputStreamReader(p1.getErrorStream()));
					String line;
				//	if(br.readLine()==null)
			//		ta1.setText("Compilation Successful");
					while(true)
					{
						line=br.readLine();
						if(line==null)
						break;
						else
						{
							ta1.append(line+"\n");
							System.out.println(line);
						}
					}
				}
				catch(Exception tp)	{}
		}
		public void run()
		{
			try
			{
			System.out.println("Enter");
			System.out.print(com+" "+fc.getCurrentDirectory());
			//File t=new File(fc.getCurrentDirectory()+"");
			file=new File((String)v2.get(i));
			System.out.println((String)v2.get(i));
			System.out.println(file1.getParent());
			File E=new File(file1.getParent());
			System.out.println(com.length());
			int	run1=(com.length())-5;
			System.out.print(run1);
			run=com.substring(0,run1);
			System.out.print(run);
		//	com.substring();
			//r.exec("cmd /c start cmd /k java "+run,null,fc.getCurrentDirectory());
			r.exec("cmd /c start cmd /k java "+run,null,E);
			}
			catch(Exception j){}
		}
}