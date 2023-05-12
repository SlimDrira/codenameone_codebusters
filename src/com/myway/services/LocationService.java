package com.myway.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.myway.entities.Location;
import com.myway.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationService {

    public static LocationService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Location> listLocations;


    private LocationService() {
        cr = new ConnectionRequest();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public ArrayList<Location> getDispo() {
        listLocations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/location/dispo");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listLocations = getList();
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

        return listLocations;
    }

    public ArrayList<Location> getAll() {
        listLocations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/location");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listLocations = getList();
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

        return listLocations;
    }

    private ArrayList<Location> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                int disponibilite = 0;
                if (obj.get("disponibilite").toString().equals("true")) disponibilite = 1;

                Location location = new Location(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("nom"),
                        disponibilite,
                        (String) obj.get("type"),
                        (String) obj.get("description"),
                        (String) obj.get("image")

                );

                listLocations.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLocations;
    }

    public int add(Location location) {

        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Location.jpg");


        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/location/add");

        try {
            cr.addData("file", location.getImage(), "image/jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cr.addArgumentNoEncoding("image", location.getImage());

        cr.addArgumentNoEncoding("nom", location.getNom());
        cr.addArgumentNoEncoding("disponibilite", String.valueOf(location.getDisponibilite()));
        cr.addArgumentNoEncoding("type", location.getType());
        cr.addArgumentNoEncoding("description", location.getDescription());
        cr.addArgumentNoEncoding("image", location.getImage());


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

    public int edit(Location location, boolean imageEdited) {

        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Location.jpg");

        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/location/edit");
        cr.addArgumentNoEncoding("id", String.valueOf(location.getId()));

        if (imageEdited) {
            try {
                cr.addData("file", location.getImage(), "image/jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cr.addArgumentNoEncoding("image", location.getImage());
        }

        cr.addArgumentNoEncoding("nom", location.getNom());
        cr.addArgumentNoEncoding("disponibilite", String.valueOf(location.getDisponibilite()));
        cr.addArgumentNoEncoding("type", location.getType());
        cr.addArgumentNoEncoding("description", location.getDescription());
        cr.addArgumentNoEncoding("image", location.getImage());


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

    public int delete(int locationId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/location/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(locationId));

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
