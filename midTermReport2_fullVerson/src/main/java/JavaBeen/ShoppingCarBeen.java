package JavaBeen;

import java.io.Serializable;

public class ShoppingCarBeen implements Serializable {
	private int shoppingcarID;
	private int custID;
	private int proid;
	private int qty;
	private String ice;
	private String sugar;

	public ShoppingCarBeen(int proid, int qty,String ice,String sugar) {
		this.proid = proid;
		this.qty = qty;
		this.ice=ice;
		this.sugar=sugar;
	}


	public ShoppingCarBeen() {

	}

	public int getProid() {
		return proid;
	}
	
	public String getIce() {
		return ice;
	}
	public int getShoppingcarID() {
		return shoppingcarID;
	}
	public int getQty() {
		return qty;
	}
	public String getSugar() {
		return sugar;
	}
	public int getCustID() {
		return custID;
	}
	public int getPrice() {
		return (int)prouctBeen.getProPrice(proid);
	}
	public String getProName() {
		return prouctBeen.getProName(proid);
	}

	

	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public void setIce(String ice) {
		this.ice = ice;
	}
	public void setShoppingcarID(int shoppingcarID) {
		this.shoppingcarID = shoppingcarID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	
	public long total() {
		return (int)prouctBeen.getProPrice(proid)*qty;
	}

}
