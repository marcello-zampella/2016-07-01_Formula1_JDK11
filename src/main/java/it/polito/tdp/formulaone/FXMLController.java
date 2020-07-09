package it.polito.tdp.formulaone;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller del turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> boxAnno;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private Button buttonDream;


    @FXML
    void doCreaGrafo(ActionEvent event) {
    	if(this.boxAnno==null) {
    		this.txtResult.setText("DEVI SCEGLIERE UN ANNO");
    		return;
    	}
    	this.model.creaGrafo(this.boxAnno.getValue());
    	this.txtResult.setText("L'id del vincitore e' "+this.model.getVincitore()+" con punteggio "+this.model.getMassimo());
    	this.buttonDream.setDisable(false);

    }
    
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Integer.parseInt(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {
    	String s=this.textInputK.getText();
    	if(!this.isNumeric(s)) {
    		this.txtResult.setText("INSERISCI UN NUMERO INTERO");
    		return;
    	}
    	int team=Integer.parseInt(s);
    	for(Integer i:this.model.getDreamTeam(team)) {
    		this.txtResult.appendText(""+i);
    	}

    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxAnno.getItems().clear();
		this.boxAnno.getItems().addAll(model.getAllYears());
		this.buttonDream.setDisable(true);
	}
}
