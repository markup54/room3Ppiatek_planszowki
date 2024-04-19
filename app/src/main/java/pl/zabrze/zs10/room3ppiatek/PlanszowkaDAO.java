package pl.zabrze.zs10.room3ppiatek;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanszowkaDAO {
    @Insert
    public void wstawDoBazy(Planszowka planszowka);


    @Delete
    public void usunZBazy(Planszowka planszowka);

    @Insert
    public void wstawDoBazyKilka(Planszowka ... planszowki);

    @Query("SELECT * FROM planszowki")
    public LiveData<List<Planszowka>> wypiszWszystkiePlanszowki();

    @Query("SELECT * FROM planszowki WHERE minLiczbaGraczy<=:liczbaGraczy and maxLiczbaGraczy>=:liczbaGraczy ")
    public LiveData<List<Planszowka>> wypiszPlanszowkiWedlugLiczbyGraczy(int liczbaGraczy);
}
