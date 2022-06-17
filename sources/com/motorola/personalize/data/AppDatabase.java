package com.motorola.personalize.data;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import com.motorola.personalize.model.OptionEntity;
import java.util.List;

public abstract class AppDatabase extends RoomDatabase {

    public interface OptionDao {
        void deleteAll();

        void deleteColor(int i);

        void deleteOptions(OptionEntity... optionEntityArr);

        LiveData<List<OptionEntity>> getAllOptions();

        long insertOption(OptionEntity optionEntity);

        void insertOptions(OptionEntity... optionEntityArr);

        List<OptionEntity> loadAllOptions();

        void updateOptions(OptionEntity... optionEntityArr);
    }

    public abstract OptionDao getDao();
}
