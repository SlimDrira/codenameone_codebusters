/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Etablissement;
import Entities.Rating;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
public class ListRatingsForm extends Form {

    public ListRatingsForm(Etablissement e) {
        this.setTitle("Ratings");
        this.setLayout(BoxLayout.y());

        ArrayList<Rating> ratings = ServiceRating.getInstance().findByEtablissement(e);
        if (ratings.isEmpty()) {
            this.add(new Label("Rating: Pas de ratings pour le moment"));
        } else {
            int i = 1;
            for (Rating r : ratings) {
                Container cn1 = new Container(BoxLayout.y());
                Label lNom = new Label("Rating n° " + i);
                i++;
                Label lNote = new Label("note: " + String.valueOf(r.getNote()));
                cn1.addAll(lNom, lNote);

                Button btnModifier = new Button("Modifier");
                Button btnSupprimer = new Button("Supprimer");

                btnModifier.addActionListener((eMod) -> {
                    new ModifyRatingForm(r).show();
                });

                btnSupprimer.addActionListener((eSupp) -> {
                    if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce rating", "Yes", "Cancel")) {
                        ServiceRating.getInstance().deleteRating(r);
                        new ListRatingsForm(e).showBack();
                    }

                });

                Container cn2 = new Container(BoxLayout.x());

                cn2.addAll(cn1, btnModifier, btnSupprimer);
                this.add(cn2);
            }

        }

        this.getToolbar().addCommandToLeftBar("Back", null, (ev) -> {
            new DetailsEtablissementForm(e).showBack();
        });
        this.getToolbar().addCommandToOverflowMenu("Add Rating", null, evAdd -> {
            Rating ra = new Rating();
            ra.setEtablissement(e);
            new AddRatingForm(ra).show();
        });
    }

}
