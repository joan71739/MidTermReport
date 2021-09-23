package JavaBeen;

public class orderBeen {
	private  String[] ordID;
	private  String[] storeName;
	private  String[] shopDate;
	private  String[] total;

	public  String getOrdID(int number) {
		return ordID[number];
	}

	public  String getStoreName(int number) {
		return storeName[number];
	}

	public  String getShopDate(int number) {
		return shopDate[number];
	}

	public  String getTotal(int number) {
		return total[number];
	}

	public  void  setOrdID(String[] ordID) {
		this.ordID = ordID;
	}

	public  void setStoreName(String[] storeName) {
		this.storeName = storeName;
	}

	public  void setShopDate(String[] shopDate) {
		this.shopDate = shopDate;
	}

	public  void setTotal(String[] total) {
		this.total = total;
	}
	public  int size() {
		return ordID.length;
	}




	public orderBeen() {
		super();
	}

	public orderBeen(String[] ordID, String[] shopDate, String[] total) {
		super();
		this.ordID = ordID;
		this.shopDate = shopDate;
		this.total = total;
	}

}
