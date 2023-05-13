/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entities.Trajet;
import Statics.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Date;
import java.util.Map;

/**
 *
 * @author 9naydel
 */
public class ServiceTrajet {

    public Trajet t = new Trajet();
    public static ServiceTrajet instance = null;
    private ConnectionRequest req;

    private ServiceTrajet() {
        req = new ConnectionRequest();
    }

    public static ServiceTrajet getInstance() {
        if (instance == null) {
            instance = new ServiceTrajet();
        }
        return instance;
    }

    public Trajet findByDepartAndDestination(String depart, String destination) {

        String url = Statics.BASE_URL + "trajet/search";
        
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("depart", depart);
        req.addArgument("destination", destination);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   if (new String(req.getResponseData()).isEmpty()){
                       t = new Trajet();
                   }else{
                       t = parseTrajet(new String(req.getResponseData()));
                   }
                    
                
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
    }

    public Trajet parseTrajet(String jsonText) throws ParseException {
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> trajetJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            Trajet trajet = new Trajet();
            float id = Float.parseFloat(trajetJson.get("id").toString());
            
            trajet.setId((int) id);
            trajet.setDepart(trajetJson.get("depart").toString());
            trajet.setDestination(trajetJson.get("destination").toString());
            trajet.setEtat(trajetJson.get("etat").toString());
            trajet.setDirections(trajetJson.get("directions").toString());
            
            float latitude = Float.parseFloat(trajetJson.get("latitude").toString());
            trajet.setLatitude(latitude);
            float longitude = Float.parseFloat(trajetJson.get("longitude").toString());
            trajet.setLongitude(longitude);
            float distance = Float.parseFloat(trajetJson.get("distance").toString());
            trajet.setDistance(distance);
            
            float views = Float.parseFloat(trajetJson.get("views").toString());
            trajet.setViews((int) views);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date_creation = sdf.parse(trajetJson.get("dateCreation").toString());
            trajet.setDate_creation(date_creation);
            t = trajet;
        } catch (IOException ex) {
                    System.out.println(ex.getMessage());        }
        return t;
    }

}
