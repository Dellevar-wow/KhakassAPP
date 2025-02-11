package com.elena.myapplication;
import com.opencsv.CSVReader;

import android.content.Context;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReaderHelper {
    private static final String TAG = "CSVReaderHelper";

    public static List<AnimalItem> readCSVFromAssets(Context context, String fileName) {
        List<AnimalItem> itemList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader reader = new CSVReader(isr);

            String[] columns;
            boolean firstLine = true;
            while ((columns = reader.readNext()) != null) {
                if (firstLine) { // Пропускаем заголовки
                    firstLine = false;
                    continue;
                }

                if (columns.length < 2) continue; // Проверка на достаточное количество столбцов
                // тут не забудь колонки добавить нужные
                String name = columns[4].trim();
                List<String> imageUrls = Arrays.asList(columns[3].trim().split(", "));

                itemList.add(new AnimalItem(name, imageUrls));

                // Выводим считанные данные в Logcat
                //Log.d(TAG, "Прочитано: " + name + " - " + imageUrls);
            }

            reader.close();
        } catch (Exception e) {
            Log.e(TAG, "Ошибка при чтении CSV: " + e.getMessage());
        }
        return itemList;
    }
}
