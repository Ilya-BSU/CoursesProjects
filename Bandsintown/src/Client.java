import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Client {
	private static double version = 1.0;
	final static String app_id = "IlyasApp";
	final static String format = ".json";
	static private ArrayList<Event> events = new ArrayList<Event>();

	public static void main(String[] args) {
		boolean check = true;
		String bandname = "";
		JSONArray jb = null;
		Server server = new Server();
		try {
			JSONParser parser = new JSONParser();
			Scanner sc = new Scanner(System.in);
			while (check) {
				System.out.println("Type artist name:");
				bandname = sc.nextLine();
				bandname = bandname.replace("/", "%252F");
				bandname = bandname.replace(" ", "%20");
				server.getData(bandname, format, app_id, version);
				BufferedReader br = new BufferedReader(new FileReader(
						"temp_in.json"));
				if (br.readLine() != null) {
					br.close();
					Object obj = parser.parse(new FileReader("temp_in.json"));
					JSONArray jsonArray = (JSONArray) obj;
					Iterator<JSONObject> iterator = jsonArray.iterator();
					String urlType = "url";
					switch (Double.toString(version)) {
					case "1.0":
						urlType = "url";
						break;
					case "2.0":
						urlType = "ticket_url";
						break;
					default:
						break;
					}
					while (iterator.hasNext()) {
						JSONObject tempObject = iterator.next();
						String dateTime = (String) tempObject.get("datetime");
						Date date = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss").parse(dateTime);
						JSONObject obj_Mas = (JSONObject) tempObject
								.get("venue");
						events.add(new Event(date,
								(String) obj_Mas.get("name"),
								(String) tempObject.get(urlType),
								(String) obj_Mas.get("city"), (String) obj_Mas
										.get("country")));
					}
					for (Event e : events)
						System.out.println(e.toString());
					events.clear();
				}
				System.out
						.println("Type exit for quit or press ENTER for one more request");
				if (sc.nextLine().equals("exit")) {
					check = false;
					sc.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
