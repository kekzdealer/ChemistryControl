package data_loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.gson.Gson;

public class GSONLoader {
	
	public <T> T parseLocalJSON (String jsonName, Class<T> dataHolder) {
		
		final File file = new File("/data/" + jsonName);
		String jsonString = "";
		
		try (BufferedReader br = file.getReader()){
			final StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			jsonString = sb.toString();
		} catch (IOException ex) {
			System.err.println("IOException while reading: " +jsonName);
		}
		
		final Gson gson = new Gson();
		return gson.fromJson(jsonString, dataHolder);
	}
	
	public <T> T parseRemoteJSON(String urlString, Class<T> dataHolder) {
		
		HttpURLConnection con = null;
		try {
			final URL url = new URL(urlString);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (ProtocolException ex) {
			ex.printStackTrace();
		} catch (ConnectException ex) {
			System.err.println("Failed to connect to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String jsonString = "";
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))){
			final StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			jsonString = sb.toString();
		} catch (ConnectException ex) {
			System.err.println("Failed to connect to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		final Gson gson = new Gson();
		return gson.fromJson(jsonString, dataHolder);
	}
}
