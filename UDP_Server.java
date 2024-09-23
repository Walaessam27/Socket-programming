import java.net.*;
import java.util.HashMap;
import java.util.Map;
//import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.util.Scanner;
   public class UDP_Server {
	   public static void main(String args[]) throws Exception
	   {   File dataFile = new File("data.txt");
		try (Scanner input = new Scanner(dataFile)) {
		HashMap<Integer, String> Vehicles = new HashMap<>();
		int lineNum = 0;
		while (input.hasNextLine()) {
		    String line = input.nextLine();
		    Vehicles.put(lineNum, line);
		    lineNum++;}
		 System.out.println("It works successfully!"+'\n');
			for (Entry<Integer, String> entry : Vehicles.entrySet()) {
			    System.out.println("line: " + entry.getKey() +  " "+entry.getValue());}
			try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
				byte[] receiveData = new byte[1024];
	            byte[] sendData = new byte[1024];
	            while(true){
	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	serverSocket.receive(receivePacket);
	String sentence ="";
	 sentence = new String(receivePacket.getData()).toUpperCase();
	InetAddress IPAddress = receivePacket.getAddress();
	int port = receivePacket.getPort();
	 System.out.println("The sentence:"+sentence+'\n');	
	String capSentence="";
	 for (Map.Entry<Integer, String> entry : Vehicles.entrySet()) 
 		 if (entry.getValue().contains(sentence.trim())) 
 	    capSentence= entry.getValue();
 		else if(capSentence=="") capSentence="Vehicle is not found"+'\n';	 
	 
	 System.out.println("The capSentence:"+capSentence+'\n');
	      sendData = capSentence.getBytes();
	     		 
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
	      serverSocket.send(sendPacket); }}}}}
