/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entities.Etablissement;
import Entities.Trajet;
import Statics.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
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
public class ServiceEtablissement {

    public Etablissement e = new Etablissement();
    public ArrayList<Etablissement> etablissements;
    public static ServiceEtablissement instance = null;
    private ConnectionRequest req;

    private ServiceEtablissement() {
        req = new ConnectionRequest();
    }

    public static ServiceEtablissement getInstance() {
        if (instance == null) {
            instance = new ServiceEtablissement();
        }
        return instance;
    }

    public ArrayList<Etablissement> findByTrajet(Trajet t) {
        String url = Statics.BASE_URL + "etablissement/listByTrajet/" + t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                try {
                    if (new String(req.getResponseData()).isEmpty()) {
                        etablissements = new ArrayList();
                    } else {
                        etablissements = parseEtablissements(new String(req.getResponseData()), t);
                    }
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return etablissements;
    }

    public ArrayList<Etablissement> parseEtablissements(String jsonText, Trajet t) throws ParseException {
        try {
            etablissements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> etablissementsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etablissementsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Etablissement etab = new Etablissement();

                float id = Float.parseFloat(obj.get("id").toString());
                etab.setId((int) id);

                etab.setNom(obj.get("nom").toString());
                etab.setType(obj.get("type").toString());
                etab.setDescription(obj.get("description").toString());
                etab.setAdresse(obj.get("adresse").toString());
                etab.setImage(obj.get("image").toString());
                etab.setEmail(obj.get("email").toString());
                etab.setHoraires(obj.get("horaires").toString());

                float views = Float.parseFloat(obj.get("views").toString());
                etab.setViews((int) views);

                float telephone = Float.parseFloat(obj.get("telephone").toString());
                etab.setTelephone((int) telephone);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date_creation = sdf.parse(obj.get("dateCreation").toString());
                etab.setDate_creation(date_creation);

                etab.setTrajet(t);

                etablissements.add(etab);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return etablissements;
    }
    
    //fonction zeyda kont chne5dem beha details etablissement
    public Etablissement parseEtablissement(String jsonText) throws ParseException {
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> etablissementJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            Etablissement etab = new Etablissement();

                float id = Float.parseFloat(etablissementJson.get("id").toString());
                etab.setId((int) id);

                etab.setNom(etablissementJson.get("nom").toString());
                etab.setType(etablissementJson.get("type").toString());
                etab.setDescription(etablissementJson.get("description").toString());
                etab.setAdresse(etablissementJson.get("adresse").toString());
                etab.setImage(etablissementJson.get("image").toString());
                etab.setEmail(etablissementJson.get("email").toString());
                etab.setHoraires(etablissementJson.get("horaires").toString());

                float views = Float.parseFloat(etablissementJson.get("views").toString());
                etab.setViews((int) views);

                float telephone = Float.parseFloat(etablissementJson.get("telephone").toString());
                etab.setTelephone((int) telephone);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date_creation = sdf.parse(etablissementJson.get("dateCreation").toString());
                etab.setDate_creation(date_creation);
                    
                etab.setTrajet(ServiceTrajet.getInstance().parseTrajet(etablissementJson.get("trajet").toString()));
                e = etab;
        } catch (IOException ex) {
                    System.out.println(ex.getMessage());        }
        return e;
    }
    
    public Image getImage(Etablissement e){
        EncodedImage ei;
        Image img;
        String url = Statics.BASE_URL + "etablissement/image/" + e.getImage();
        
        try {
            ei = EncodedImage.create("/load.png");
            img = URLImage.createToStorage(ei, url, url, URLImage.RESIZE_SCALE_TO_FILL);
            return img;
        } catch (IOException ex) {
            
        }
        
        return null;

    }
}
