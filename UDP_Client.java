import java.io.*;
import java.net.*;
class UDP_Client {
	public static void main(String args[]) throws Exception
		{
			String flag;
		do{
		System.out.println("PLease enter Vehicle plate-ID:");	 
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket();
		
		InetAddress IPAddress = InetAddress.getByName("192.168.37.1");
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		 System.out.println("The sentence:"+sentence+'\n');	
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				
		clientSocket.send(sendPacket);	
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
				
		String data =new String(receivePacket.getData());
		System.out.println("FROM SERVER: \n");
		System.out.println("VehicleplateID   Make   Model   Year   Colour   OwnerName   OwnerID");
		System.out.println(data.trim());
		System.out.println("\nPlease if you are done enter exit, if not press enter.");
		flag=inFromUser.readLine();
		clientSocket.close();
		} while(!flag.equalsIgnoreCase("exit"));}}