package com.myway.gui.front.location;


import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.myway.entities.Location;
import com.myway.services.LocationService;
import com.myway.utils.Statics;

import java.util.ArrayList;

public class AfficherToutLocation extends Form {

    Form previous;

    Resources theme = UIManager.initFirstTheme("/theme");

    public static Location currentLocation = null;

    TextField searchTF;
    ArrayList<Component> componentModels;


    public AfficherToutLocation(Form previous) {
        super("Locations", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        ArrayList<Location> listLocations = LocationService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher location par Nom");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Location location : listLocations) {
                if (location.getNom().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeLocationModel(location);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);


        if (listLocations.size() > 0) {
            for (Location location : listLocations) {
                Component model = makeLocationModel(location);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {

    }

    Label nomLabel, disponibiliteLabel, typeLabel, descriptionLabel, imageLabel;

    ImageViewer imageIV;


    private Container makeModelWithoutButtons(Location location) {
        Container locationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        locationModel.setUIID("containerRounded");


        nomLabel = new Label("Nom : " + location.getNom());
        nomLabel.setUIID("labelDefault");

        disponibiliteLabel = new Label("Disponibilite : " + (location.getDisponibilite() == 1 ? "True" : "False"));
        disponibiliteLabel.setUIID("labelDefault");

        typeLabel = new Label("Type : " + location.getType());
        typeLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + location.getDescription());
        descriptionLabel.setUIID("labelDefault");

        imageLabel = new Label("Image : " + location.getImage());
        imageLabel.setUIID("labelDefault");

        if (location.getImage() != null) {
            String url = Statics.LOCATION_IMAGE_URL + location.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);

        locationModel.addAll(
                imageIV,
                nomLabel, disponibiliteLabel, typeLabel, descriptionLabel
        );

        return locationModel;
    }


    private Component makeLocationModel(Location location) {

        Container locationModel = makeModelWithoutButtons(location);


        return locationModel;
    }

}