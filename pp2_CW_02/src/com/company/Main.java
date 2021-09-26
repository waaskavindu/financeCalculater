package com.company;

import com.mongodb.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Main extends Application{
    public static DB database;
    public static String SALast;
    public static Label lblInvestment;
    public static Label lblInterest;
    public static Label lblYears;
    public static Label lblFutureValue;
    public static Label lblMonthly;
    public static TextField txtInvestment;
    public static TextField txtInterest;
    public static TextField txtYears;
    public static TextField txtFutureValue;
    public static TextField txtMonthly;
    public static Button btnCalculateSA;
    public static AnchorPane keyBoardPane;
    public static boolean selectInvestment;
    public static boolean selectInterest;
    public static boolean selectYears;
    public static boolean selectFutureValue;
    // for Interest saving
    public static Button btnCalculateIS;
    public static AnchorPane keyBoardPaneIS;
    public static Label lblInvestmentIS;
    public static Label lblInterestIS;
    public static Label lblYearsIS;
    public static Label lblFutureValueIS;
    public static TextField txtInvestmentIS;
    public static TextField txtInterestIS;
    public static TextField txtYearsIS;
    public static TextField txtFutureValueIS;
    public static String ISLast;
    public static boolean selectInvestmentIS;
    public static boolean selectInterestIS;
    public static boolean selectYearsIS;
    public static boolean selectFutureValueIS;
    //for Loan
    public static Button btnCalculateLO;
    public static AnchorPane keyBoardPaneLO;
    public static Label lblLoanAmountLO;
    public static Label lblInterestLO;
    public static Label lblYearsLO;
    public static Label lblFutureValueLO;
    public static TextField txtLoanAmountLO;
    public static TextField txtInterestLO;
    public static TextField txtYearsLO;
    public static TextField txtFutureValueLO;
    public static String LOLast;
    public static boolean selectInvestmentLO;
    public static boolean selectInterestLO;
    public static boolean selectYearsLO;
    public static boolean selectFutureValueLO;
    //for mortgage
    public static Button btnCalculateMT;
    public static AnchorPane keyBoardPaneMT;
    public static Label lblInvestmentMT;
    public static Label lblInterestMT;
    public static Label lblYearsMT;
    public static Label lblFutureValueMT;
    public static TextField txtInvestmentMT;
    public static TextField txtInterestMT;
    public static TextField txtYearsMT;
    public static TextField txtFutureValueMT;
    public static String MTLast;
    public static boolean selectInvestmentMT;
    public static boolean selectInterestMT;
    public static boolean selectYearsMT;
    public static boolean selectFutureValueMT;


    MongoClient mongoClient=new MongoClient("localhost",27017);

    //decimal format
    DecimalFormat df=new DecimalFormat("0.00");


    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane pane = new TabPane();
        pane.setPrefHeight(638);
        pane.setPrefWidth(428);
        Tab TabHP = new Tab("Home");
        Tab TabIS = new Tab("Compound Saving");
        Tab TabSA = new Tab("Saving");
        Tab TabLO = new Tab("Loans");
        Tab TabMT = new Tab("Mortgage");
        Tab TabHelp=new  Tab("Help");
        //creating anchorPanes for tabs
        AnchorPane APaneHP = new AnchorPane();
        AnchorPane APaneIS = new AnchorPane();
        AnchorPane APaneSA = new AnchorPane();
        AnchorPane APaneLO = new AnchorPane();
        AnchorPane APaneMT = new AnchorPane();
        AnchorPane APaneHelp=new AnchorPane();
        //give widths for AnchorPane
        APaneHP.setPrefWidth(200);
        APaneIS.setPrefWidth(200);
        APaneSA.setPrefWidth(200);
        APaneLO.setPrefWidth(200);
        APaneMT.setPrefWidth(200);
        APaneHelp.setPrefWidth(200);
        //give heights for AnchorPane
        APaneHP.setPrefHeight(180);
        APaneIS.setPrefHeight(180);
        APaneSA.setPrefHeight(180);
        APaneLO.setPrefHeight(180);
        APaneMT.setPrefHeight(180);
        APaneHelp.setPrefHeight(180);
        //connect tabs to AnchorPanes
        TabHP.setContent(APaneHP);
        TabIS.setContent(APaneIS);
        TabSA.setContent(APaneSA);
        TabLO.setContent(APaneLO);
        TabMT.setContent(APaneMT);
        TabHelp.setContent(APaneHelp);
        //connect tabs to the pane
        pane.getTabs().add(TabHP);//HomePage
        pane.getTabs().add(TabIS);//Interest Saving
        pane.getTabs().add(TabSA);//Saving
        pane.getTabs().add(TabLO);//loan
        pane.getTabs().add(TabMT);//Mortgage
        pane.getTabs().add(TabHelp);//Help
        //setting up a image to homepage
        Image image1 =new Image("com/imagers/54.jpg");
        ImageView imgHP = new ImageView();
        imgHP.setFitWidth(738);
        imgHP.setFitHeight(488);
        imgHP.setImage(image1);
        APaneHP.getChildren().add(imgHP);
        //setting image to help
        Image imageHelp=new Image("com/imagers/help.png");
        ImageView imgHelp = new ImageView();
        imgHelp.setLayoutX(2);
        imgHelp.setLayoutY(1);
        imgHelp.setFitWidth(738);
        imgHelp.setFitHeight(388);
        imgHelp.setImage(imageHelp);
        APaneHelp.getChildren().add(imgHelp);
        //Create a stage
        Stage stage = new Stage();
        stage.setTitle("Calculator");
        Scene scene = new Scene(pane, 738, 428);
        stage.setScene(scene);
        stage.show();

        //****************************************************************
        //**************************Saving**********************************
        //****************************************************************
        //Buttons for saving
        btnCalculateSA = createButton("Calculate", 151.0, 345.0, 80.8, 25.6);
        //labels for saving
        lblInvestment = createLabel("Investment", 61.0, 100.0);
        lblInterest = createLabel("Interest rate", 63.0, 146.0);
        lblYears = createLabel("Time", 66.0, 194.0);
        lblFutureValue = createLabel("Final value", 62.0, 240.0);
        lblMonthly = createLabel("PMT",62.0,294.0);
        //TextFields for saving
        txtInvestment = createTextField("Present value", 151.0, 96.0, 148.8, 25.6);
        txtInterest = createTextField("(%)", 151.0, 142.0, 148.8, 25.6);
        txtYears = createTextField("In Years", 151.0, 190.0, 148.8, 25.6);
        txtFutureValue = createTextField("Future Value", 151.0, 236.0, 148.8, 25.6);
        txtMonthly = createTextField("PMT",151.0,286.0,148.8,25.6);
        //add image to saving
        Image image2 =new Image("com/imagers/Untitled design 3.png");
        ImageView imgSA = new ImageView();
        imgSA.setFitWidth(738);
        imgSA.setFitHeight(488);
        imgSA.setImage(image2);
        APaneSA.getChildren().add(imgSA);
        //add keyboards to AnchorPanes
        keyBoardPane = keyboard();
        APaneSA.getChildren().add(keyBoardPane);
        //Set Button to AnchorPane
        APaneSA.getChildren().add(btnCalculateSA);
        //Set lbl to AnchorPane
        APaneSA.getChildren().add(lblInvestment);
        APaneSA.getChildren().add(lblInterest);
        APaneSA.getChildren().add(lblYears);
        APaneSA.getChildren().add(lblFutureValue);
        APaneSA.getChildren().add(lblMonthly);
        //Set TextFields to AnchorPane
        APaneSA.getChildren().add(txtInvestment);
        APaneSA.getChildren().add(txtInterest);
        APaneSA.getChildren().add(txtYears);
        APaneSA.getChildren().add(txtFutureValue);
        APaneSA.getChildren().add(txtMonthly);
        //Create a database
        database = mongoClient.getDB("JavaCW02");
        //Creating the SavingDetails table in the database
        database.createCollection("SavingDetails", null);
        DBCollection SATable = database.getCollection("SavingDetails");
        DBCursor findIterable = SATable.find();
        for (DBObject count : findIterable) {
            SALast = (String) count.get("Last");
            if (SALast.equals("Yes")) {
                txtInvestment.setText((String) count.get("Investment"));
                txtInterest.setText((String) count.get("Interest"));
                txtYears.setText((String) count.get("Years"));
                txtFutureValue.setText((String) count.get("FutureValue"));
                txtMonthly.setText((String) count.get("Monthly"));
            }
        }
        btnCalculateSA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!txtMonthly.getText().equals("")&&!txtInvestment.getText().equals("")&& !txtInterest.getText().equals("") && !txtYears.getText().equals("") && txtFutureValue.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestment.getText());
                        Double r = Double.parseDouble(txtInterest.getText());
                        Double t = Double.parseDouble(txtYears.getText());
                        Double PMT=Double.parseDouble(txtMonthly.getText());
                        Double x=Math.pow((1+(r/1200)),(12*t));
                        Double fV=(PMT*((x-1)/(r/1200)))+pV*x;//future value//wright
                        txtFutureValue.setText(df.format(fV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Future value is"+df.format(fV));
                        a.showAndWait();
                    }else if (!txtInvestment.getText().equals("")&& txtInterest.getText().equals("") && !txtYears.getText().equals("") && !txtFutureValue.getText().equals("")) {
                        Double pV = Double.parseDouble(txtInvestment.getText());
                        Double fV = Double.parseDouble(txtFutureValue.getText());
                        Double t = Double.parseDouble(txtYears.getText());
                        Double r = 12 * ((Math.pow((fV / pV), (1/(12 * t)))) - 1);//Interest rate////////////////////////wrong
                        txtInterest.setText(df.format(r));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Interest rate is"+df.format(r)+"%");
                        a.showAndWait();
                    }else if (txtInvestment.getText().equals("") && !txtInterest.getText().equals("") && !txtYears.getText().equals("") && !txtFutureValue.getText().equals("")) {
                        Double r = Double.parseDouble(txtInterest.getText());
                        Double fV = Double.parseDouble(txtFutureValue.getText());
                        Double t = Double.parseDouble(txtYears.getText());
                        Double PMT=Double.parseDouble(txtMonthly.getText());
                        Double x=Math.pow((1+(r/1200)),(12*t));
                        Double pV =((fV-(PMT*((x-1)/(r/1200))))/x);//Investment///wright
                        txtInvestment.setText(df.format(pV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("You need to invest" + df.format(pV));
                        a.showAndWait();
                    }else if(txtYears.getText().equals("")&&!txtInvestment.getText().equals("") && !txtInterest.getText().equals("") && !txtFutureValue.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestment.getText());
                        Double r = Double.parseDouble(txtInterest.getText());
                        Double fV = Double.parseDouble(txtFutureValue.getText());
                        Double PMT=Double.parseDouble(txtMonthly.getText());
                        Double t = Math.log(((fV*pV)/(1200*PMT))+1)/(12*Math.log(1+(r/1200)));//Time //wright
                        txtYears.setText(df.format(t));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("it will take " + df.format(t) + "years");
                        a.showAndWait();

                    }
                    else if (txtMonthly.getText().equals("")&&!txtInvestment.getText().equals("")&& !txtInterest.getText().equals("") && !txtYears.getText().equals("") && !txtFutureValue.getText().equals("")) {
                        Double r = Double.parseDouble(txtInterest.getText());
                        Double pV = Double.parseDouble(txtInvestment.getText());
                        Double fV = Double.parseDouble(txtFutureValue.getText());//wright
                        Double t = Double.parseDouble(txtYears.getText());
                        Double x=Math.pow((1+(r/1200)),(12*t));
                        Double PMT=(fV-pV*x)/((x-1)/(r/1200));//PMT
                        txtMonthly.setText(df.format(PMT));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("PMT is "+PMT);
                        a.showAndWait();}
                    else {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Only one text filed can be empty please enter correct values ");
                        a.showAndWait();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Please re-cheque the input details and enter again");
                    a.showAndWait();

                }
                DBCollection SATable = database.getCollection("SavingDetails");
                DBCursor findIterable = SATable.find();
                BasicDBObject basicDBObject = new BasicDBObject();
                for (DBObject count : findIterable) {
                    SALast = (String) count.get("Last");
                    if(SALast.equals("Yes")){
                        BasicDBObject queryForSaving= new BasicDBObject();
                        queryForSaving.put("Last", SALast);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("Last","No");
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        database.getCollection("SavingDetails").update(queryForSaving,updateObject);
                    }
                }basicDBObject.put("Investment", txtInvestment.getText());
                basicDBObject.put("Interest", txtFutureValue.getText());
                basicDBObject.put("Years",txtYears.getText());
                basicDBObject.put("FutureValue", txtFutureValue.getText());
                basicDBObject.put("Monthly",txtMonthly.getText());
                basicDBObject.put("Last","Yes");
                SATable.insert(basicDBObject);
            }
        });

        txtInvestment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestment = true;
                selectInterest = false;
                selectYears = false;
                selectFutureValue = false;
            }
        });
        txtInterest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestment = false;
                selectInterest = true;
                selectYears = false;
                selectFutureValue = false;
            }
        });
        txtYears.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestment = false;
                selectInterest = false;
                selectYears = true;
                selectFutureValue = false;
            }
        });
        txtFutureValue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestment = false;
                selectInterest = false;
                selectYears = false;
                selectFutureValue = true;
            }
        });
        //****************END****Saving********************************

        //************************************************************
        //*************Interest Saving*********************************
        //************************************************************
        btnCalculateIS = createButton("Calculate", 151.0, 289.0, 80.8, 25.6);
        //labels for interest saving
        lblInvestmentIS = createLabel("Investment", 61.0, 100.0);
        lblInterestIS = createLabel("Interest rate", 63.0, 146.0);
        lblYearsIS = createLabel("Time", 66.0, 194.0);
        lblFutureValueIS = createLabel("Final value", 62.0, 240.0);
        //TextFields for interest saving
        txtInvestmentIS = createTextField("Present value", 151.0, 96.0, 148.8, 25.6);
        txtInterestIS = createTextField("(%)", 151.0, 142.0, 148.8, 25.6);
        txtYearsIS = createTextField("In Years", 151.0, 190.0, 148.8, 25.6);
        txtFutureValueIS = createTextField("Future Value", 151.0, 236.0, 148.8, 25.6);
        //add image to interest saving
        Image image3 =new Image("com/imagers/Untitled design 5.png");
        ImageView imgIS = new ImageView();
        imgIS.setFitWidth(738);
        imgIS.setFitHeight(478);
        imgIS.setImage(image3);
        APaneIS.getChildren().add(imgIS);
        //Add labels for interest saving
        APaneIS.getChildren().add(lblInvestmentIS);
        APaneIS.getChildren().add(lblInterestIS);
        APaneIS.getChildren().add(lblYearsIS);
        APaneIS.getChildren().add(lblFutureValueIS);
        //Add TextFields for interest saving
        APaneIS.getChildren().add(txtInvestmentIS);
        APaneIS.getChildren().add(txtInterestIS);
        APaneIS.getChildren().add(txtYearsIS);
        APaneIS.getChildren().add(txtFutureValueIS);
        //Add Buttons to AnchorPane
        APaneIS.getChildren().add(btnCalculateIS);
        //Add KeyBoard to interest saving AnchorPane
        keyBoardPaneIS = keyboard();
        APaneIS.getChildren().add(keyBoardPaneIS);
        database.createCollection("InterestSavingDetails", null);
        DBCollection ISTable = database.getCollection("InterestSavingDetails");
        DBCursor findIterableIS = ISTable.find();
        for (DBObject count : findIterableIS) {
            ISLast = (String) count.get("Last");
            if (ISLast.equals("Yes")) {
                txtInvestmentIS.setText((String) count.get("InvestmentIS"));
                txtInterestIS.setText((String) count.get("InterestIS"));
                txtYearsIS.setText((String) count.get("YearsIS"));
                txtFutureValueIS.setText((String) count.get("FutureValueIS"));
            }
        }
        btnCalculateIS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!txtInvestmentIS.getText().equals("")&& !txtInterestIS.getText().equals("") && !txtYearsIS.getText().equals("") && txtFutureValueIS.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestmentIS.getText());
                        Double r = Double.parseDouble(txtInterestIS.getText());
                        Double t = Double.parseDouble(txtYearsIS.getText());
                        Double fV=pV*(Math.pow((1+r/100),t));//future value
                        txtFutureValueIS.setText(df.format(fV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Future value is"+df.format(fV));
                        a.showAndWait();
                    }else if (!txtInvestmentIS.getText().equals("")&& txtInterestIS.getText().equals("") && !txtYearsIS.getText().equals("") && !txtFutureValueIS.getText().equals("")) {
                        Double pV = Double.parseDouble(txtInvestmentIS.getText());
                        Double fV = Double.parseDouble(txtFutureValueIS.getText());
                        Double t = Double.parseDouble(txtYearsIS.getText());
                        Double r = 100*((Math.pow(fV/pV,1/t))-1);//Interest rate
                        txtInterestIS.setText(df.format(r));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Interest rate is"+df.format(r)+"%");
                        a.showAndWait();
                    }else if (txtInvestmentIS.getText().equals("")&& !txtInterestIS.getText().equals("") && !txtYearsIS.getText().equals("") && !txtFutureValueIS.getText().equals("")) {
                        Double r = Double.parseDouble(txtInterestIS.getText());
                        Double fV = Double.parseDouble(txtFutureValueIS.getText());
                        Double t = Double.parseDouble(txtYearsIS.getText());
                        Double pV = fV/(Math.pow((1+r/100),t));//Investment
                        txtInvestmentIS.setText(df.format(pV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("You need to invest" + df.format(pV));
                        a.showAndWait();
                    }else if(txtYearsIS.getText().equals("")&&!txtInvestmentIS.getText().equals("") && !txtInterestIS.getText().equals("") && !txtFutureValueIS.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestmentIS.getText());
                        Double r = Double.parseDouble(txtInterestIS.getText());
                        Double fV = Double.parseDouble(txtFutureValueIS.getText());
                        Double t = (Math.log(fV)-Math.log(pV))/(Math.log(1+r/100));//Time
                        txtYearsIS.setText(df.format(t));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("it will take " + df.format(t) + "years");
                        a.showAndWait();
                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Only one text filed can be empty please enter correct values ");
                        a.showAndWait();
                    }

                }catch (Exception e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Please re-cheque the input details and enter again");
                    a.showAndWait();

                }
                DBCollection SATable = database.getCollection("InterestSavingDetails");
                DBCursor findIterable = SATable.find();
                BasicDBObject basicDBObject = new BasicDBObject();
                for (DBObject count : findIterable) {
                    SALast = (String) count.get("Last");
                    if(SALast.equals("Yes")){
                        BasicDBObject queryForSaving= new BasicDBObject();
                        queryForSaving.put("Last", SALast);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("Last","No");
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        database.getCollection("InterestSavingDetails").update(queryForSaving,updateObject);
                    }
                }basicDBObject.put("Investment", txtInvestmentIS.getText());
                basicDBObject.put("Interest", txtFutureValueIS.getText());
                basicDBObject.put("Years",txtYearsIS.getText());
                basicDBObject.put("FutureValue", txtFutureValueIS.getText());
                basicDBObject.put("Last","Yes");
                SATable.insert(basicDBObject);
            }
        });
        txtInvestmentIS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentIS = true;
                selectInterestIS = false;
                selectYearsIS = false;
                selectFutureValueIS = false;
            }
        });
        txtInterestIS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentIS = false;
                selectInterestIS = true;
                selectYearsIS = false;
                selectFutureValueIS = false;
            }
        });
        txtYearsIS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentIS = false;
                selectInterestIS = false;
                selectYearsIS = true;
                selectFutureValueIS = false;
            }
        });
        txtFutureValueIS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentIS = false;
                selectInterestIS = false;
                selectYearsIS = false;
                selectFutureValueIS = true;
            }
        });
        //****************END****Interest Saving**************

        //**************************************************
        //*****************Loan*******************************
        //**************************************************
        btnCalculateLO = createButton("Calculate", 151.0, 289.0, 80.8, 25.6);
        //labels for loan
        lblLoanAmountLO = createLabel("Loan Amount", 61.0, 100.0);
        lblInterestLO = createLabel("Interest rate", 63.0, 146.0);
        lblYearsLO = createLabel("Time", 66.0, 194.0);
        lblFutureValueLO = createLabel("Monthly Pay", 62.0, 240.0);
        //TextFields for loan
        txtLoanAmountLO = createTextField("Present value", 151.0, 96.0, 148.8, 25.6);
        txtInterestLO = createTextField("(%)", 151.0, 142.0, 148.8, 25.6);
        txtYearsLO = createTextField("In Years", 151.0, 190.0, 148.8, 25.6);
        txtFutureValueLO = createTextField("Monthly Payment", 151.0, 236.0, 148.8, 25.6);
        //Add background image to loan
        Image image4 =new Image("com/imagers/Untitled design (2).png");
        ImageView imgLO = new ImageView();
        imgLO.setFitWidth(738);
        imgLO.setFitHeight(488);
        imgLO.setImage(image4);
        APaneLO.getChildren().add(imgLO);
        //Add labels to loan AnchorPane
        APaneLO.getChildren().add(lblLoanAmountLO);
        APaneLO.getChildren().add(lblInterestLO);
        APaneLO.getChildren().add(lblYearsLO);
        APaneLO.getChildren().add(lblFutureValueLO);
        //Add TextFields to loan AnchorPane
        APaneLO.getChildren().add(txtLoanAmountLO);
        APaneLO.getChildren().add(txtInterestLO);
        APaneLO.getChildren().add(txtYearsLO);
        APaneLO.getChildren().add(txtFutureValueLO);
        //Add Buttons to AnchorPAne
        APaneLO.getChildren().add(btnCalculateLO);
        //Add KeyBoard to loan AnchorPane
        keyBoardPaneLO = keyboard();
        APaneLO.getChildren().add(keyBoardPaneLO);
        database.createCollection("loanDetails", null);
        DBCollection LOTable = database.getCollection("loanDetails");
        DBCursor findIterableLO = LOTable.find();
        for (DBObject count : findIterableLO) {
            LOLast = (String) count.get("Last");
            if (LOLast.equals("Yes")) {
                txtLoanAmountLO.setText((String) count.get("LoanAmountLO"));
                txtInterestLO.setText((String) count.get("InterestLO"));
                txtYearsLO.setText((String) count.get("YearsLO"));
                txtFutureValueLO.setText((String) count.get("FutureValueLO"));
            }
        }
        btnCalculateLO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!txtLoanAmountLO.getText().equals("")&& !txtInterestLO.getText().equals("") && !txtYearsLO.getText().equals("") && txtFutureValueLO.getText().equals("")){
                        Double pV = Double.parseDouble(txtLoanAmountLO.getText());
                        Double r = (Double.parseDouble(txtInterestLO.getText()))/12/100;
                        int t = 12*(Integer.parseInt(txtYearsLO.getText()));
                        Double m=pV*r*Math.pow(1+r,t)/(Math.pow(1+r,t)-1);//future value
                        txtFutureValueLO.setText(df.format(m));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Monthly payment is "+df.format(m));
                        a.showAndWait();
                    }else if (!txtLoanAmountLO.getText().equals("")&& txtInterestLO.getText().equals("") && !txtYearsLO.getText().equals("") && !txtFutureValueLO.getText().equals("")) {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Interest rate should be known");
                        a.showAndWait();
                    }else if (txtLoanAmountLO.getText().equals("")&& !txtInterestLO.getText().equals("") && !txtYearsLO.getText().equals("") && !txtFutureValueLO.getText().equals("")) {
                        Double r = (Double.parseDouble(txtInterestLO.getText()))/12/100;
                        Double m = Double.parseDouble(txtFutureValueLO.getText());
                        int t = 12*(Integer.parseInt(txtYearsLO.getText()));
                        Double pV = m*(Math.pow(1+r,t)-1)/(r*Math.pow(1+r,t));//Investment
                        txtLoanAmountLO.setText(df.format(pV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("You are taking loan value is" + df.format(pV));
                        a.showAndWait();
                    }else if(txtYearsLO.getText().equals("")&&!txtLoanAmountLO.getText().equals("") && !txtInterestLO.getText().equals("") && !txtFutureValueLO.getText().equals("")){
                        Double pV = Double.parseDouble(txtLoanAmountLO.getText());
                        Double r = (Double.parseDouble(txtInterestLO.getText()))/12/100;
                        Double m = Double.parseDouble(txtFutureValueLO.getText());
                        Double t = 12*((Math.log((m/r)/((m/r)-pV))/Math.log(1+r)));//Time
                        txtYearsLO.setText(df.format(t));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("it will take " + df.format(t/12) + "years");
                        a.showAndWait();
                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Only one text filed can be empty please enter correct values ");
                        a.showAndWait();
                    }

                }catch (Exception e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Please re-cheque the input details and enter again");
                    a.showAndWait();

                }
                DBCollection SATable = database.getCollection("loanDetails");
                DBCursor findIterable = SATable.find();
                BasicDBObject basicDBObject = new BasicDBObject();
                for (DBObject count : findIterable) {
                    SALast = (String) count.get("Last");
                    if(SALast.equals("Yes")){
                        BasicDBObject queryForSaving= new BasicDBObject();
                        queryForSaving.put("Last", SALast);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("Last","No");
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        database.getCollection("loanDetails").update(queryForSaving,updateObject);
                    }
                }basicDBObject.put("Investment", txtLoanAmountLO.getText());
                basicDBObject.put("Interest", txtFutureValueLO.getText());
                basicDBObject.put("Years",txtYearsLO.getText());
                basicDBObject.put("FutureValue", txtFutureValueLO.getText());
                basicDBObject.put("Last","Yes");
                SATable.insert(basicDBObject);
            }
        });
        txtLoanAmountLO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentLO = true;
                selectInterestLO = false;
                selectYearsLO = false;
                selectFutureValueLO = false;
            }
        });
        txtInterestLO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentLO = false;
                selectInterestLO = true;
                selectYearsLO = false;
                selectFutureValueLO = false;
            }
        });
        txtYearsLO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentLO = false;
                selectInterestLO = false;
                selectYearsLO = true;
                selectFutureValueLO = false;
            }
        });
        txtFutureValueLO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentLO = false;
                selectInterestLO = false;
                selectYearsLO = false;
                selectFutureValueLO = true;
            }
        });
        //******************END****loan*********************************

        //*************************************************************
        //*****************Mortgage**************************************
        //*************************************************************
        btnCalculateMT = createButton("Calculate", 151.0, 289.0, 80.8, 25.6);
        //labels for Mortgage
        lblInvestmentMT = createLabel("Investment", 61.0, 100.0);
        lblInterestMT = createLabel("Interest rate", 63.0, 146.0);
        lblYearsMT = createLabel("Time", 66.0, 194.0);
        lblFutureValueMT = createLabel("Monthly Pay", 62.0, 240.0);
        //TextFields for Mortgage
        txtInvestmentMT = createTextField("Present value", 151.0, 96.0, 148.8, 25.6);
        txtInterestMT = createTextField("(%)", 151.0, 142.0, 148.8, 25.6);
        txtYearsMT = createTextField("In Years", 151.0, 190.0, 148.8, 25.6);
        txtFutureValueMT = createTextField("Monthly Payment", 151.0, 236.0, 148.8, 25.6);
        //Add image to background
        Image image5 =new Image("com/imagers/Untitled design (1).png");
        ImageView imgMT = new ImageView();
        imgMT.setFitWidth(738);
        imgMT.setFitHeight(488);
        imgMT.setImage(image5);
        imgMT.setStyle("-fx-background-blend-mode:lighten");
        APaneMT.getChildren().add(imgMT);
        //Add labels to Mortgage AnchorPane
        APaneMT.getChildren().add(lblInvestmentMT);
        APaneMT.getChildren().add(lblInterestMT);
        APaneMT.getChildren().add(lblYearsMT);
        APaneMT.getChildren().add(lblFutureValueMT);
        //Add TextFields to Mortgage AnchorPane
        APaneMT.getChildren().add(txtInvestmentMT);
        APaneMT.getChildren().add(txtInterestMT);
        APaneMT.getChildren().add(txtYearsMT);
        APaneMT.getChildren().add(txtFutureValueMT);
        //Add Buttons to AnchorPAne
        APaneMT.getChildren().add(btnCalculateMT);
        //Add KeyBoard to Mortgage AnchorPane
        keyBoardPaneMT = keyboard();
        APaneMT.getChildren().add(keyBoardPaneMT);
        database.createCollection("mortgageDetails", null);
        DBCollection MTTable = database.getCollection("mortgageDetails");
        DBCursor findIterableMT = MTTable.find();
        for (DBObject count : findIterableMT) {
            MTLast = (String) count.get("Last");
            if (MTLast.equals("Yes")) {
                txtInvestmentMT.setText((String) count.get("InvestmentMT"));
                txtInterestMT.setText((String) count.get("InterestMT"));
                txtYearsMT.setText((String) count.get("YearsMT"));
                txtFutureValueMT.setText((String) count.get("FutureValueMT"));
            }
        }btnCalculateMT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(txtFutureValueMT.getText().equals("") && !txtInterestMT.getText().equals("") && !txtYearsMT.getText().equals("") && !txtInvestmentMT.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestmentMT.getText());
                        Double r = (Double.parseDouble(txtInterestMT.getText()))/0.12;
                        int t = 12*(Integer.parseInt(txtYearsMT.getText()));
                        Double m=pV*r*Math.pow(1+r,t)/(Math.pow(1+r,t)-1);//monthly payment*
                        txtFutureValueMT.setText(df.format(m));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("You have to pay monthly "+df.format(m));
                        a.showAndWait();
                    }else if (!txtInvestmentMT.getText().equals("")&& txtInterestMT.getText().equals("") && !txtYearsMT.getText().equals("") && !txtFutureValueMT.getText().equals("")) {
                        Alert a = new Alert(Alert.AlertType.NONE);//
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Interest rate should be known");
                        a.showAndWait();
                    }else if (txtInvestmentMT.getText().equals("")&& !txtInterestMT.getText().equals("") && !txtYearsMT.getText().equals("") && !txtFutureValueMT.getText().equals("")) {
                        Double r = (Double.parseDouble(txtInterestMT.getText()))/12/100;
                        Double m = Double.parseDouble(txtFutureValueMT.getText());
                        int t =12*(Integer.parseInt(txtYears.getText()));
                        Double pV = m*(Math.pow(1+r,t)-1)/(r*Math.pow(1+r,t));//Investment
                        txtInvestmentMT.setText(df.format(pV));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("You need to invest" + df.format(pV) + "%");
                        a.showAndWait();
                    }else if(txtYearsMT.getText().equals("")&&!txtInvestmentMT.getText().equals("") && !txtInterestMT.getText().equals("") && !txtFutureValueMT.getText().equals("")){
                        Double pV = Double.parseDouble(txtInvestmentMT.getText());
                        Double r = (Double.parseDouble(txtInterestMT.getText()))/0.12;
                        Double m = Double.parseDouble(txtFutureValueMT.getText());
                        Double t = 12*(Math.log((m/r)/((m/r)-pV))/Math.log(1+r));//Time
                        txtYearsMT.setText(df.format(t));
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("it will take " + df.format(t/12) + "years");
                        a.showAndWait();
                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Only one text filed can be empty please enter correct values ");
                        a.showAndWait();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Please re-cheque the input details and enter again");
                    a.showAndWait();

                }
                DBCollection SATable = database.getCollection("mortgageDetails");
                DBCursor findIterable = SATable.find();
                BasicDBObject basicDBObject = new BasicDBObject();
                for (DBObject count : findIterable) {
                    SALast = (String) count.get("Last");
                    if(SALast.equals("Yes")){
                        BasicDBObject queryForSaving= new BasicDBObject();
                        queryForSaving.put("Last", SALast);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("Last","No");
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        database.getCollection("mortgageDetails").update(queryForSaving,updateObject);
                    }
                }basicDBObject.put("Investment", txtInvestmentMT.getText());
                basicDBObject.put("Interest", txtFutureValueMT.getText());
                basicDBObject.put("Years",txtYearsMT.getText());
                basicDBObject.put("FutureValue", txtFutureValueMT.getText());
                basicDBObject.put("Last","Yes");
                SATable.insert(basicDBObject);
            }
        });
        txtInvestmentMT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentMT = true;
                selectInterestMT = false;
                selectYearsMT = false;
                selectFutureValueMT = false;
            }
        });
        txtInterestMT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentMT = false;
                selectInterestMT = true;
                selectYearsMT = false;
                selectFutureValueMT = false;
            }
        });
        txtYearsMT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentMT = false;
                selectInterestMT = false;
                selectYearsMT = true;
                selectFutureValueMT = false;
            }
        });
        txtFutureValueMT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectInvestmentMT = false;
                selectInterestMT = false;
                selectYearsMT = false;
                selectFutureValueMT = true;
            }
        });
        //*************end mortgage*******************


    }
    public static Label createLabel(String promptText,Double x, Double y) {
        Label lbl = new Label(promptText);
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setStyle("-fx-font-size:15px;-fx-font-weight: bold;-fx-text-fill:white;");
        return lbl;
    }
    public static TextField createTextField(String promptText,Double x, Double y,Double scaleX, Double scaleY) {
        TextField txtField = new TextField();
        txtField.setPromptText(promptText);
        txtField.setLayoutX(x);
        txtField.setLayoutY(y);
        txtField.setPrefWidth(scaleX);
        txtField.setPrefHeight(scaleY);
        return txtField;
    }
    public static Button createButton(String Text,Double x,Double y,Double scaleX, Double scaleY){
        Button btn = new Button(Text);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(scaleX);
        btn.setPrefHeight(scaleY);
        //btn.getStylesheets().add("stylesheet.css");
        btn.setStyle("-fx-background-color: #c7ecee; -fx-text-fill: black;");

        return btn;
    }
    //Create a keyboard
    public AnchorPane keyboard(){
        AnchorPane APaneKeyBoard=new AnchorPane();
        APaneKeyBoard.setPrefWidth(266);
        APaneKeyBoard.setPrefHeight(296);
        APaneKeyBoard.setLayoutX(371);
        APaneKeyBoard.setLayoutY(1);
        Button key1=createButton("1",7.0,200.0,60.0,39.2);
        Button key2=createButton("2",74.0,200.0,59.2,38.4);
        Button key3=createButton("3",137.0,200.0,62.4,38.4);
        Button key4=createButton("4",7.0,153.0,61.6,38.4);
        Button key5=createButton("5",74.0,153.0,57.6,38.4);
        Button key6=createButton("6",137.0,153.0,62.4,38.4);
        Button key7=createButton("7",7.0,108.0,59.2,38.4);
        Button key8=createButton("8",74.0,108.0,58.4,38.4);
        Button key9=createButton("9",137.0,108.0,62.4,38.4);
        Button key0=createButton("0",7.0,248.0,60.0,38.4);
        Button btnErase=createButton("Erase",74.0,248.0,60.0,38.4);
        Button btnEnter=createButton("Enter",137.0,248.0,60.0,38.4);
        TextField txtKeyboard=createTextField("Enter Value to Enter",20.0,65.0,159.0,26.0);
        APaneKeyBoard.getChildren().add(key1);
        APaneKeyBoard.getChildren().add(key2);
        APaneKeyBoard.getChildren().add(key3);
        APaneKeyBoard.getChildren().add(key4);
        APaneKeyBoard.getChildren().add(key5);
        APaneKeyBoard.getChildren().add(key6);
        APaneKeyBoard.getChildren().add(key7);
        APaneKeyBoard.getChildren().add(key8);
        APaneKeyBoard.getChildren().add(key9);
        APaneKeyBoard.getChildren().add(key0);
        APaneKeyBoard.getChildren().add(btnErase);
        APaneKeyBoard.getChildren().add(btnEnter);
        APaneKeyBoard.getChildren().add(txtKeyboard);
        //

        //Set values to TextField
        key1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"1");
            }
        });
        key2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"2");
            }
        });
        key3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"3");
            }
        });
        key4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"4");
            }
        });        key5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"5");
            }
        });
        key6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"6");
            }
        });
        key7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"7");
            }
        });
        key8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"8");
            }
        });
        key9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"9");
            }
        });
        key0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyboard.setText(txtKeyboard.getText()+"0");
            }
        });
        btnErase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtKeyboard.getText();
                if (!(number.equals(""))) {
                    txtKeyboard.setText(number.substring(0, (number.length() - 1)));
                }
            }
        });
        btnEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //for saving
                if (selectInvestment==true){
                    txtInvestment.setText(txtKeyboard.getText());
                }else if(selectInterest==true){
                    txtInterest.setText(txtKeyboard.getText());
                }else if(selectYears==true){
                    txtYears.setText(txtKeyboard.getText());
                }else if(selectFutureValue==true) {
                    txtFutureValue.setText(txtKeyboard.getText());
                }else if(selectInvestmentIS==true){
                    txtInvestmentIS.setText(txtKeyboard.getText());
                }else if(selectInterestIS==true){
                    txtInterestIS.setText(txtKeyboard.getText());
                }else if(selectYearsIS==true){
                    txtYearsIS.setText(txtKeyboard.getText());
                }else if(selectFutureValueIS==true){
                    txtFutureValueIS.setText(txtKeyboard.getText());
                }else if(selectInvestmentLO==true){
                    txtLoanAmountLO.setText(txtKeyboard.getText());
                }else if(selectInterestLO==true){
                    txtInterestLO.setText(txtKeyboard.getText());
                }else if(selectYearsLO==true){
                    txtYearsLO.setText(txtKeyboard.getText());
                }else if(selectFutureValueLO==true){
                    txtFutureValueLO.setText(txtKeyboard.getText());
                }else if(selectInvestmentMT==true){
                    txtInvestmentMT.setText(txtKeyboard.getText());
                }else if(selectInterestMT==true){
                    txtInterestMT.setText(txtKeyboard.getText());
                }else if(selectYearsMT==true){
                    txtYearsMT.setText(txtKeyboard.getText());
                }else if(selectFutureValueMT==true){
                    txtFutureValueMT.setText(txtKeyboard.getText());
                }
            }
        });
        return APaneKeyBoard;
    }

}
