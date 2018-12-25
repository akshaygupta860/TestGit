import java.io.*;
import java.net.*;
import java.util.Vector;
public class ClientThread 
{	
	String str,sender,receiver,private_message;
	int count,count1,index,count2;
	PrintWriter out;
	Socket socket;
	BufferedReader in;
	int i=0;
	char ch;
	public void set(Socket socket)
	{		
		try
		{
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream(),true);
		}
		catch(Exception e){e.printStackTrace();}
		while(true)
		{	
			try
			{
			 	str=in.readLine();
		 		while(count1 < TCPServer.vector_name.size() )
				{	
					System.out.println("Elements Are"+TCPServer.vector_name.get(count1));
				 	out.println(TCPServer.vector_name.get(count1));
				 	count1++;
				}
				System.out.println(" str = "+str);
				if(!str.equals(""))
				ch=str.charAt(0);
			 	if(ch == '*')
			 	{	
			 		TCPServer.vector_name.add(str);
			  		i++;
			 	}
			 	if(ch == 'p')
			 	{	
			 		sender=str.substring(7,str.indexOf("@",7));
			 		receiver=str.substring(str.indexOf("@",7)+1,str.lastIndexOf("@"));
			 		System.out.println("receiver IS "+receiver);
			 		String receiver1="*"+receiver;
			 		private_message=str.substring(str.lastIndexOf("@")+1,str.length());
			 		System.out.println("private_message="+private_message);
			 		for(int i=0;i<TCPServer.vector_name.size();i++)
			 		{
			 			if(receiver1.equals(TCPServer.vector_name.get(i)))
			 			index=i;
			 		}	
			 		socket=(Socket)TCPServer.vector.get(index);
			 		out=new PrintWriter(socket.getOutputStream(),true);
			 		out.println(str);
			 		ch=' ';
			 	}
			 	count=0;
				if(ch=='$')
			 	{
			 		String temp1=str.substring(1,str.length());
			 		System.out.println("temp1="+temp1);
			 		temp1="*"+temp1;
			 		TCPServer.vector_name.remove(temp1);
			 		while(count2 < TCPServer.vector_name.size() )
					{	
//						System.out.println("Enter ");
//				 		out.println("l"+TCPServer.vector_name.get(count2));
						System.out.println("str===="+str);
						out.println(str);
				 		count2++;
				 	}
			 	}
			 	else
			 	{
			 	
					while(count < TCPServer.vector.size())
					{	
						Socket 	soc=(Socket)TCPServer.vector.get(count);
						out=new PrintWriter(soc.getOutputStream(),true);
						out.println(str);
						count++;
					}
			 	}
			 }
			 catch(Exception y)
			 {
			 	y.printStackTrace();
			 	break;
			 }

		}
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
		try
		{
		
			while(count < TCPServer.vector.size())
			{	
				Socket 	soc=(Socket)TCPServer.vector.get(count);
				out=new PrintWriter(soc.getOutputStream(),true);
				out.println(str);
				count++;
			}
		}
		catch(Exception e){e.printStackTrace();}
	}
}
