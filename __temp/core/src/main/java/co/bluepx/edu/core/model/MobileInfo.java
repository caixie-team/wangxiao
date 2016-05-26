package co.bluepx.edu.core.model;
/**
 * 手机信息类
 * 
 */
public class MobileInfo {
	
	
	public MobileInfo() {
		super();
	}
	
	public MobileInfo(String id, String provinceName, String locationName,
			String operatorName, String areaCode, String postCode) {
		super();
		this.id = id;
		this.provinceName = provinceName;
		this.locationName = locationName;
		this.operatorName = operatorName;
		this.areaCode = areaCode;
		this.postCode = postCode;
	}

	private String id;
	
	private String provinceName;//省份名称
	
	private String locationName;//所在地
	
	private String operatorName;//运营商
	
	private String areaCode;//区号
	
	private String postCode;//邮编
	
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
