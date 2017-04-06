import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bindr extends Application {
    /**
     * @author Martin et al
     */
    private void chooseDirectory() {
        Bindr bindr = this; // Reference to this Bindr object in the anonymous EventHandler below
        final Label labelSelectedDirectory = new Label();

        Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText("Open DirectoryChooser");
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory =
                        directoryChooser.showDialog(stage);

                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("No Directory selected");
                }else{
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                    bindr.showImages(selectedDirectory.getAbsolutePath());
                }
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                labelSelectedDirectory,
                btnOpenDirectoryChooser);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 250);

        // primaryStage.setTitle("http://java-buddy.blogspot.com/");
        stage.setScene(scene);
        stage.show();
    }



    /**
     * @author Kristian et al
     */
    private List<File> getImages(String path) {
        String[] fileTypes = {"jpg", "png"};
        File folder = new File(path);
        String[] parts;
        ArrayList<File> listOfFiles = new ArrayList<>();

        for (File f: folder.listFiles()) {
            listOfFiles.add(f);
        }

        // System.out.println(listOfFiles);

        for (int i = 0; i < listOfFiles.size(); i++) {
            if (listOfFiles.get(i).isFile()) {
                parts = listOfFiles.get(i).getName().split("\\.");

                String extension = parts[parts.length-1];
                // System.out.println(extension);

                boolean remove = true;
                for (String s : fileTypes) {
                    if (extension.equals(s)) {
                        remove = false;
                        break;
                    }
                }
                if (remove) {
                    listOfFiles.remove(i);
                    i--;
                }

            } else {
                listOfFiles.remove(i);
                i--;
            }
        }

        // System.out.println(listOfFiles);

        return listOfFiles;
    }



    /**
     * @author Mathias et al
     */
    private Button[] getNextButtons(String url) {
        Button no1 = new Button("Delete");
        no1.setOnAction(event -> {
            try {
                // Files.delete(path); //tager i mod en "Path" fil (fra et array?)
                Path path = Paths.get(url);
                String folder = path.getParent().toString();
                String file = path.getFileName().toString();
                Path bin = Paths.get(folder, "bin", file);
                Files.move(path, bin); // Move to bin folder instead of deleting
            // } catch (NoSuchFileException x) {
                // System.err.format("%s: no such" + " file or directory%n", path);
            // } catch (DirectoryNotEmptyException x) {
                // System.err.format("%s not empty%n", path);
            } catch (IOException x) {
                System.err.println(x);
            }

            showNext();
        });

        Button no2 = new Button("Keep");
        no2.setOnAction(event -> showNext());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(no1,no2);

        Scene scene = new Scene(vBox);
        // primaryStage.setScene(scene);
        // primaryStage.show();

        return new Button[]{no1, no2};
    }

    private void showNext() {
        // int i = 0;

        // while (ListOfFile().length > i) {
        if (this.images.hasNext()) {
            // ListOfFiles[i];
            // i++;
            showImage(this.images.next().getAbsolutePath());
        } else {
            this.folderEmpty();
        }
    }



    /**
     * @author Tina, Karo and Emma
     */
    private void showImage (String url) {
        Button[] buttons = this.getNextButtons(url);
        ImageView imageView = new ImageView("file:" + url);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);
        HBox hBox = new HBox(buttons);
        VBox vBox = new VBox(hBox, imageView);
        vBox.setPadding(new Insets(30, 30, 30, 30));
        vBox.setStyle("-fx-background-color: black");
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }



    /**
     * @author Mikkel et al
     */
    private void folderEmpty() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Window");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Folder is empty. THUMPS UP!");
        Button closeButton = new Button("Close");
        // closeButton.setOnAction(e -> window.close());
        closeButton.setOnAction(e -> System.exit(0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }



    // Kick things off after choosing directory
    private void showImages(String path) {
        try {
            Files.createDirectory(Paths.get(path, "bin"));
            this.images = this.getImages(path).iterator();
            this.showNext();
        } catch (FileAlreadyExistsException e) {
            this.images = this.getImages(path).iterator();
            this.showNext();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Bindr");
        this.stage = stage;
        this.chooseDirectory();
    }

    private Stage stage;
    private Iterator<File> images;
}
