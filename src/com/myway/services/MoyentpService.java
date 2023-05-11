package com.myway.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.myway.entities.Moyentp;
import com.myway.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoyentpService {

    public static MoyentpService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Moyentp> listMoyentps;

    private MoyentpService() {
        cr = new ConnectionRequest();
    }

    public static MoyentpService getInstance() {
        if (instance == null) {
            instance = new MoyentpService();
        }
        return instance;
    }

    public ArrayList<Moyentp> getAll() {
        listMoyentps = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/moyentp");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listMoyentps = getList();
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

        return listMoyentps;
    }

    private ArrayList<Moyentp> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Moyentp moyentp = new Moyentp(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        (String) obj.get("matricule"),
                        (String) obj.get("type"),
                        (int) Float.parseFloat(obj.get("nbreplace").toString()),
                        Float.parseFloat(obj.get("prixTicket").toString()),
                        (String) obj.get("horaire"),
                        (String) obj.get("nom"),
                        (int) Float.parseFloat(obj.get("note").toString())
                );

                listMoyentps.add(moyentp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listMoyentps;
    }

    public int add(Moyentp moyentp) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/moyentp/add");

        cr.addArgument("matricule", moyentp.getMatricule());
        cr.addArgument("type", moyentp.getType());
        cr.addArgument("nbreplace", String.valueOf(moyentp.getNbreplace()));
        cr.addArgument("prixTicket", String.valueOf(moyentp.getPrixTicket()));
        cr.addArgument("horaire", moyentp.getHoraire());
        cr.addArgument("nom", moyentp.getNom());
        cr.addArgument("note", String.valueOf(moyentp.getNote()));

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

    public int edit(Moyentp moyentp) {

        cr = new ConnectionRequest();
        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/moyentp/edit");
        cr.addArgument("id", String.valueOf(moyentp.getId()));

        cr.addArgument("matricule", moyentp.getMatricule());
        cr.addArgument("type", moyentp.getType());
        cr.addArgument("nbreplace", String.valueOf(moyentp.getNbreplace()));
        cr.addArgument("prixTicket", String.valueOf(moyentp.getPrixTicket()));
        cr.addArgument("horaire", moyentp.getHoraire());
        cr.addArgument("nom", moyentp.getNom());
        cr.addArgument("note", String.valueOf(moyentp.getNote()));

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

    public int delete(int moyentpId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/moyentp/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(moyentpId));

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
