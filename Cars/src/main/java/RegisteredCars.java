
public class RegisteredCars {
	
	protected String carModel;
	protected String licensePlate;
	protected String warranty;
	
	
	public RegisteredCars(String carModel, String licensePlate, String warranty) {
		super();
		this.carModel = carModel;
		this.licensePlate = licensePlate;
		this.warranty = warranty;
	}
	
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	

}
