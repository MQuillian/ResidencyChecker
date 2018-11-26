public class Place {
	public String icon;
	public String name;
	public String formatted_address;
	public String formatted_phone_number;

	public void printAddress(){
		System.out.println(this.formatted_address);
	}

}
