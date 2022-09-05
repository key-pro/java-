package keisanki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author SC
 */
public class Dentaku extends Application {

   private Label dsp;

   private Button[][] btnsA;
   private Button[]   btnsB;

   private Button     btnsZero;
   private Button     btnEQ;
   private Button     btnClear;

   private HBox[] hboxs;
   private VBox root;

   private long num1;
   private long num2;
   private char kigo = ' ';
   

    @Override
    public void start(Stage primaryStage) {

       root = new VBox();
       root.setAlignment(Pos.CENTER);
      // root.setSpacing(10);
       root.setSpacing(3);
       root.setPadding(new Insets(5,5,5,5));
       root.getChildren().add(dsp);
       root.getChildren().addAll(hboxs);

       /////////////////////////////////////
        Scene scene = new Scene(root, 300, 300);

        btnClear.requestFocus();/////////////////////




        primaryStage.setScene(scene);
        primaryStage.setTitle("電卓");
        primaryStage.getIcons().add(new Image("dentaku.jpg"));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

 @Override
public void init(){


       //表示窓
       dsp =new Label("0");//初期値
       dsp.setFont(new Font(20));//文字サイズ
       dsp.setTextFill(Color.BLUE);//文字の色
       dsp.setPrefWidth(300);//幅
       dsp.setPrefHeight(30);//高さ
       dsp.setPadding(new Insets(10, 10, 10, 10));
       dsp.setAlignment(Pos.CENTER_RIGHT);//文字の配置
      dsp.setBackground(new Background(new BackgroundFill( Color.AQUA , new CornerRadii(10.0), Insets.EMPTY)));
      Border border =
      new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID, new CornerRadii(10.0),BorderWidths.DEFAULT));
     dsp.setBorder(border);// 枠線

           btnsA = new Button[3][3];// ボタン作成　[0]->[７８９]　[1]->[４５６]　[2]->[１２３]
              for(int i=0; i<btnsA.length; i++){
                  for(int j=0; j<btnsA[i].length; j++){
                      int z=7-i*3+j;
                      btnsA[i][j]=new Button(Integer.toString(z));//ボタンの表示
                      btnsA[i][j].setId(Integer.toString(z));//ボタンのID（名前)
                      btnsA[i][j].setPrefWidth(60);

                        //ボタンをクリックしたときの　イベント
                      	btnsA[i][j].setOnAction(new ButtonEventHandler());

                  }
              }

           btnsB = new Button[4];// ボタン作成　[0]->[―]　[1]->[＋]　[2]->[×] [3]->[÷]
           btnsB[0] = new Button("―");
           btnsB[0].setId("-");
           btnsB[0].setPrefWidth(60);
            //ボタンをクリックしたときの　イベント
           btnsB[0].setOnAction(new KIGOEventHandler());

           btnsB[1] = new Button("＋");
           btnsB[1].setId("+");
           btnsB[1].setPrefWidth(60);
            //ボタンをクリックしたときの　イベント
           btnsB[1].setOnAction(new KIGOEventHandler());

           btnsB[2] = new Button("×");
           btnsB[2].setId("*");
           btnsB[2].setPrefWidth(60);
            //ボタンをクリックしたときの　イベント
           btnsB[2].setOnAction(new KIGOEventHandler());

           btnsB[3] = new Button("÷");
           btnsB[3].setId("/");
           btnsB[3].setPrefWidth(60);
          //ボタンをクリックしたときの　イベント
           btnsB[3].setOnAction(new KIGOEventHandler());

          //　数字　０　ボタン
          btnsZero = new Button(Integer.toString(0));
          btnsZero.setId(Integer.toString(0));
          btnsZero.setPrefWidth(200);
          //ボタンをクリックしたときの　イベント
          btnsZero.setOnAction(new ZeroEventHandler());
          	// ＝ ボタン
           btnEQ =  new Button("＝");
           btnEQ.setId("eq");
           btnEQ.setPrefWidth(130);
            //ボタンをクリックしたときの　イベント
           btnEQ.setOnAction(new EQEventHandler());

