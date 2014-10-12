import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private String name;
	private String url;
	private String city;
	private String country;
	private Date date;

	public Event() {
	}


	@Override
	public String toString() {
		DateFormat data_part1=new SimpleDateFormat("YYYY-MM-dd ");
		DateFormat data_part2=new SimpleDateFormat("hh.mm.ss ");
		return "Event [name=" + name + ", date=" + date.toLocaleString()+ ", city=" + city
				+ ", country=" + country + ", url=" + url+ "]";
	}


	public String getName() {
		return name;
	}

	public Event(Date date, String name, String url, String city, String country) {
		this.name = name;
		this.url = url;
		this.city = city;
		this.country = country;
		this.date = date;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
