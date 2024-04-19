package pl.zabrze.zs10.room3ppiatek;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlanszowkaViewModel extends AndroidViewModel {
    private PlanszowkiRepozytorium planszowkiRepozytorium;
    private final LiveData<List<Planszowka>> planszowki;

    public PlanszowkaViewModel(Application application) {
        super(application);
        this.planszowkiRepozytorium = new PlanszowkiRepozytorium(application);
        this.planszowki = planszowkiRepozytorium.getPlanszowki();
    }

    public LiveData<List<Planszowka>> getPlanszowki() {
        return planszowki;
    }

    public void wstawPlanszowke(Planszowka planszowka){
        planszowkiRepozytorium.wstaw(planszowka);
    }

    public void usunPlanszowke(Planszowka planszowka){
        planszowkiRepozytorium.usun(planszowka);
    }
}
