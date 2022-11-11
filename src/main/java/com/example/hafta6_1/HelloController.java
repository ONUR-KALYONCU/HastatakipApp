package com.example.hafta6_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> cmbcinsiyet;

    @FXML
    private ComboBox<String> cmbhastasecimi;

    @FXML
    private Spinner<Double> spnboy;

    @FXML
    private Spinner<Double> spnkilo;

    @FXML
    private TextField txtadsoyad;

    @FXML
    private TextField txtboy;

    @FXML
    private TextField txtcinsiyet;

    @FXML
    private TextField txtkilo;

    private ArrayList<Hasta>Hastalar=new ArrayList<Hasta>();

    @FXML
    void hastagoster(ActionEvent event) {

        int secilen=cmbhastasecimi.getSelectionModel().getSelectedIndex();
        txtboy.setText(Hastalar.get(secilen).getBoy()+" m");
        txtkilo.setText(Hastalar.get(secilen).getKilo()+" kg");
        txtcinsiyet.setText(Hastalar.get(secilen).getCinsiyet());

    }

    @FXML
    void hastakayit(ActionEvent event) throws FileNotFoundException {

        String adsoyad=txtadsoyad.getText();

        for (int i=0 ;i<Hastalar.size();i++){
            if (adsoyad.equals(Hastalar.get(i).getAdsoyad())){
                Alert hastamesaji=new Alert(Alert.AlertType.ERROR);
                hastamesaji.setTitle("Hata");
                hastamesaji.setHeaderText(adsoyad + " kişisi zaten kayıtlı...");
                hastamesaji.show();
                return;
            }
        }


        String cinsiyet=cmbcinsiyet.getValue();
        double boy=spnboy.getValue();
        double kilo=spnkilo.getValue();


        Hasta hasta=new Hasta(adsoyad,cinsiyet,boy,kilo);

        Hastalar.add(hasta);

        try{
            FileOutputStream fos=new FileOutputStream("hastabilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(Hastalar);

            fos.close();
            oos.close();

            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.INFORMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(hasta.getAdsoyad()+" kişisi kaydedildi...");
            bilgilendirmemesaji.show();


            cmbhastasecimi.getItems().add(hasta.getAdsoyad());

        }
         catch (Exception e) {
             e.printStackTrace();
         }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbcinsiyet.getItems().add("Erkek");
        cmbcinsiyet.getItems().add("Kadın");

       spnboy.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,3,1.75,0.01));
       spnkilo.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,300,75,0.10));

        try {
            FileInputStream fis= new FileInputStream("hastabilgileri.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);

            Hastalar=(ArrayList<Hasta>)ois.readObject();
            ois.close();


            for (int i=0;1<Hastalar.size();i++){
                cmbhastasecimi.getItems().add(Hastalar.get(i).getAdsoyad());
            }

        }
        catch (Exception e) {
           e.printStackTrace();
        }
    }
}
