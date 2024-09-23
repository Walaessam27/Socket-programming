import java.io.*;
import java.net.*;
	public class TCP_Client {
	public static void main(String argv[]) throws Exception
	{        String flag;
	do{	 
	String sentence;
	String output;
	System.out.println("PLease enter Vehicle plate-ID:");
	
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	Socket clientSocket = new Socket("192.168.37.1", 6789);
	
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	sentence = inFromUser.readLine();
			
	outToServer.writeBytes(sentence + '\n');
	System.out.println( "The sent sentence:"+sentence+'\n');

	output = inFromServer.readLine();
	
	System.out.println("FROM SERVER: \n");
	System.out.println("VehicleplateID   Make   Model   Year   Colour   OwnerName   OwnerID");
	System.out.println( output);
	
	clientSocket.close();
    
	System.out.println("\nPlease if you are done enter exit, if not press enter.");
				flag=inFromUser.readLine();
				clientSocket.close();
				
				} while(!flag.equalsIgnoreCase("exit"));}}
