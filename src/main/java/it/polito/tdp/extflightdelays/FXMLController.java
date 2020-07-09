package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="compagnieMinimo"
    private TextField compagnieMinimo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxAeroportoPartenza"
    private ComboBox<Airport> cmbBoxAeroportoPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxAeroportoDestinazione"
    private ComboBox<Airport> cmbBoxAeroportoDestinazione; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessione"
    private Button btnConnessione; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	int x;
    	try {
    		x=Integer.parseInt(compagnieMinimo.getText());
    	}catch(Throwable t) {
    		txtResult.appendText("Errore nell'input");
    		return;
    	}
    	this.model.creaGrafo(x);
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("Vertici: "+this.model.vertexNumber()+"\n");
    	txtResult.appendText(" Archi: "+this.model.edgeNumber()+"\n");
    	
    	this.cmbBoxAeroportoPartenza.getItems().addAll(this.model.getAereoportiNelGrafo());
    	this.cmbBoxAeroportoDestinazione.getItems().addAll(this.model.getAereoportiNelGrafo());
    	
    }

    @FXML
    void doTestConnessione(ActionEvent event) {
    	Airport a1=this.cmbBoxAeroportoPartenza.getValue();
    	Airport a2=this.cmbBoxAeroportoDestinazione.getValue();
    	if(a1==null || a2 ==null) {
    		txtResult.appendText("Selezionare i due aereoporti");
    		return;
    	}
    	List<Airport>percorso=this.model.trovaPercorso(a1, a2);
    	if(percorso==null) {
    		txtResult.appendText("Non sono collegati");
    	}else {
    		for(int i=0;i<percorso.size();i++) {
    			txtResult.appendText(percorso.get(i).getAirportName()+"\n");
    		}
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert compagnieMinimo != null : "fx:id=\"compagnieMinimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBoxAeroportoPartenza != null : "fx:id=\"cmbBoxAeroportoPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBoxAeroportoDestinazione != null : "fx:id=\"cmbBoxAeroportoDestinazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConnessione != null : "fx:id=\"btnConnessione\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }
}
