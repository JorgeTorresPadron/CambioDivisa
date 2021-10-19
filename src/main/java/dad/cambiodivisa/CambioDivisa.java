package dad.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField primerText, segundoText;
	private ComboBox<Divisa> primerComboBox, segundoComboBox;
	private Button calcularButton;

	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);
	private Divisa[] divisas = { euro, libra, dolar, yen };

	@Override
	public void start(Stage primaryStage) throws Exception {

		primerText = new TextField("0");
		primerText.setMaxWidth(70);
		segundoText = new TextField("0");
		segundoText.setMaxWidth(70);

		primerComboBox = new ComboBox<>();
		primerComboBox.getItems().addAll(divisas);
		primerComboBox.getSelectionModel().select(euro);
		segundoComboBox = new ComboBox<>();
		segundoComboBox.getItems().addAll(divisas);
		segundoComboBox.getSelectionModel().select(yen);

		calcularButton = new Button("Cambiar");
		calcularButton.setOnAction(e -> onCalcularButton(e));

		HBox primerHBox = new HBox(primerText, primerComboBox);
		primerHBox.setSpacing(5);
		primerHBox.setAlignment(Pos.CENTER);
		HBox segundoHBox = new HBox(segundoText, segundoComboBox);
		segundoHBox.setSpacing(5);
		segundoHBox.setAlignment(Pos.CENTER);
		VBox root = new VBox(5, primerHBox, segundoHBox, calcularButton);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 300, 200);
		primaryStage.setTitle("CambioDivisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void onCalcularButton(ActionEvent e) {
		Double primerNumero = Double.parseDouble(primerText.getText());
		Divisa primeraMoneda = primerComboBox.getSelectionModel().getSelectedItem();
		Divisa segundaMoneda = segundoComboBox.getSelectionModel().getSelectedItem();
		Double pasarEuros = primeraMoneda.toEuro(primerNumero);
		Double resultado = segundaMoneda.fromEuro(pasarEuros);
		segundoText.setText(resultado.toString());
		segundoText.setEditable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
