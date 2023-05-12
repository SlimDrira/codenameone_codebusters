package com.myway.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.myway.entities.Contrat;
import com.myway.entities.Location;
import com.myway.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContratService {

    public static ContratService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Contrat> listContrats;


    private ContratService() {
        cr = new ConnectionRequest();
    }

    public static ContratService getInstance() {
        if (instance == null) {
            instance = new ContratService();
        }
        return instance;
    }

    public ArrayList<Contrat> getAll() {
        listContrats = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/contrat");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listContrats = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listContrats;
    }

    private ArrayList<Contrat> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Contrat contrat = new Contrat(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        makeLocation((Map<String, Object>) obj.get("location")),
                        Float.parseFloat(obj.get("prix").toString()),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateDebut")),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateFin"))
                );

                listContrats.add(contrat);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listContrats;
    }

    public Location makeLocation(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Location location = new Location();
        location.setId((int) Float.parseFloat(obj.get("id").toString()));
        location.setNom((String) obj.get("nom"));
        return location;
    }

    public int add(Contrat contrat) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/contrat/add");

        cr.addArgument("location", String.valueOf(contrat.getLocation().getId()));
        cr.addArgument("prix", String.valueOf(contrat.getPrix()));
        cr.addArgument("dateDebut", new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateDebut()));
        cr.addArgument("dateFin", new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateFin()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int edit(Contrat contrat) {

        cr = new ConnectionRequest();
        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/contrat/edit");
        cr.addArgument("id", String.valueOf(contrat.getId()));

        cr.addArgument("location", String.valueOf(contrat.getLocation().getId()));
        cr.addArgument("prix", String.valueOf(contrat.getPrix()));
        cr.addArgument("dateDebut", new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateDebut()));
        cr.addArgument("dateFin", new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateFin()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int contratId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/contrat/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(contratId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
