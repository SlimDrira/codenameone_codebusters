/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Entities.Trajet;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceTrajet;

/**
 *
 * @author 9naydel
 */
public class SearchTrajetForm extends Form {
    public SearchTrajetForm(){
        this.setTitle("Search Trajet");
        this.setLayout(BoxLayout.y());
        TextField tfDepart = new TextField(null,"Depart");
        TextField tfDestination = new TextField(null,"Destination");
        Button btnSearch = new Button("Search");
        btnSearch.addActionListener(e->{
            if(tfDepart.getText().isEmpty() || tfDestination.getText().isEmpty()){
                Dialog.show("Alert", "Veuiller remplir tous les champs", "ok", null);
            }else{
                
                Trajet t = ServiceTrajet.getInstance().findByDepartAndDestination(tfDepart.getText(), tfDestination.getText());
            if(t.getId() == 0){
                Dialog.show("Oops", "Ce trajet n'existe pas", "OK", null);
            }else{
                new DetailsTrajetForm(t).show();
            }
                
            }
            
            
        });
        this.addAll(tfDepart, tfDestination, btnSearch);
    }
    
}
