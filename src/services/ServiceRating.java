/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entities.Etablissement;
import Entities.Rating;
import Entities.Trajet;
import Statics.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 9naydel
 */
public class ServiceRating {
    boolean resultOK;
    public Rating e = new Rating();
    public ArrayList<Rating> ratings;
    public static ServiceRating instance = null;
    private ConnectionRequest req;
    
    private ServiceRating() {
        req = new ConnectionRequest();
    }

    public static ServiceRating getInstance() {
        if (instance == null) {
            instance = new ServiceRating();
        }
        return instance;
    }
    
    public ArrayList<Rating> findByEtablissement(Etablissement e) {
        String url = Statics.BASE_URL + "rating/listByEtablissement/" + e.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                try {
                    if (new String(req.getResponseData()).isEmpty()) {
                        ratings = new ArrayList();
                    } else {
                        ratings = parseRatings(new String(req.getResponseData()), e);
                    }
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ratings;
    }
    
    public ArrayList<Rating> parseRatings(String jsonText, Etablissement e) throws ParseException {
        try {
            ratings = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ratingsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ratingsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Rating rating = new Rating();

                float id = Float.parseFloat(obj.get("id").toString());
                rating.setId((int) id);
                
                float note = Float.parseFloat(obj.get("note").toString());
                rating.setNote((int) note);

                rating.setEtablissement(e);

                ratings.add(rating);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return ratings;
    }
    
    public boolean addRating(Rating r) {
        
        String url = Statics.BASE_URL + "rating/add/";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("id", Integer.toString(r.getEtablissement().getId()));
        req.addArgument("note", Integer.toString(r.getNote()));
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean modifyRating(Rating r) {
        
        String url = Statics.BASE_URL + "rating/modify/"+r.getId();

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("note", Integer.toString(r.getNote()));
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean deleteRating(Rating r) {
        
        String url = Statics.BASE_URL + "rating/delete/"+r.getId();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
}
