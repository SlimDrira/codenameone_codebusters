/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Etablissement;
import Entities.Trajet;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import services.ServiceEtablissement;
import gui.MapForm;


/**
 *
 * @author 9naydel
 */
public class DetailsTrajetForm extends Form {
    public DetailsTrajetForm(Trajet t){
        this.setTitle(t.getDepart()+" - "+t.getDestination());
        this.setLayout(BoxLayout.y());
        Label lEtat = new Label("Etat: ");
        SpanLabel slEtat = new SpanLabel(t.getEtat());
        Label lDirections = new Label("Directions: ");
        SpanLabel slDirections = new SpanLabel(t.getDirections());
        Label lDistance = new Label("Distance: "+ t.getDistance()+ " km");
        Button btnEtablissements = new Button("Voir Etablissements");
        Button btnShowMap = new Button("Voir le map");
        
        btnShowMap.addActionListener((ebtnbtnShowMap)->{
            new MapForm(t);
        });
        
        
        btnEtablissements.addActionListener((ebtnEtab)->{
            new ListEtablissementForm(t).show();
        });
        
        this.addAll(lEtat, slEtat,lDirections, slDirections, lDistance, btnEtablissements, btnShowMap);
        
        this.getToolbar().addCommandToLeftBar("Back", null, (e)->{new SearchTrajetForm().showBack();});
        
    }
    
}
