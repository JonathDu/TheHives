/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import hive.thehives.TheHives;

/**
 *
 * @author Adeline
 */
public class InterfaceJoueurs extends Parent{

    String versionIA1;
    String versionIA2;
    int est_h1=0, est_h2=0, est_ai1=0, est_ai2=0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();
    public InterfaceJoueurs(int height, int width, Stage primaryStage, TheHives i) {

        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;


        InterfaceUtiles u = new InterfaceUtiles(height, width, primaryStage, i);
        this.getChildren().add(u);


        //Image imageHumain = new Image("vue/rsc/images/humain1.png");
        //Image imageHumain = new Image("vue/rsc/images/humain2.png");
        //Image imageHumain = new Image("vue/rsc/images/humain3.png");
        //Image imageHumain = new Image("vue/rsc/images/humain4.png");
        Image imageHumain = new Image("vue/rsc/images/humain5.png");


        //Image imageIA = new Image("vue/rsc/images/ai1.png");
        Image imageIA = new Image("vue/rsc/images/ai2.png");

        Group J1 =new Group();
        Label joueur1 = new Label("Joueur 1");
        joueur1.setFont(new Font("Arial", tailleDeCase/3));
        joueur1.setAlignment(Pos.CENTER);
        joueur1.setMinSize(width/10, 30);
        joueur1.setMaxSize(width/5, 70);
        joueur1.setPrefSize(tailleDeCase/3*4, 50);
        joueur1.setLayoutX(width/2-tailleDeCase/3*2);
        joueur1.setLayoutY(height/6);
        this.getChildren().add(joueur1);
        final ToggleGroup j1 = new ToggleGroup();
        //ToggleButton humain1 = new RadioButton("Humain");
        ToggleButton humain1 = new RadioButton("");
        humain1.setUserData("Humain");
        ImageView humainIm1 = new ImageView(imageHumain);
        humainIm1.setFitHeight(width/10);
        humainIm1.setFitWidth(width/12);
        humain1.setGraphic(humainIm1);
        humain1.setFont(new Font("Arial", tailleDeCase/5));
        humain1.setLayoutX(width/2-width/4);
        humain1.setLayoutY(height/4);
        //humain1.setPrefSize(tailleDeCase/2, tailleDeCase/2);
        humain1.setToggleGroup(j1);
        //ToggleButton IA1 = new RadioButton("IA");
        ToggleButton IA1 = new RadioButton("");
        IA1.setUserData("IA");
        ImageView IAIm1 = new ImageView(imageIA);
        IAIm1.setFitHeight(width/10);
        IAIm1.setFitWidth(width/10);
        IA1.setGraphic(IAIm1);
        IA1.setFont(new Font("Arial", tailleDeCase/5));
        IA1.setLayoutX(width/2+width/4-width/10);
        IA1.setLayoutY(height/4);
        IA1.setToggleGroup(j1);

        Name1.setPromptText("Votre prenom");
        final ToggleGroup ia1 = new ToggleGroup();

        j1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j1.getSelectedToggle() != null) {
                    if(humain1.isSelected()){
                        if(est_ai1==1){
                            J1.getChildren().remove(J1.getChildren().size()-3, J1.getChildren().size());
                            est_ai1=0;
                            versionIA1=null;
                        }
                        est_h1=1;
                        Name1.setLayoutX(width/2-width/10);
                        Name1.setLayoutY(height/4+width/9);
                        Name1.setMinSize(width/10, 30);
                        Name1.setMaxHeight(40);
                        Name1.setAlignment(Pos.CENTER);
                        J1.getChildren().add(Name1);
                    }
                    else if(IA1.isSelected()){
                        if(est_h1==1){
                            J1.getChildren().remove(J1.getChildren().size()-1);
                            est_h1=0;
                            Name1 = new TextField();
                        }
                        est_ai1=1;
                        ToggleButton facile = new RadioButton("Facile");
                        facile.setUserData("facile");
                        facile.setFont(new Font("Arial", tailleDeCase/9));
                        facile.setLayoutX(width/2-width/4);
                        facile.setLayoutY(height/4+width/9);
                        facile.setMinSize(width/10, 30);
                        facile.setMaxHeight(40);
                        facile.setToggleGroup(ia1);
                        ToggleButton moyenne = new RadioButton("Moyenne");
                        moyenne.setUserData("moyenne");
                        moyenne.setFont(new Font("Arial", tailleDeCase/9));
                        moyenne.setLayoutX(width/2);
                        moyenne.setLayoutY(height/4+width/9);
                        moyenne.setMinSize(width/10, 30);
                        moyenne.setMaxHeight(40);
                        moyenne.setToggleGroup(ia1);
                        ToggleButton difficile = new RadioButton("Difficile");
                        difficile.setUserData("difficile");
                        difficile.setFont(new Font("Arial", tailleDeCase/9));
                        difficile.setLayoutX(width/2+width/4);
                        difficile.setLayoutY(height/4+width/9);
                        difficile.setMinSize(width/10, 30);
                        difficile.setMaxHeight(40);
                        difficile.setToggleGroup(ia1);
                        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                        J1.getChildren().add(facile);
                        J1.getChildren().add(moyenne);
                        J1.getChildren().add(difficile);
                    }
                }
            }
        });

        J1.getChildren().add(humain1);
        J1.getChildren().add(IA1);
        this.getChildren().add(J1);

        Group J2 =new Group();
        Label joueur2 = new Label("Joueur 2");
        joueur2.setFont(new Font("Arial", tailleDeCase/3));
        joueur2.setPrefSize(tailleDeCase/3*8, 50);
        joueur2.setAlignment(Pos.CENTER);
        joueur2.setMinSize(width/10, 30);
        joueur2.setMaxSize(width/5, 70);
        joueur2.setLayoutX(width/2-tailleDeCase/3*2);
        joueur2.setLayoutY(height/2);
        this.getChildren().add(joueur2);
        final ToggleGroup j2 = new ToggleGroup();
        //RadioButton humain2 = new RadioButton("Humain");
        RadioButton humain2 = new RadioButton("");
        humain2.setUserData("Humain");
        ImageView humainIm2 = new ImageView(imageHumain);
        humainIm2.setFitHeight(width/10);
        humainIm2.setFitWidth(width/12);
        humain2.setGraphic(humainIm2);
        humain2.setFont(new Font("Arial", tailleDeCase/9));
        humain2.setLayoutX(width/2-width/4);
        humain2.setLayoutY(height/1.8);
        humain2.setToggleGroup(j2);
        //RadioButton IA2 = new RadioButton("IA");
        RadioButton IA2 = new RadioButton("");
        humain2.setUserData("IA2");
        ImageView IAIm2 = new ImageView(imageIA);
        IAIm2.setFitHeight(width/10);
        IAIm2.setFitWidth(width/10);
        IA2.setGraphic(IAIm2);
        IA2.setFont(new Font("Arial", tailleDeCase/9));
        IA2.setLayoutX(width/2+width/4-width/12);
        IA2.setLayoutY(height/1.8);
        IA2.setToggleGroup(j2);
        Name2.setPromptText("Votre prenom");

        final ToggleGroup ia2 = new ToggleGroup();


        j2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j2.getSelectedToggle() != null) {
                    if(humain2.isSelected()){


                        if(est_ai2==1){
                            J2.getChildren().remove(J2.getChildren().size()-3, J2.getChildren().size());
                            est_ai2=0;
                            versionIA2=null;
                        }
                        est_h2=1;
                        Name2.setLayoutX(width/2-width/10);
                        Name2.setLayoutY(height/1.8+width/9);
                        Name2.setMinSize(width/10, 30);
                        Name2.setAlignment(Pos.CENTER);
                        Name2.setMinSize(width/10, 30);
                        Name2.setMaxHeight(40);
                        J2.getChildren().add(Name2);
                    }
                    else if(IA2.isSelected()){

                        if(est_h2==1){
                            J2.getChildren().remove(J2.getChildren().size()-1);
                            est_h2=0;
                            Name2 = new TextField();

                        }
                        est_ai2=1;
                        RadioButton facile = new RadioButton("Facile");
                        facile.setUserData("facile");
                        facile.setFont(new Font("Arial", tailleDeCase/9));
                        facile.setLayoutX(width/2-width/4);
                        facile.setLayoutY(height/1.8+width/9);
                        facile.setMinSize(width/10, 30);
                        facile.setMaxHeight(40);
                        facile.setToggleGroup(ia2);
                        RadioButton moyenne = new RadioButton("Moyenne");
                        moyenne.setUserData("moyenne");
                        moyenne.setFont(new Font("Arial", tailleDeCase/9));
                        moyenne.setLayoutX(width/2);
                        moyenne.setLayoutY(height/1.8+width/9);
                        moyenne.setMinSize(width/10, 30);
                        moyenne.setMaxHeight(40);
                        moyenne.setToggleGroup(ia2);
                        RadioButton difficile = new RadioButton("Difficile");
                        difficile.setUserData("difficile");
                        difficile.setFont(new Font("Arial", tailleDeCase/9));
                        difficile.setLayoutX(width/2+width/4);
                        difficile.setLayoutY(height/1.8+width/9);
                        difficile.setMinSize(width/10, 30);
                        difficile.setMaxHeight(40);
                        difficile.setToggleGroup(ia2);
                        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();

                                        System.out.println("IA2 : " + versionIA2);
                                    }

                                }

                            });

                        J2.getChildren().add(facile);
                        J2.getChildren().add(moyenne);
                        J2.getChildren().add(difficile);
                    }
                }
            }
        });

        J2.getChildren().add(humain2);
        J2.getChildren().add(IA2);
        this.getChildren().add(J2);



        //DropShadow shadow = new DropShadow();
        Button valider = new Button("Valider");
        valider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            valider.setEffect(shadow);
        });
        valider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            valider.setEffect(null);
        });
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

            System.out.println("Enregistrer ! ");
            System.out.println("Name1 : " + Name1.getCharacters());
            System.out.println("IA1 : " + versionIA1);
            System.out.println("Name2 : " + Name2.getCharacters());
            System.out.println("IA2 : " + versionIA2);


            i.goToPlateau(versionIA1, versionIA2);
        });
        valider.setLayoutX(width/2-width/20);
        valider.setLayoutY(height/1.3);
        valider.setMinSize(width/10, 30);
        this.getChildren().add(valider);

    }

}
