package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA();
        displayForTeamB();
    }

    public void addThreeForTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA();
    }

    public void addTwoForTeamA(View view) {
        scoreTeamA += 2;
        displayForTeamA();
    }

    public void addOneForTeamA(View view) {
        scoreTeamA++;
        displayForTeamA();
    }

    private void displayForTeamA() {
        TextView scoreTextView = (TextView) findViewById(R.id.team_a_score);
        scoreTextView.setText(scoreTeamA + "");
    }

    public void addThreeForTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB();
    }

    public void addTwoForTeamB(View view) {
        scoreTeamB += 2;
        displayForTeamB();
    }

    public void addOneForTeamB(View view) {
        scoreTeamB++;
        displayForTeamB();
    }

    private void displayForTeamB() {
        TextView scoreTextView = (TextView) findViewById(R.id.team_b_score);
        scoreTextView.setText(scoreTeamB + "");
    }

    public void resetScores(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA();
        displayForTeamB();
    }
}
