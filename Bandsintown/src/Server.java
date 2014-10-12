import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Server {
	private static String BASE_URL = "http://api.bandsintown.com/artists/";

	public void getData(String artist, String format, String app_id,
			double version) {
		HttpURLConnection con = null;
		InputStream is = null;
		try {
			FileOutputStream fos = new FileOutputStream("temp_in.json");
			con = (HttpURLConnection) (new URL(BASE_URL + artist + "/"
					+ "events" + format + "?api_version="
					+ Double.toString(version) + "&app_id=" + app_id))
					.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			is = con.getInputStream();
			ReadableByteChannel rbc = Channels.newChannel(is);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			is.close();
			con.disconnect();
		} catch (FileNotFoundException ee) {
			System.out.println("Artist not found");
		} catch (MalformedURLException ee) {
			System.out.println("Exception. Something not found");
		} catch (Exception ee) {
			System.out.println("Global Exception");
		}
	}
}
