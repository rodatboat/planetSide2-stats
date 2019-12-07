/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendingrequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import static jdk.nashorn.internal.objects.NativeMath.round;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class planetsideRequests {

    public static JSONArray getPlayerInfo(String username) throws MalformedURLException, ProtocolException, IOException, JSONException{
        //http://census.dhttp://census.daybreakgames.com/get/ps2:v2/character/?name.first_lower=" + username.toLowerCase() + "&c:resolve=stat_historyaybreakgames.com/get/ps2:v2/character/?character_id=5428047126282473569&c:resolve=stat_history
        String url = "http://census.daybreakgames.com/get/ps2:v2/character/?name.first_lower=" + username.toLowerCase() + "&c:resolve=stat_history";
        //String url = "http://census.daybreakgames.com/get/ps2:v2/character/?name.first_lower=" + "pizzahutdeliveryman" + "&c:resolve=stat_history";
        URL link = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setRequestMethod("GET");
        
        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        
        JSONObject object = new JSONObject(response.toString());
        JSONArray objectarr = object.getJSONArray("character_list");
        
        return objectarr;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public static int getLV(JSONArray arr) throws JSONException{
        int character_level = arr.getJSONObject(0).getJSONObject("battle_rank").getInt("value");
        //System.out.println("LV: " + character_level);
        return character_level;
    }
    
    public static String getID(JSONArray arr) throws JSONException{
        String character_level = arr.getJSONObject(0).getString("character_id");
        //System.out.println("LV: " + character_level);
        return character_level;
    }
    
    public static String getNamex(JSONArray arr) throws JSONException{
        String character_level = arr.getJSONObject(0).getJSONObject("name").get("first").toString();
        //System.out.println("LV: " + character_level);
        return character_level;
    }
    
    public static float getKDR(JSONArray arr) throws JSONException{
        int deaths = arr.getJSONObject(0).getJSONObject("stats").getJSONArray("stat_history").getJSONObject(2).getInt("all_time");
        int kills = arr.getJSONObject(0).getJSONObject("stats").getJSONArray("stat_history").getJSONObject(5).getInt("all_time");
        
        float kdr = (float)kills/deaths;
        DecimalFormat twoSF = new DecimalFormat("#.###");
        kdr = Float.valueOf(twoSF.format(kdr));
        
        return kdr;
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        //JSONArray response = getPlayerInfo("pizzahutdeliveryman");
        
        /*int deaths = response.getJSONObject(0).getJSONObject("stats").getJSONArray("stat_history").getJSONObject(2).getInt("all_time");
        int kills = response.getJSONObject(0).getJSONObject("stats").getJSONArray("stat_history").getJSONObject(5).getInt("all_time");
        System.out.println(kills + " " + deaths);
        
        float kdr = (float)kills/deaths;
        DecimalFormat twoSF = new DecimalFormat("#.##");
        kdr = Float.valueOf(twoSF.format(kdr));
        System.out.println("KDR: " + kdr);*/
        
        /* String character_name = character_list.getJSONObject(0).getJSONObject("name").get("first").toString();
        System.out.println("");
        System.out.println("Name: " + character_name);
        
        String character_id = character_list.getJSONObject(0).getString("character_id");
        System.out.println("ID: " + character_id);
        
        int character_level = character_list.getJSONObject(0).getJSONObject("battle_rank").getInt("value");
        System.out.println("LV: " + character_level);*/
        
        //System.out.println(getLV(response));
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new planetSide2frame().setVisible(true);
            }
        });
    }
    
    
}
