package record.dto;

public class ParseDTO {
	public String weatherLocation;
	public String weatherName;
	public String weatherImg;
	public String weatherStatus;
	public String weatherDegree;
	public String weatherClock;
	
	public ParseDTO(){}

	public ParseDTO(String weatherLocation, String weatherName,
			String weatherImg, String weatherStatus, String weatherDegree,
			String weatherClock) {
		super();
		this.weatherLocation = weatherLocation;
		this.weatherName = weatherName;
		this.weatherImg = weatherImg;
		this.weatherStatus = weatherStatus;
		this.weatherDegree = weatherDegree;
		this.weatherClock = weatherClock;
	}

	public String getWeatherLocation() {
		return weatherLocation;
	}

	public void setWeatherLocation(String weatherLocation) {
		this.weatherLocation = weatherLocation;
	}

	public String getWeatherName() {
		return weatherName;
	}

	public void setWeatherName(String weatherName) {
		this.weatherName = weatherName;
	}

	public String getWeatherImg() {
		return weatherImg;
	}

	public void setWeatherImg(String weatherImg) {
		this.weatherImg = weatherImg;
	}

	public String getWeatherStatus() {
		return weatherStatus;
	}

	public void setWeatherStatus(String weatherStatus) {
		this.weatherStatus = weatherStatus;
	}

	public String getWeatherDegree() {
		return weatherDegree;
	}

	public void setWeatherDegree(String weatherDegree) {
		this.weatherDegree = weatherDegree;
	}

	public String getWeatherClock() {
		return weatherClock;
	}

	public void setWeatherClock(String weatherClock) {
		this.weatherClock = weatherClock;
	}
	
}
