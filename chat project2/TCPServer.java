import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.util.Vector;
class TCPServer	implements Runnable
{
	final static int port=65535;
	static 	Socket socket=null;
	static TCPServer tcpserver;
	static 	ServerSocket serversocket=null;
//	PrintWriter out;
	static	String str;
	static boolean flag=false;
	static 	Thread thread;
	static	Vector vector;
	static 	Vector vector_name=new Vector();;
	ClientThread clientthread;
	public void run()
	{
		clientthread=new ClientThread();
		clientthread.set(socket);
	}
	public static void main(String arsg[])
	{		
				try
				{
					vector=new Vector();
				//	vector_name=new Vector();
					System.out.println("SERVER");
			 		serversocket=new ServerSocket(port);//Creates a server socket, bound to the specified port
			 											//port - the port number, or 0 to use a port number that is automatically allocated.
			 		while(true)
					{	
						socket=serversocket.accept();
						vector.add(socket);
						System.out.println(socket);
						TCPServer tcpserver=new TCPServer();
			 			Thread t=new Thread(tcpserver);
			 			t.start();
			 			System.out.println("vector.size="+vector.size());	
					}
				}
				catch(Exception u){u.printStackTrace();}
	}
}