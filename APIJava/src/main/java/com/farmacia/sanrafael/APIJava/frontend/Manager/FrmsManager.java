package com.farmacia.sanrafael.APIJava.frontend.Manager;
import com.farmacia.sanrafael.APIJava.frontend.App;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import javax.swing.*;
import java.awt.*;

public class FrmsManager {

    private App app;

    private static FrmsManager instance;
    public static FrmsManager getInstance() {
        if (instance == null) {
            instance = new FrmsManager();
        }
        return instance;
    }

    private FrmsManager() {

    }

    public void initApp(App app) {
        this.app = app;
    }
    public void showForm(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            app.setContentPane(form);
            app.revalidate();
            app.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }
}
