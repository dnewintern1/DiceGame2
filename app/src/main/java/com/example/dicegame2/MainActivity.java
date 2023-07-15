package com.example.dicegame2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final SecureRandom secureRandomNo = new SecureRandom();

    private enum Status {NOTSTARTEDYET, PROCEED , WON , LOST};

    private static final int TIGER_CLAWS = 2;
    private static final int TREE = 3;
    private static final int CEVEN =7;
    private static final int WE_LEVEN = 11;
    private static final int PANTHER = 12;

    TextView txtCalculation;
    ImageView imgdice;
    Button btnroll;

    String oldtxtCalculationValue = " ";
    boolean firsttime= true;
    Status gameStatus = Status.NOTSTARTEDYET;
    int points;

    private void makeImgDiceInvisible(){
        imgdice.setVisibility(View.INVISIBLE);
    }

    private void makeImgDicVisible(){
        imgdice.setVisibility(View.VISIBLE);

    }

    private void makeButtonInvisible(){
       // btnroll.setVisibility(INVISIBLE);
       btnroll.setVisibility(View.INVISIBLE);
    }

    private void makeButtonVisible(){
        btnroll.setVisibility(View.VISIBLE);
    }

    private int letsRollTheDice(){
        int roundDice1 = 1 + secureRandomNo.nextInt(6);
        int roundDice2 = 1+ secureRandomNo.nextInt(6);

        int sum = roundDice1 + roundDice2;
        txtCalculation.setText(String.format(oldtxtCalculationValue + "the rolled dice %d + %d = %d",roundDice1,roundDice2,sum));

    return sum;}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCalculation = findViewById(R.id.txtCalculation);
       TextView txtStatusBar = findViewById(R.id.txtStatusBar);
        imgdice = findViewById(R.id.imgdice);
        Button btnroll = findViewById(R.id.btnroll);

        //makeButtonInvisible();

        txtStatusBar.setText("");
        txtCalculation.setText("");

        imgdice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameStatus==Status.NOTSTARTEDYET){
                    int dicesum= letsRollTheDice();

                    if (gameStatus== Status.NOTSTARTEDYET){
                       oldtxtCalculationValue = txtCalculation.getText().toString();
                        points=0;
                        switch (dicesum){

                            case CEVEN:
                            case WE_LEVEN:
                                gameStatus = Status.WON;
                                txtStatusBar.setText("you have won!!!");

                               // makeButtonInvisible();
                                break;

                            case TREE:
                            case TIGER_CLAWS:
                            case PANTHER:
                                gameStatus= Status.LOST;
                                txtStatusBar.setText("you have LOst!");


                                break;

                            default:
                                gameStatus= Status.PROCEED;
                                points = dicesum;

                                txtCalculation.setText(oldtxtCalculationValue + " your points is " + points );

                                oldtxtCalculationValue="your point is" + points  + "/n";
                                break;
                        }
                        return;

                    }
                    if(gameStatus==Status.PROCEED){
                    //    int dicesum = letsRollTheDice();
                        if(dicesum==points){
                            gameStatus= Status.WON;
                            txtStatusBar.setText("you Won");

                        }
                        else if (dicesum == CEVEN) {

                            gameStatus= Status.LOST;

                            txtStatusBar.setText("yuo lost!");




                        }
                    }
                }
            }
        });

        btnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameStatus= Status.NOTSTARTEDYET;
                txtStatusBar.setText("");
                txtCalculation.setText("");
                oldtxtCalculationValue= "";
            }
        });






    }


}