           //clearボタン
           btnClear =   new Button("Clear");
           btnClear.setId("clear");
           btnClear.setPrefWidth(130);
            //ボタンをクリックしたときの　イベント
           btnClear.setOnAction(new ClearEventHandler());

          hboxs = new HBox[5];//

         for(int i=0; i<hboxs.length - 2; i++){
          hboxs[i]= new HBox();
          hboxs[i].setAlignment(Pos.CENTER);
          hboxs[i].setSpacing(10);
          hboxs[i].setPadding(new Insets(5,5,5,5));
          hboxs[i].getChildren().addAll(btnsA[i]);
          hboxs[i].getChildren().add(btnsB[i]);
      }
          hboxs[3] = new HBox();
          hboxs[3].setAlignment(Pos.CENTER);
          hboxs[3].setSpacing(10);
          hboxs[3].setPadding(new Insets(5,5,5,5));
          hboxs[3].getChildren().addAll(btnsZero,btnsB[3]);
          hboxs[4] = new HBox();
          hboxs[4].setAlignment(Pos.CENTER);
          hboxs[4].setSpacing(10);
          hboxs[4].setPadding(new Insets(5,5,5,5));
          hboxs[4].getChildren().addAll( btnEQ, btnClear);

}


//////カンマ編集
  private static String numFormat(Long num){

    return String.format("%,d", num);

  }

  private static String numFormat(String numstr){

     Long num  = Long.valueOf(numstr);

    return String.format("%,d", num);

  }

  class ButtonEventHandler implements EventHandler<ActionEvent>{
	  public void handle(ActionEvent e) {
		  Button btn = (Button) e.getSource();
		  Long num = Long.parseLong(btn.getId());
		  if(kigo==' ') {
		  num1 = num1*10+num;
		  dsp.setText(numFormat(num1));
		  }
		  else if(kigo!=' '){
		  num2 = num2*10+num;
		  dsp.setText(numFormat(num2));
		  }
	  }
  }

  class ZeroEventHandler implements EventHandler<ActionEvent>{
	  public void handle(ActionEvent e) {
		  Button btn = (Button)e.getSource();

		  if(kigo == ' ') {
		      num1 = num1*10;
		  	  dsp.setText(numFormat(num1));
		  }else if(kigo!=' ') {
			  num2 = num2*10;
			  dsp.setText(numFormat(num2));
		  }

	  }
  }


  class KIGOEventHandler implements EventHandler<ActionEvent>{
	  public void handle(ActionEvent e) {
		  Button btn = (Button)e.getSource();
		  String ekigo = btn.getId();
		  kigo = ekigo.charAt(0);
		  dsp.setText("");
		  System.out.println(kigo);
	  }
  }

  class EQEventHandler implements EventHandler<ActionEvent>{
	  public void handle(ActionEvent e) {
		  Button btn = (Button)e.getSource();
		  long kekka=0;
		  if(kigo == '-') {
			  kekka = num1 - num2;
			 dsp.setText(numFormat(kekka));
		  }else if(kigo == '+') {
			  kekka = num1 + num2;
			 dsp.setText(numFormat(kekka));
		  }else if(kigo == '*') {
			  kekka = num1 * num2;
			 dsp.setText(numFormat(kekka));
		  }else if(kigo == '/') {
			  kekka = num1 / num2;
			 dsp.setText(numFormat(kekka));
		  }
		  num1 = kekka;
		  num2 = 0;
		  kigo = ' ';
	  }


  }

  class ClearEventHandler implements EventHandler<ActionEvent>{
	  public void handle(ActionEvent e) {
		  Button btn = (Button)e.getSource();

		  num1 = 0;
		  num2 = 0;
		  kigo = ' ';
		  dsp.setText("0");

	  }
  }

}
