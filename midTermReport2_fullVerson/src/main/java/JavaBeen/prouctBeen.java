package JavaBeen;

public class prouctBeen {
	private static String[] proName;
	private static Float[] proPrice;

	public static void setProName(String[] proName) {
		prouctBeen.proName = proName;
	}


	public static void setProPrice(Float[] proPrice) {
		prouctBeen.proPrice = proPrice;
	}
	public static String getProName(int proid) {
		
		return proName[proid];
	}
	
	public static float getProPrice(int proid) {
		return proPrice[proid];
	}
	//產品總量
	public static int size() {
		return proName.length;
	}
	

}
