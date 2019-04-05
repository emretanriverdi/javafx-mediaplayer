import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class JavaFxMediaPlayer extends Application {
    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    public void start(Stage primaryStage) {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(TEST_URL));
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });

        Button fullScreen = new Button("[[]]");
        fullScreen.setOnAction(e -> {
            if (fullScreen.getText().equals("[[]]")) {
                ((Stage)mediaView.getScene().getWindow()).setFullScreen(true);
                fullScreen.setText("]][[");
            } else {
                ((Stage)mediaView.getScene().getWindow()).setFullScreen(false);
                fullScreen.setText("[[]]");
            }
        });

        Slider slVolume = new Slider();
        slVolume.setPrefWidth(180);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(60);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton, fullScreen,
                new Label("Volume"), slVolume);

        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Media Player Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}