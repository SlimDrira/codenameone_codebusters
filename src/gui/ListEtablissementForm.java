/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Etablissement;
import Entities.Trajet;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceEtablissement;

/**
 *
 * @author 9naydel
 */
public class ListEtablissementForm extends Form {
    public ListEtablissementForm(Trajet t){
        this.setTitle("Etablissement");
        this.setLayout(BoxLayout.y());
        
        for(Etablissement e : ServiceEtablissement.getInstance().findByTrajet(t)){
            Container cn1 = new Container(BoxLayout.y());
            Label lNom = new Label(e.getNom());
            Label lType = new Label(e.getType());
            cn1.addAll(lNom, lType);
            
            Container cn2 = new Container(BoxLayout.x());
            ImageViewer iv = new ImageViewer(ServiceEtablissement.getInstance().getImage(e).scaled(240, 240));
            cn2.add(iv);
            cn2.add(cn1);
            
            lNom.addPointerPressedListener((eLNom)->{
                new DetailsEtablissementForm(e).show();
            });
            
            cn2.setLeadComponent(lNom);
            
            this.add(cn2);        
        }
        this.getToolbar().addCommandToLeftBar("Back", null, (e)->{new DetailsTrajetForm(t).showBack();});
        
        
    }
    
}
