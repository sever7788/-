package sample;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu {
    Game game;
    Stage stage;
    public Menu(Stage stage, Game game){
        this.game = game;
        this.stage = stage;
    }
    public void initMenu(){
        StackPane root = new StackPane();
        Image imageFon = new Image(getClass().getResourceAsStream("5.gif"));
        ImageView imgFon = new ImageView(imageFon);
        Image imageLogo = new Image(getClass().getResourceAsStream("6.png"));
        ImageView imgLogo = new ImageView(imageLogo);

        imgFon.setFitHeight(600);
        imgFon.setFitWidth(900);
        imgLogo.setFitHeight(121);
        imgLogo.setFitWidth(512);
        StackPane.setMargin(imgLogo, new Insets(25, 0, 0, 0));
        StackPane.setAlignment(imgLogo, Pos.TOP_CENTER);

        MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(
                newGame, options, exitGame
        );
        MenuItem sound = new MenuItem("ЗВУК");
        MenuItem video = new MenuItem("ВИДЕО");
        MenuItem keys = new MenuItem("УПРАВЛЕНИЕ");
        MenuItem optionsBack = new MenuItem("НАЗАД");
        SubMenu optionsMenu = new SubMenu(
                sound, video, keys, optionsBack
        );
        MenuItem NG1 = new MenuItem("ТУРНИР");
        MenuItem NG2 = new MenuItem("ОДИН ЗАЕЗД");
        MenuItem NG3 = new MenuItem("2 ИГРОКА");
        MenuItem NG4 = new MenuItem("НАЗАД");
        SubMenu newGameMenu = new SubMenu(
                NG1, NG2, NG3, NG4
        );
        MenuBox menuBox = new MenuBox(mainMenu);
        menuBox.setTranslateX(250);
        menuBox.setTranslateY(200);

        newGame.setOnMouseClicked(event->game.initGame());
        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event->System.exit(0));
        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
        NG4.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(imgFon,imgLogo, menuBox);

        Scene scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
                if (!menuBox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
                }
                else{
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt ->   menuBox.setVisible(false));
                    ft.play();

                }
            }
        });
        stage.setScene(scene);
    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name){
            Rectangle bg = new Rectangle(400, 60, Color.rgb(240,130,0));
            bg.setArcWidth(20);
            bg.setArcHeight(20);
            bg.setOpacity(1);
            Text text = new Text(name);
            text.setFill(Color.rgb(59,31,135));
            text.setFont(Font.font("Arial", FontWeight.BOLD,28));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bg);//анимация
            setOnMouseEntered(event->{
                st.setFromValue(Color.rgb(240,130,0));
                st.setToValue(Color.rgb(252,207,0));
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event->{
                st.stop();
                bg.setFill(Color.rgb(240,130,0));
            });
        }
    }


    private static class MenuBox extends Pane {
        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;

            setVisible(true);
            Rectangle bg = new Rectangle(900,600, Color.LIGHTBLUE);
            bg.setOpacity(0);
            getChildren().addAll(bg,subMenu);
        }
        public void setSubMenu(SubMenu subMenu){
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
    }

    private static class SubMenu extends VBox {
        public SubMenu(MenuItem...items){
            setSpacing(15);

            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }
    }
}
