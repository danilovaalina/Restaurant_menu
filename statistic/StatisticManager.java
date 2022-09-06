package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private  static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map <EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            for (Map.Entry<EventType, List<EventDataRow>> pair: storage.entrySet()) {
                if (pair.getKey() == data.getType()) {
                    pair.getValue().add(data);
                }
            }
        }

        public  Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<Date, Double> advertisementProfit() {
        List<EventDataRow> rows = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        TreeMap<Date, Double> advertisementProfit = (TreeMap<Date, Double>) Collections.reverseOrder();
        for (EventDataRow row : rows) {
            VideoSelectedEventDataRow videoRow = (VideoSelectedEventDataRow) row;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(videoRow.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            double sum = (double) videoRow.getAmount()/100;
            if (!advertisementProfit.containsKey(date)) {
                advertisementProfit.put(date, sum);
            }
        }
        return advertisementProfit;
    }

    public Map<Date, Double> getCookWorkLoadingMap() {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        List <EventDataRow> rows = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyy", Locale.ENGLISH);


    }
}
