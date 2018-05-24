/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class Preferences extends Parent
{

    private final Stage primaryStage;
    private final Controller controller;

    private final String police;
    private final CacheImage cacheImage;

    private final int height;
    private final int width;
    private final int tailleDeCase;
    private final int maxJoueur;
    private final int minJoueur;

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

    public Preferences(Stage _primaryStage, Controller _controller, CacheImage _cacheImage) {
        primaryStage = _primaryStage;
        controller = _controller;

        height = (int) primaryStage.getHeight();
        width = (int) primaryStage.getWidth();
        tailleDeCase = width / 8;
        maxJoueur = width / 2;
        minJoueur = maxJoueur / 2;

        cacheImage = _cacheImage;
        police = controller.getPolice();

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

        setObjetsGraphiques();
        setHandlers();
        Pane panePrincipale = placerObjetsGraphiques();

        this.getChildren().add(panePrincipale);
    }

    private void setObjetsGraphiques()
    {
        imageFond.setImage(cacheImage.getImage("PlateauCentral.png"));
        imageFond.setFitHeight((width - 30) / 1.35);
        imageFond.setFitWidth(width - 30);

        labelPreferences.setText(controller.gestionnaireLangage.getText("text_preference"));
        labelPreferences.setFont(new Font(police, maxJoueur / 10));
        labelPreferences.setTextFill(Color.web("#ffff66"));
        labelPreferences.setAlignment(Pos.CENTER);
        labelPreferences.setMinSize(minJoueur, 30);
        labelPreferences.setMaxSize(maxJoueur, 70);

        labelLangue.setText(controller.gestionnaireLangage.getText("text_langue"));
        labelLangue.setFont(new Font(police, maxJoueur / 14));
        labelLangue.setTextFill(Color.web("#ffff66"));
        labelLangue.setAlignment(Pos.CENTER);
        labelLangue.setMinSize(minJoueur, 30);
        labelLangue.setMaxSize(maxJoueur, 70);

        comboLangue.getItems().addAll(controller.gestionnaireLangage.getImplementedLanguagesString());
        comboLangue.setValue(controller.gestionnaireLangage.getCurrentLanguage().getDisplayName());

        labelAide.setText(controller.gestionnaireLangage.getText("text_activerAide"));
        labelAide.setFont(new Font(police, maxJoueur / 14));
        labelAide.setTextFill(Color.web("#ffff66"));
        labelAide.setAlignment(Pos.CENTER);
        labelAide.setMinSize(minJoueur, 30);
        labelAide.setMaxSize(maxJoueur, 70);

        checkBoxAide.setSelected(true);

        labelTheme.setText(controller.gestionnaireLangage.getText("text_theme"));
        labelTheme.setFont(new Font(police, maxJoueur / 14));
        labelTheme.setTextFill(Color.web("#ffff66"));
        labelTheme.setAlignment(Pos.CENTER);
        labelTheme.setMinSize(minJoueur, 30);
        labelTheme.setMaxSize(maxJoueur, 70);

        radioButtonJour.setText(controller.gestionnaireLangage.getText("text_jour"));
        radioButtonJour.setToggleGroup(groupRadioButtons);
        radioButtonNuit.setText(controller.gestionnaireLangage.getText("text_nuit"));
        radioButtonNuit.setToggleGroup(groupRadioButtons);
        if (controller.typeTheme.equals("Jour"))
        {
            radioButtonJour.setSelected(true);
        } else
        {
            radioButtonNuit.setSelected(true);
        }

        buttonValider.setText(controller.gestionnaireLangage.getText("text_valider"));
        buttonValider.setFont(new Font(police, width / 35));
        buttonValider.setMinHeight(20);

        Image imageQ = cacheImage.getImage("exit3.png");
        ImageView ImQ = new ImageView(imageQ);
        ImQ.setFitHeight(tailleDeCase / 2.5);
        ImQ.setFitWidth(tailleDeCase / 2.5);
        stackAnnuler.getChildren().add(ImQ);
    }

    private void setHandlers()
    {
        buttonValider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) ->
        {
            buttonValider.setEffect(new DropShadow());
        });
        buttonValider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event)
                -> {
            buttonValider.setEffect(null);
        });
        buttonValider.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event)
                -> {
            String nomLangue = comboLangue.getSelectionModel().getSelectedItem();
            boolean activerAide = checkBoxAide.isSelected();
            String textSelectedRadioButton = ((RadioButton) groupRadioButtons.getSelectedToggle()).getText();
            String nomTheme = textSelectedRadioButton.equals(controller.gestionnaireLangage.getText("text_jour")) ? "Jour" : "Nuit";
            controller.validerParametres(nomLangue, activerAide, nomTheme);
            setVisible(false);
        });

        stackAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            setVisible(false);
        });
    }

    private Pane placerObjetsGraphiques() {
        Pane panePrincipale = new Pane();
        panePrincipale.prefWidthProperty().bind(primaryStage.widthProperty());
        panePrincipale.prefHeightProperty().bind(primaryStage.heightProperty());

        AnchorPane p = new AnchorPane();
        p.prefWidthProperty().bind(panePrincipale.widthProperty());
        p.prefHeightProperty().bind(panePrincipale.heightProperty());
        AnchorPane.setTopAnchor(stackAnnuler, 10.0);
        AnchorPane.setRightAnchor(stackAnnuler, 10.0);


        GridPane gridPane = new GridPane();
        gridPane.prefWidthProperty().bind(p.widthProperty());
        gridPane.prefHeightProperty().bind(p.heightProperty());
        Outils.fixerRepartition(gridPane, Outils.HORIZONTAL, 20, 20, 20, 20, 20);
        Outils.fixerRepartition(gridPane, Outils.VERTICAL, 33, 33, 33);
        gridPane.setPadding(new Insets(30));

        gridPane.setAlignment(Pos.CENTER);

        BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bgIm = new BackgroundImage(imageFond.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
        Background bg = new Background(bgIm);
        gridPane.setBackground(bg);

        gridPane.add(labelPreferences, 0, 0, 3, 1);
        GridPane.setHalignment(labelPreferences, HPos.CENTER);

        gridPane.add(labelLangue, 0, 1);
        gridPane.add(comboLangue, 1, 1);

        gridPane.add(labelAide, 0, 2);
        gridPane.add(checkBoxAide, 1, 2);

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
