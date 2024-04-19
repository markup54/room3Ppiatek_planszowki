package pl.zabrze.zs10.room3ppiatek;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlanszowkiRepozytorium {
    private PlanszowkaDAO planszowkaDAO;
    private LiveData<List<Planszowka>> planszowki;

    public PlanszowkiRepozytorium(Application application){
        GranieDatabase db = GranieDatabase.zwrocInstacjeBazy(application);
        planszowkaDAO = db.uzyjPlanszowkaDao();
        planszowki = planszowkaDAO.wypiszWszystkiePlanszowki();
    }

    public LiveData<List<Planszowka>> getPlanszowki() {
        return planszowki;
    }

    void wstaw (Planszowka planszowka){
        GranieDatabase.databaseExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        planszowkaDAO.wstawDoBazy(planszowka);
                    }
                }
        );
    }
}
