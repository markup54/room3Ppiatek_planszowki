package pl.zabrze.zs10.room3ppiatek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlanszowkaViewModel planszowkaViewModel;

    GranieDatabase granieDatabase;
    EditText editTextNazwa;
    EditText editTextMin;
    EditText editTextMax;
    Spinner spinnerKategoria;
    Button buttonDodaj;
    ListView listView;
    Button buttonWypisz;
    EditText editTextLiczbaGraczy;
    ArrayAdapter<Planszowka> adapter;
    List<Planszowka> szukaneGry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        planszowkaViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(PlanszowkaViewModel.class);
        granieDatabase = GranieDatabase.zwrocInstacjeBazy(getApplicationContext());
        buttonDodaj = findViewById(R.id.buttonDodajDoBazy);
        editTextNazwa = findViewById(R.id.editTextNazwa);
        editTextMin = findViewById(R.id.editTextNumberMinLiczbaGraczy);
        editTextMax = findViewById(R.id.editTextNumberMaxLiczbaGraczy);
        spinnerKategoria = findViewById(R.id.spinnerKategoria);
        buttonWypisz = findViewById(R.id.buttonWypisz);
        editTextLiczbaGraczy = findViewById(R.id.editTextLiczbaGraczy);
        listView = findViewById(R.id.listView);

        planszowkaViewModel.getPlanszowki().observe(this,
                new Observer<List<Planszowka>>() {
                    @Override
                    public void onChanged(List<Planszowka> planszowkas) {
                        adapter = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                planszowkas);
                        listView.setAdapter(adapter);
                    }

                });


        Planszowka planszowka = new Planszowka("Monopoly",2,6,"strategiczna");
        Planszowka planszowka1 = new Planszowka("Inis",3,4,"strategiczna");
        Planszowka planszowka2 = new Planszowka("Everdell",2,4,"ekonomiczna");
        Planszowka planszowka3 = new Planszowka("Poszukiwanie planety X", 1,4,"skręślanka");
        //granieDatabase.uzyjPlanszowkaDao().wstawDoBazy(planszowka);
        //granieDatabase.uzyjPlanszowkaDao().wstawDoBazyKilka(planszowka3,planszowka1,planszowka2);
        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nazwa = editTextNazwa.getText().toString();
                        int maks = Integer.valueOf(editTextMax.getText().toString());
                        int min = Integer.valueOf(editTextMin.getText().toString());
                        String kategoria = spinnerKategoria.getSelectedItem().toString();
                        Planszowka p = new Planszowka(nazwa,min,maks,kategoria);
                        planszowkaViewModel.wstawPlanszowke(p);
                    }
                }
        );
        buttonWypisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int liczbaGraczy = Integer.valueOf(editTextLiczbaGraczy.getText().toString());
                       /* szukaneGry = granieDatabase.uzyjPlanszowkaDao()
                                .wypiszPlanszowkiWedlugLiczbyGraczy(liczbaGraczy);
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,szukaneGry);
                        listView.setAdapter(adapter);*/
                    }
                }
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        planszowkaViewModel.usunPlanszowke(adapter.getItem(i));
                    }
                }
        );

    }
}