package order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;


public class Order implements Serializable 
{	
	public class DelivaryInfo implements Serializable 
	{
		String delivaryAddress;
		String receiverName;
		String receiverPhoneNumber;

		public DelivaryInfo(String delivaryAddress, String receiverName, String receiverPhoneNumber)
		{
			this.delivaryAddress = delivaryAddress;
			this.receiverName = receiverName;
			this.receiverPhoneNumber = receiverPhoneNumber;
		}

		public String getDelivaryAddress() {
			return delivaryAddress;
		}

		public String getReceiverName() {
			return receiverName;
		}

		public String getReceiverPhoneNumber() {
			return receiverPhoneNumber;
		}
	}

	public class ItemInOrder implements Serializable
	{
		long productID;
		String greetingCard;

		public ItemInOrder(long productID, String greetingCard)
		{
			this.productID = productID;
			this.greetingCard = greetingCard;
		}

		public long getProductID() {
			return productID;
		}

		public String getGreetingCard() {
			return greetingCard;
		}
		
	}

	public final static float delivaryCost = 10;
	public enum Status{NEW, READLY, DELIVERED}
	public enum PayMethod {CASH, CREDITCARD, SUBSCRIPTION}

	int orderID;
	Status orderStatus;
	float  orderPrice;

	LocalDate orderDate;
	String    orderTime;
	DelivaryInfo delivaryInfo = null;
	PayMethod orderPaymentMethod;
	long orderOriginStore;
	long customerID;
	// 
	ArrayList<ItemInOrder> itemsInOrder;

	public Order(int id,Status status,float price,LocalDate date,String time,
			String delivaryAddress, String receiverName, String receiverPhoneNumber 
			,PayMethod payMethod,long originShop, long customerID) throws OrderException
	{
		itemsInOrder = new ArrayList<ItemInOrder>();
		setID(id);
		setStatus(status);
		setPrice(price);
		this.setOrderDate(date);
		this.setOrderTime(time);
		if (delivaryAddress != null)
			delivaryInfo = new DelivaryInfo(delivaryAddress, receiverName, receiverPhoneNumber);
		setOrderPaymentMethod(payMethod);
		setOrderOriginStore(originShop);
		setCustomerID(customerID);
	}


	public void setID(int orderID)
	{
		this.orderID = orderID;
	}

	public int getID()
	{
		return orderID;
	}

	public void setStatus(Status orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public Status getStatus()
	{
		return this.orderStatus;
	}

	public void setPrice(float price) throws OrderException
	{
		if(price >= 0)
			this.orderPrice = price;
		else
			throw new OrderException("price must be above or equal zero");
	}

	public float getPrice()
	{
		return this.orderPrice;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Calendar getOrderDateAndTime() 
	{
		Calendar orderDateAndTime = Calendar.getInstance();
		String[] time = orderTime.trim().split(":");
		orderDateAndTime.set(orderDate.getYear(), orderDate.getMonth().getValue(), orderDate.getDayOfMonth(),
				Integer.parseInt(time[0]), Integer.parseInt(time[1]));
		
		return orderDateAndTime;
	}
	
	public DelivaryInfo getDelivaryInfo() {
		return delivaryInfo;
	}

	public void setDelivaryInfo(DelivaryInfo delivaryInfo) {
		this.delivaryInfo = delivaryInfo;
	}

	public PayMethod getOrderPaymentMethod() {
		return orderPaymentMethod;
	}

	public void setOrderPaymentMethod(PayMethod orderPaymentMethod) {
		this.orderPaymentMethod = orderPaymentMethod;
	}

	public long getOrderOriginStore() {
		return orderOriginStore;
	}

	public void setOrderOriginStore(long orderOriginStore) {
		this.orderOriginStore = orderOriginStore;
	}

	public ArrayList<ItemInOrder> getItemsInOrder()
	{
		return itemsInOrder;
	}
	
	public void addItemToOrder(long productID, String greetingCard)
	{
		this.itemsInOrder.add(new ItemInOrder(productID, greetingCard));
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	

}
