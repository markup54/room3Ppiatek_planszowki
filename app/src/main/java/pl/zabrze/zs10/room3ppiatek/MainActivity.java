package pl.zabrze.zs10.room3ppiatek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GranieDatabase granieDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        granieDatabase = GranieDatabase.zwrocInstacjeBazy(getApplicationContext());

        Planszowka planszowka = new Planszowka("Monopoly",2,6,"strategiczna");
        Planszowka planszowka1 = new Planszowka("Inis",3,4,"strategiczna");

    }
}