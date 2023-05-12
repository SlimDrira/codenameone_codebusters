package com.myway.gui.front.contrat;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class AccueilFront extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    Form previous;
    public static Form accueilForm;

    public AccueilFront(Form previous) {
        super("Menu", new BorderLayout());
        this.previous = previous;
        accueilForm = this;
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Espace utilisateur"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");

        Tabs tabs = new Tabs();
        tabs.addTab("Locations", FontImage.MATERIAL_MONEY, 5, new com.myway.gui.front.location.AfficherToutLocation(previous));
        tabs.addTab("Contrats", FontImage.MATERIAL_BOOK, 5, new com.myway.gui.front.contrat.AfficherToutContrat(previous));

        this.add(BorderLayout.CENTER, tabs);
    }
}
