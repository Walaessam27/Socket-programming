import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
   public class TCP_Server{
	 static int flag=0;
	public static void main(String argv[]) throws FileNotFoundException, IOException
	{   File dataFile = new File("data.txt");
	try (Scanner input = new Scanner(dataFile)) {
	HashMap<Integer, String> Vehicles = new HashMap<>();
	int lineNum = 0;
	while (input.hasNextLine()) {
	    String line = input.nextLine();
	    Vehicles.put(lineNum, line);
	    lineNum++;}
	     String clientSentence;
	     String capSentence = "";
	     System.out.println("It works successfully!"+'\n');
	     for (Map.Entry<Integer, String> entry : Vehicles.entrySet()) {
	    	    System.out.println("Line " + entry.getKey() + " " + entry.getValue());}
	    try (ServerSocket welcomeSocket = new ServerSocket(6789)) {
	    	while(true){
		 	 Socket connectionSocket = welcomeSocket.accept();
		 	 BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
					 
		 	 DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
						
		 	 clientSentence = inFromClient.readLine().toUpperCase();
		 	 
		 	 System.out.println("The clientSentence:"+clientSentence+'\n');	
		 	  capSentence = "";
		 	 for (Map.Entry<Integer, String> entry : Vehicles.entrySet()) {
		 		 if (entry.getValue().contains(clientSentence))
		 	    capSentence= entry.getValue();
		 		 else  if (capSentence=="") capSentence= "Vehicle is not found"+'\n';	 } 
		 			
		 	 System.out.println(" The capSentence:"+capSentence+'\n');	     
		 	 outToClient.writeBytes(capSentence+ '\n');	}}}}}
