/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Etablissement;
import Entities.Rating;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import services.ServiceEtablissement;
import services.ServiceRating;

/**
 *
 * @author 9naydel
 */
public class DetailsEtablissementForm extends Form {

    public DetailsEtablissementForm(Etablissement e) {
        this.setTitle(e.getNom());
        this.setLayout(BoxLayout.y());

        ImageViewer iv = new ImageViewer(ServiceEtablissement.getInstance().getImage(e).scaledHeight(800));

        Label lType = new Label("Type: " + e.getType());
        Label lAdresse = new Label("Adresse: ");
        SpanLabel slAdresse = new SpanLabel(e.getAdresse());
        Label lDescription = new Label("Description: ");
        SpanLabel slDescription = new SpanLabel(e.getDescription());
        Label lTelephone = new Label("Telephone: " + e.getTelephone());
        Label lEmail = new Label("Email: " + e.getEmail());
        Label lHoraires = new Label("Horaires: " + e.getHoraires());
        Label lRating;

        ArrayList<Rating> ratings = ServiceRating.getInstance().findByEtablissement(e);

        if (ratings.isEmpty()) {
            lRating = new Label("Rating: Pas de rating pour le moment");
        } else {
            float moyRating = 0;
            for (Rating r : ratings) {
                moyRating += r.getNote();
            }
            lRating = new Label("Rating: " + (double) Math.round(moyRating/ratings.size() * 10) / 10 + "/10");
        }

        Button btnSeeRatings = new Button("See all ratings");

        btnSeeRatings.addActionListener((ebtnEtab) -> {
            new ListRatingsForm(e).show();
        });

        this.addAll(iv, lType, lAdresse, slAdresse, lDescription, slDescription, lTelephone, lEmail, lHoraires, lRating, btnSeeRatings);

        this.getToolbar().addCommandToLeftBar("Back", null, (ev) -> {
            new ListEtablissementForm(e.getTrajet()).showBack();
        });

    }

}
