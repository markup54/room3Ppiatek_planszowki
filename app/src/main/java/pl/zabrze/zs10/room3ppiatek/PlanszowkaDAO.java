package pl.zabrze.zs10.room3ppiatek;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanszowkaDAO {
    @Insert
    public void wstawDoBazy(Planszowka planszowka);

    @Query("SELECT * FROM planszowki")
    public List<Planszowka> wypiszWszystkiePlanszowki();

    @Query("SELECT * FROM planszowki WHERE minLiczbaGraczy<=:liczbaGraczy and maxLiczbaGraczy>=:liczbaGraczy ")
    public List<Planszowka> wypiszPlanszowkiWedlugLiczbyGraczy(int liczbaGraczy);
}
