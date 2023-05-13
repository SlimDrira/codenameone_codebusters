/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Rating;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceRating;

/**
 *
 * @author 9naydel
 */
public class ModifyRatingForm extends Form {

    public ModifyRatingForm(Rating r) {
        this.setTitle("Modify Rating");
        this.setLayout(BoxLayout.y());

        ComboBox<Integer> combo = new ComboBox<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Button btn = new Button("Valider");

        btn.addActionListener((ev) -> {
            r.setNote(combo.getSelectedItem());
            ServiceRating.getInstance().modifyRating(r);
            if (Dialog.show("Success", "Rating modifié avec succès", "Ok", null)) {
                new ListRatingsForm(r.getEtablissement()).showBack();
            }

        });

        this.addAll(combo, btn);
        this.getToolbar().addCommandToLeftBar("Back", null, (e) -> {
            new ListRatingsForm(r.getEtablissement()).showBack();
        });

    }
}
