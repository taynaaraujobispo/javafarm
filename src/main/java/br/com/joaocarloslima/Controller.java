package br.com.joaocarloslima;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {

    private Fazenda fazenda = new Fazenda();
    private List<ImageView> imageTerrenos = new ArrayList<>();
    private int sleepTime = 3000;

    @FXML
    GridPane grid;
    @FXML
    ToggleButton botaoBatata;
    @FXML
    ToggleButton botaoCenoura;
    @FXML
    ToggleButton botaoMorango;
    @FXML
    ToggleButton botaoColher;
    @FXML
    ProgressBar ocupacaoDoCeleiro;
    @FXML
    CheckBox ckbAcelerar;

    /** Atualiza as imagens da tela e informações do celeiro */
    public void atualizar() {
        botaoBatata.setText("Batata x " + fazenda.getCeleiro().getQtdeBatatas());
        botaoCenoura.setText("Cenoura x " + fazenda.getCeleiro().getQtdeCenouras());
        botaoMorango.setText("Morango x " + fazenda.getCeleiro().getQtdeMorangos());
        // getOcupacao() já retorna 0..1 para ProgressBar
        ocupacaoDoCeleiro.setProgress(fazenda.getCeleiro().getOcupacao());

        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                Terreno terreno = fazenda.getTerreno(x, y);
                ImageView imageView = imageTerrenos.get(x * 13 + y);

                Planta p = (terreno != null) ? terreno.getPlanta() : null;
                if (p != null) {
                    try {
                        imageView.setImage(new Image(getClass().getResourceAsStream(p.getImagem())));
                    } catch (Exception e) {
                        // se a imagem não for encontrada, limpa a imagem
                        imageView.setImage(null);
                    }
                } else {
                    imageView.setImage(null);
                }
            }
        }
    }

    /** Faz todas as plantas crescerem a cada ciclo */
    public void ciclo() {
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                Terreno terreno = fazenda.getTerreno(x, y);
                if (terreno != null) {
                    Planta p = terreno.getPlanta();
                    if (p != null) {
                        p.crescer();
                    }
                }
            }
        }
        atualizar();
    }

    /** Ativa ou desativa aceleração */
    public void acelerar() {
        if (ckbAcelerar.isSelected()) {
            sleepTime = 1000;
        } else {
            sleepTime = 3000;
        }
    }

    /** Thread que controla o relógio do jogo */
    public void clockThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                    Platform.runLater(this::ciclo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Cria a grade de terrenos (13x13)
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                grid.add(imageView, x, y);
                imageTerrenos.add(imageView);
            }
        }

        // Evento de clique na grade para plantar ou colher
        grid.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / 50);
            int y = (int) (e.getY() / 50);

           try {
    if (botaoCenoura.isSelected())
        fazenda.plantar(x, y, new Cenoura());
    if (botaoBatata.isSelected())
        fazenda.plantar(x, y, new Batata());
    if (botaoMorango.isSelected())
        fazenda.plantar(x, y, new Morango());
    if (botaoColher.isSelected()) {
        boolean ok = fazenda.colher(x, y);
        if (!ok) {
            throw new Exception("Não foi possível colher (terreno vazio, produto não maduro ou celeiro cheio).");
        }
    }

    atualizar();
} catch (Exception ex) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erro");
    alert.setHeaderText(ex.getMessage());
    alert.showAndWait();
}

        });

        atualizar();
        clockThread();
    }
}
