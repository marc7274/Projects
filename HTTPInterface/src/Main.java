import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.*;
import java.util.*;

import com.google.gson.*;

public class Main {

	public static void main(String[] args) throws IOException
	{
		Map<String, String> parameters = new HashMap<>();
		parameters.put("identifier", "val");
		parameters.put("name", "val");
		//Operators
		Operator[] operator_list = new Gson().fromJson(get_response("http://192.168.111.123:4000/operator"), Operator[].class);
		Operator operator_list_post = new Gson().fromJson(post_response("http://192.168.111.123:4000/operator",parameters), Operator.class);
		Operator[] operator_list_2 = new Gson().fromJson(get_response("http://192.168.111.123:4000/operator"), Operator[].class);
		parameters.clear();
		//Shipments
		Shipment[] shipment_list = new Gson().fromJson(get_response("http://192.168.111.123:4000/shipment"), Shipment[].class);
		Shipment shipment_list_post = new Gson().fromJson(post_response("http://192.168.111.123:4000/shipment",parameters), Shipment.class);
		Shipment[] shipment_list_2 = new Gson().fromJson(get_response("http://192.168.111.123:4000/shipment"), Shipment[].class);
		parameters.clear();
		//Devices
		Device[] device_list = new Gson().fromJson(get_response("http://192.168.111.123:4000/device"), Device[].class);
		Device[] device_list_post = new Gson().fromJson(post_response("http://192.168.111.123:4000/device",parameters), Device[].class);
		Device[] device_list_2 = new Gson().fromJson(get_response("http://192.168.111.123:4000/device"), Device[].class);
		
		System.out.println(new Gson().toJson(operator_list));
		System.out.println(new Gson().toJson(operator_list_post));
		System.out.println(new Gson().toJson(operator_list_2));
		System.out.println(new Gson().toJson(shipment_list));
		System.out.println(new Gson().toJson(shipment_list_post));
		System.out.println(new Gson().toJson(shipment_list_2));
		System.out.println(new Gson().toJson(device_list));
		System.out.println(new Gson().toJson(device_list_post));
		System.out.println(new Gson().toJson(device_list_2));
	}
	
	public static String get_response(String url_string) throws IOException{
		URL url = new URL(url_string);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		//timeout
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		//reading request
		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}
	public static String post_response(String url_string, Map<String,String> parameters) throws IOException
	{
		URL url = new URL(url_string);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
		//timeout
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		//reading request
		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}
}
