import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class principal {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		List<String> listVoos = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Local de Partida ou tuga");
		String depart = sc.nextLine();
		//String depart = "tuga";
		
		System.out.println("Data de inicio");
		String starDay= sc.nextLine();
		System.out.println("data de fim");
		String endDay= sc.nextLine();
		
		if(depart.contentEquals("tuga")){
			portugal(starDay,endDay, listVoos);
		}
		else{
			
			System.out.println("Local de chegada");
			String arrive = sc.nextLine();
			String monthStarDay= "0";
			String monthEndDay= "0";
			search(depart,arrive, starDay,endDay, listVoos);
		}
		
		print(listVoos);
		System.out.println("DONE");
		
	}
	


	private static void print( List<String>listVoos) throws IOException {
		
		java.util.Date dt_atual = new java.util.Date();        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");                
        String data = sdf.format(dt_atual);
        System.out.println(data);
		FileWriter file = new FileWriter(data);
	    PrintWriter savefile = new PrintWriter(file);
		
		for(String i : listVoos ){
			//System.out.println(i);
			savefile.printf(i +"\n");
		}
		file.close();
	}

	private static void portugal(String starDay, String endDay, List<String> listVoos) throws InterruptedException{
		
		String [] lisboa = {"Lisbon", "Birmingham", "London Gatwick", "Manchester"};
		String [] faro = {"Faro (Algarve)", "Birmingham", "London Gatwick", "Manchester","Leeds-Bradford","London Luton" };
		//String [] porto = {"Porto", "London Luton", "Manchester"};
		
		
		for(int i=1; i<lisboa.length; i++) {
			search(lisboa[0], lisboa[i], starDay, endDay, listVoos);
		}
		//for(int i=1; i<faro.length; i++) {
			//search(faro[0], faro[i], starDay, endDay, listVoos);
		//}
		//for(int i=1; i<porto.length; i++) {
			//search(porto[0], porto[i], starDay, endDay, listVoos);
		//}
	}
	
	
	private static void search(String depart, String arrive, String starDay, String endDay, List<String> listVoos) throws InterruptedException{
		String day = starDay;
		
		for(int i = Integer.parseInt(day); i<=Integer.parseInt(endDay); i++){
			listVoos.add(monarch.run(Integer.toString(i),depart, arrive));
			listVoos.add(monarch.run(Integer.toString(i),arrive, depart));
			String[] parts = listVoos.get(listVoos.size()-1).split("\\s+");
			i = Integer.parseInt(parts[7])+1;
		
		}
	}

}
