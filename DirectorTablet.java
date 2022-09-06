import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;


public class DirectorTablet {

    public void printAdvertisementProfit() {
        Map<Date, Double> advertisementProfit = StatisticManager.getInstance().advertisementProfit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        double sum = advertisementProfit.values().stream().mapToDouble(Double::doubleValue).sum();
        for (Map.Entry<Date, Double> pair : advertisementProfit.entrySet()) {
            if (pair.getValue() > 0) {
                ConsoleHelper.writeMessage(pair.getKey() + " - " + pair.getValue());
                ConsoleHelper.writeMessage("Total - " + sum);
            }
        }
    }

    public void printCookWorkloading() {


    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
