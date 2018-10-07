package info.ostaszewski.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import info.ostaszewski.model.Request;

public class CSVFileManager {

	
	public CSVFileManager() {
		
	}
	
	public List<Request> getData(String csvFile) {
		
		List<Request> list = new ArrayList<>();
		
		
		 
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        Request request;
	        try {
	        	
	        	InputStream in = getClass().getResourceAsStream(csvFile); 
	        	 br = new BufferedReader(new InputStreamReader(in));
	            //br = new BufferedReader(new FileReader(csvFile));
	        
	            br.readLine();
	            while ((line = br.readLine()) != null ) {
	            	
	                // use comma as separator
	                String[] values = line.split(cvsSplitBy);

	               // System.out.println("Country [code= " + values[2] + " , name=" + values[3] + "]");
	                try {
						request = new Request(values[0],Long.parseLong( values[1]), values[2], Integer.parseInt(values[3]), Double.parseDouble(values[4]));
						list.add(request);
					}catch(NullPointerException e)
					{
						System.out.println("Błąd podczas wczytywania zamówienia: "+e.getMessage());
					}

	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    
		
		
		return list;
	}
	

public  void writeData(String filePath, List<Request> list) 
{ 
  
    // first create file object for file placed at location 
    // specified by filepath 
    File file = new File(filePath); 
  
    try { 
        // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 
  
 
        CSVWriter writer = new CSVWriter(outputfile); 
  
        // create a List which contains String array 
        List<String[]> data = new ArrayList<String[]>(); 
        
        for (int i=0;i<list.size();i++)
        {
        	data.add(new String[] { list.get(i).getClientId(),Long.toString(list.get(i).getRequestId()),list.get(i).getName(),Integer.toString(list.get(i).getQuantity()),Double.toString(list.get(i).getPrice())});
       
        }
        writer.writeAll(data); 
        // closing writer connection 
        writer.close(); 
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    } 
} 
public  void writeData(String filePath, String value) 
{ 
  
    File file = new File(filePath); 
  
    try { 
        // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 
  
 
        CSVWriter writer = new CSVWriter(outputfile); 
  
        String[] data = {"wynik", value }; 
        writer.writeNext(data); 
        // closing writer connection 
        writer.close(); 
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    } 
} 
	
	
}
