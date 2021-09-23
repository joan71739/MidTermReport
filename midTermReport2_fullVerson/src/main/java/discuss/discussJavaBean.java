package discuss;

import java.io.Serializable;

public class discussJavaBean implements Serializable{
	
	public discussJavaBean() {}
	
	public String shopName;
	public String drink;
	public String drinkDiscuss;
	public int drinkstars;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getDrinkDiscuss() {
		return drinkDiscuss;
	}
	public void setDrinkDiscuss(String drinkDiscuss) {
		this.drinkDiscuss = drinkDiscuss;
	}
	public int getDrinkstars() {
		return drinkstars;
	}
	public void setDrinkstars(int drinkstars) {
		this.drinkstars = drinkstars;
	}

}
