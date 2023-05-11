package com.myway.gui.front.moyentp;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.myway.entities.Moyentp;

import com.myway.services.MoyentpService;

import java.util.ArrayList;

public class AfficherToutMoyentp extends Form {

    Form previous;

    public static Moyentp currentMoyentp = null;
    Button showMapsBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;

    public AfficherToutMoyentp(Form previous) {
        super("Moyentps", new BoxLayout(BoxLayout.Y_AXIS));
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

        ArrayList<Moyentp> listMoyentps = MoyentpService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher moyentp par Matricule");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Moyentp moyentp : listMoyentps) {
                if (moyentp.getMatricule().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeMoyentpModel(moyentp);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);

        if (listMoyentps.size() > 0) {
            for (Moyentp moyentp : listMoyentps) {
                Component model = makeMoyentpModel(moyentp);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {

    }

    Label matriculeLabel, typeLabel, nbreplaceLabel, prixTicketLabel, horaireLabel, nomLabel, noteLabel;

    private Container makeModelWithoutButtons(Moyentp moyentp) {
        Container moyentpModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        moyentpModel.setUIID("containerRounded");

        matriculeLabel = new Label("Matricule : " + moyentp.getMatricule());
        matriculeLabel.setUIID("labelDefault");

        typeLabel = new Label("Type : " + moyentp.getType());
        typeLabel.setUIID("labelDefault");

        nbreplaceLabel = new Label("Nbreplace : " + moyentp.getNbreplace());
        nbreplaceLabel.setUIID("labelDefault");

        prixTicketLabel = new Label("PrixTicket : " + moyentp.getPrixTicket());
        prixTicketLabel.setUIID("labelDefault");

        horaireLabel = new Label("Horaire : " + moyentp.getHoraire());
        horaireLabel.setUIID("labelDefault");

        nomLabel = new Label("Nom : " + moyentp.getNom());
        nomLabel.setUIID("labelDefault");

        noteLabel = new Label("Note : " + moyentp.getNote());
        noteLabel.setUIID("labelDefault");

        Button[] stars = {new Button(), new Button(), new Button(), new Button(), new Button()};

        Container starsContainer = new Container();
        starsContainer.add(noteLabel);
        for (int indexEtoile = 0; indexEtoile < 5; indexEtoile++) {
            int i = indexEtoile;

            if (i >= moyentp.getNote()) {
                stars[i].setUIID("starButtonOutlined");
            } else {
                stars[i].setUIID("starButton");
            }

            stars[i].addActionListener(action -> {
                setStars(i + 1, stars);
                moyentp.setNote(i + 1);
                MoyentpService.getInstance().edit(moyentp);
                this.refresh();
            });
            
            stars[i].setPreferredH(100);
            stars[i].setPreferredW(100);
            starsContainer.add(stars[i]);

        }

        showMapsBtn = new Button("ShowMaps");
        showMapsBtn.addActionListener(actionEvent -> {
            new com.mycompany.gui.MapForm2().show();
        });

        moyentpModel.addAll(
                matriculeLabel, typeLabel, nbreplaceLabel, prixTicketLabel, horaireLabel, nomLabel, starsContainer, showMapsBtn
        );

        return moyentpModel;
    }

    private void setStars(int nb, Button[] stars) {
        for (int i = 0; i < 5; i++) {
            if (i < nb) {
                stars[i].setUIID("starButton");
            } else {
                stars[i].setUIID("starButtonOutlined");
            }
        }
        this.refreshTheme();
    }

    private Component makeMoyentpModel(Moyentp moyentp) {

        Container moyentpModel = makeModelWithoutButtons(moyentp);

        return moyentpModel;
    }

}
