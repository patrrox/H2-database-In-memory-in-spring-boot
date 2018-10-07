package info.ostaszewski.controller;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import info.ostaszewski.model.Request;

import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class XMLFileManager {

	public XMLFileManager() {

	}

	public List<Request> getData(String fileName) {
		
		List<Request> list = new ArrayList<>();
		try {

			InputStream fXmlFile = this.getClass().getResourceAsStream(fileName);
			
			
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
						
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();

				
						
				NodeList nList = doc.getElementsByTagName("request");
						
				

				Request request;
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
							
					
							
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						try {
							

							 String clientId= eElement.getElementsByTagName("clientId").item(0).getTextContent();
							 Long requestId=Long.parseLong(eElement.getElementsByTagName("requestId").item(0).getTextContent());
							 String name= eElement.getElementsByTagName("name").item(0).getTextContent();
							 Integer quantity=Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent());
							 Double price=Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());

							 //if (clientId!=null &&requestId!=null&&name!=null&&quantity!=null&&price!=null)
							 //{
								 request= new Request(clientId, requestId, name, quantity, price);
								 list.add(request);
							// }else
							 
							//System.out.println("clientId : "+ clientId);
							//System.out.println("requestId : " + requestId);
							//System.out.println("name : " + name);
							//System.out.println("quantity : " + quantity);
							//System.out.println("price : " + price);
							//System.out.println("clientId : " + eElement.getElementsByTagName("clientId").item(0).getTextContent());
							//System.out.println("requestId : " + eElement.getElementsByTagName("requestId").item(0).getTextContent());
							//System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
							//System.out.println("quantity : " + eElement.getElementsByTagName("quantity").item(0).getTextContent());
							//System.out.println("price : " + eElement.getElementsByTagName("price").item(0).getTextContent());

						}catch(NullPointerException e)
						{
							System.out.println("Błąd podczas wczytywania zamówienia: "+e.getMessage());
							
						}
				
						
					}
				}
			    } catch (Exception e) {
				e.printStackTrace();
			    }
		return list;
			  }
	
	
	}

	

