/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Adeline
 */
public class Preferences extends Parent
{

    private final Scene scene;
    private final Controller controller;

    private final String police;
    private final CacheImage cacheImage;

    private final ComboBox<String> comboLangue;
    private final ImageView imageFond;
    private final Label labelPreferences;
    private final Label labelLangue;
    private final Label labelAide;
    private final CheckBox checkBoxAide;
    private final Label labelTheme;
    private final ToggleGroup groupRadioButtons;
    private final RadioButton radioButtonNuit;
    private final RadioButton radioButtonJour;
    private final Button buttonValider;
    private final StackPane stackAnnuler;
    private final DoubleProperty fontSize = new SimpleDoubleProperty(10);

    public Preferences(Scene _scene, Controller _controller, CacheImage _cacheImage)
    {
        scene = _scene;
        controller = _controller;


        cacheImage = _cacheImage;
        police = "Papyrus";

        fontSize.bind(scene.heightProperty().divide(25));
        this.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";",
                "-fx-font-family: ", police, ";"));
        imageFond = new ImageView();
        labelPreferences = new Label();
        labelLangue = new Label();
        comboLangue = new ComboBox<>();
        labelAide = new Label();
        checkBoxAide = new CheckBox();
        labelTheme = new Label();
        groupRadioButtons = new ToggleGroup();
        radioButtonNuit = new RadioButton();
        radioButtonJour = new RadioButton();
        buttonValider = new Button();
        stackAnnuler = new StackPane();

        setTextWithCurrentLanguage();
        setObjetsGraphiques();
        setHandlers();
        Pane panePrincipale = placerObjetsGraphiques();

        this.getChildren().add(panePrincipale);
    }

    private void setObjetsGraphiques()
    {
        imageFond.setImage(cacheImage.getImage("Design/FenetrePlateau/fond.jpg"));
        

        labelPreferences.setTextFill(Color.web("#ffff66"));
        labelPreferences.setAlignment(Pos.CENTER);

        labelLangue.setTextFill(Color.web("#ffff66"));
        labelLangue.setAlignment(Pos.CENTER);

        comboLangue.getItems().addAll(controller.gestionnaireLangage.getImplementedLanguagesString());

        labelAide.setTextFill(Color.web("#ffff66"));
        labelAide.setAlignment(Pos.CENTER);

        checkBoxAide.setSelected(controller.settingsGesture.getSetting("aide").equals("true"));

        labelTheme.setTextFill(Color.web("#ffff66"));
        labelTheme.setAlignment(Pos.CENTER);

        radioButtonJour.setToggleGroup(groupRadioButtons);
        radioButtonNuit.setToggleGroup(groupRadioButtons);
        if (controller.settingsGesture.getSetting("theme").equals("Jour"))
        {
            radioButtonJour.setSelected(true);
        } else
        {
            radioButtonNuit.setSelected(true);
        }

        buttonValider.setMinHeight(20);
        
        Image imageQ = cacheImage.getImage("exit3.png");
        ImageView ImQ = new ImageView(imageQ);
        ImQ.setFitHeight(40);
        ImQ.setPreserveRatio(true);
        stackAnnuler.getChildren().add(ImQ);
    }

    private void setTextWithCurrentLanguage()
    {
        labelPreferences.setText(controller.gestionnaireLangage.getText("text_preference"));
        labelLangue.setText(controller.gestionnaireLangage.getText("text_langue"));
        comboLangue.setValue(controller.settingsGesture.getSetting("langue"));
        labelAide.setText(controller.gestionnaireLangage.getText("text_activerAide"));
        labelTheme.setText(controller.gestionnaireLangage.getText("text_theme"));
        radioButtonJour.setText(controller.gestionnaireLangage.getText("text_jour"));
        radioButtonNuit.setText(controller.gestionnaireLangage.getText("text_nuit"));
        buttonValider.setText(controller.gestionnaireLangage.getText("text_valider"));
    }

    private void setHandlers()
    {
        buttonValider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) ->
        {
            buttonValider.setEffect(new DropShadow());
        });
        buttonValider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) ->
        {
            buttonValider.setEffect(null);
        });
        buttonValider.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            String nomLangue = comboLangue.getSelectionModel().getSelectedItem();
            boolean activerAide = checkBoxAide.isSelected();
            String textSelectedRadioButton = ((RadioButton) groupRadioButtons.getSelectedToggle()).getText();
            String nomTheme = textSelectedRadioButton.equals(controller.gestionnaireLangage.getText("text_jour")) ? "Jour" : "Nuit";
            controller.validerParametres(nomLangue, activerAide, nomTheme);
            setTextWithCurrentLanguage();
            setVisible(false);
        });

        stackAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            setVisible(false);
        });
    }

    private Pane placerObjetsGraphiques()
    {
        Pane panePrincipale = new Pane();
       
        panePrincipale.prefWidthProperty().bind(scene.widthProperty());
        panePrincipale.prefHeightProperty().bind(scene.heightProperty());

        AnchorPane p = new AnchorPane();
        p.prefWidthProperty().bind(scene.widthProperty());
        p.prefHeightProperty().bind(scene.heightProperty());
        AnchorPane.setTopAnchor(stackAnnuler, 10.0);
        AnchorPane.setRightAnchor(stackAnnuler, 10.0);
        p.setPadding(new Insets(30,30, 30, 30));

        GridPane gridPane = new GridPane();
        gridPane.prefWidthProperty().bind(scene.widthProperty());
        gridPane.prefHeightProperty().bind(scene.heightProperty());
        Outils.fixerRepartition(gridPane, Outils.HORIZONTAL, 20, 20, 20, 20, 20);
        Outils.fixerRepartition(gridPane, Outils.VERTICAL, 33, 33, 33);
        gridPane.setPadding(new Insets(30));

        gridPane.setAlignment(Pos.CENTER);

        BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgIm = new BackgroundImage(imageFond.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
        Background bg = new Background(bgIm);
        gridPane.setBackground(bg);

        gridPane.add(labelPreferences, 0, 0, 3, 1);
        GridPane.setHalignment(labelPreferences, HPos.CENTER);

        labelLangue.setAlignment(Pos.TOP_LEFT);
        gridPane.add(labelLangue, 0, 1);
        gridPane.add(comboLangue, 1, 1);

        labelAide.setAlignment(Pos.TOP_LEFT);
        gridPane.add(labelAide, 0, 2);
        gridPane.add(checkBoxAide, 1, 2);

        labelTheme.setAlignment(Pos.TOP_LEFT);
        gridPane.add(labelTheme, 0, 3);
        gridPane.add(radioButtonJour, 1, 3);
        gridPane.add(radioButtonNuit, 2, 3);

        GridPane.setHalignment(buttonValider, HPos.CENTER);
        gridPane.add(buttonValider, 0, 4, 3, 1);

        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);

        p.getChildren().add(gridPane);
        p.getChildren().add(stackAnnuler);
        panePrincipale.getChildren().add(p);

        return panePrincipale;
    }
}
