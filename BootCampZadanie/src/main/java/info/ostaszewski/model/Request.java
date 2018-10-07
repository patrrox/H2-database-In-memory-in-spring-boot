package info.ostaszewski.model;



public class Request  {
	
	private String clientId;
	private long requestId;
	private String name;
	private int quantity;
	private double price;

	public Request() {
		
	}
	
	public Request(String clientId, long requestId, String name, int quantity, double price) {
		this.clientId = clientId;
		this.requestId = requestId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
	
		return clientId+" "+ requestId+" "+name+" "+quantity+" "+price;
	}

}
