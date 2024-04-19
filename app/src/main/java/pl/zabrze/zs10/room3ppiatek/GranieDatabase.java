package pl.zabrze.zs10.room3ppiatek;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Planszowka.class},exportSchema = false, version = 1)
public abstract class GranieDatabase extends RoomDatabase {

    private static volatile GranieDatabase instancja;

    public abstract PlanszowkaDAO uzyjPlanszowkaDao();
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);

    public static GranieDatabase zwrocInstacjeBazy(Context context){
        if(instancja == null) {
            synchronized (GranieDatabase.class) {
                if (instancja == null) {
                    instancja = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    GranieDatabase.class,
                                    "granie_db"
                            )
                            //.allowMainThreadQueries()
                            //.fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instancja;
    }


}
