package com.myway.gui.front.contrat;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.myway.entities.Contrat;
import com.myway.services.ContratService;
import com.myway.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class AfficherToutContrat extends Form {

    Form previous;

    PickerComponent sortPicker;
    ArrayList<Component> componentModels;

    public AfficherToutContrat(Form previous) {
        super("Contrats", new BoxLayout(BoxLayout.Y_AXIS));
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
        ArrayList<Contrat> listContrats = ContratService.getInstance().getAll();

        componentModels = new ArrayList<>();

        sortPicker = PickerComponent.createStrings("Location", "Prix", "DateDebut", "DateFin").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listContrats);
            for (Contrat contrat : listContrats) {
                Component model = makeContratModel(contrat);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);

        if (listContrats.size() > 0) {
            for (Contrat contrat : listContrats) {
                Component model = makeContratModel(contrat);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
    }

    Label locationLabel, prixLabel, dateDebutLabel, dateFinLabel;

    Calendar calendar;

    private Container makeModelWithoutButtons(Contrat contrat) {
        Container contratModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contratModel.setUIID("containerRounded");


        locationLabel = new Label("Location : " + contrat.getLocation());
        locationLabel.setUIID("labelDefault");

        prixLabel = new Label("Prix : " + contrat.getPrix());
        prixLabel.setUIID("labelDefault");

        dateDebutLabel = new Label("DateDebut : " + new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateDebut()));
        dateDebutLabel.setUIID("labelDefault");

        dateFinLabel = new Label("DateFin : " + new SimpleDateFormat("dd-MM-yyyy").format(contrat.getDateFin()));
        dateFinLabel.setUIID("labelDefault");

        locationLabel = new Label("Location : " + contrat.getLocation().getNom());
        locationLabel.setUIID("labelDefault");


        if (contrat.getDateDebut() != null && contrat.getDateFin() != null) {
            calendar = new Calendar();
            calendar.setFocusable(false);
            calendar.setDate(contrat.getDateDebut());
            calendar.highlightDate(contrat.getDateDebut(), "dateStart");
            calendar.highlightDate(contrat.getDateFin(), "dateEnd");
        }

        contratModel.addAll(
                locationLabel, prixLabel, dateDebutLabel, dateFinLabel
        );

        if (contrat.getDateDebut() != null && contrat.getDateFin() != null) {
            contratModel.add(calendar);
        }

        return contratModel;
    }


    private Component makeContratModel(Contrat contrat) {


        return makeModelWithoutButtons(contrat);
    }

}