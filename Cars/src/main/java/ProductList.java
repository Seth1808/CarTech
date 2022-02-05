
public class ProductList {
	protected String name;
	
	public ProductList(String name, String description, String price, String specs) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.specs = specs;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	protected String description;
	protected String price;
	protected String specs;
}


