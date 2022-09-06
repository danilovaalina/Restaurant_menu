package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> bestAdvertisements = null;
    private long bestAmount;
    private int bestDuration = Integer.MAX_VALUE;


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        VideoSelectedEventDataRow row = new VideoSelectedEventDataRow(bestAdvertisements, bestAmount, bestDuration);
        StatisticManager.getInstance().register(row);
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        List<Advertisement> advertisements = storage.list().stream()
                .filter(advertisement -> advertisement.getHits() > 0)
                .collect(Collectors.toList());
        makeAllAdvertisements(advertisements);
        bestAdvertisements
                .sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
                        .thenComparingInt(Advertisement::getDuration)
                        .reversed());
        bestAdvertisements.forEach(advertisement -> {
            ConsoleHelper.writeMessage(advertisement.toString());
            advertisement.revalidate();
        });

    }

    private int calcDuration (List<Advertisement> advertisements) {
        return advertisements.stream().mapToInt(Advertisement::getDuration).sum();
    }

    private long calcAmount (List<Advertisement> advertisements) {
        return advertisements.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
    }

    private void checkSet (List<Advertisement> advertisements) {
        int newDuration = calcDuration(advertisements);
        long newAmount = calcAmount(advertisements);

        if (bestAdvertisements == null && newDuration <= timeSeconds) {
            bestAdvertisements = advertisements;
            bestAmount = newAmount;
            bestDuration = newDuration;
        } else {
            if (newDuration <= timeSeconds) {
                if (newAmount > bestAmount) {
                    bestAdvertisements = advertisements;
                    bestAmount = newAmount;
                    bestDuration = newDuration;
                }

                if (newAmount == bestAmount) {
                    if (newDuration > bestDuration) {
                        bestAdvertisements = advertisements;
                        bestAmount = newAmount;
                        bestDuration = newDuration;
                    }

                    if (newDuration == bestDuration && advertisements.size() < bestAdvertisements.size()) {
                        bestAdvertisements = advertisements;
                        bestAmount = newAmount;
                        bestDuration = newDuration;
                    }
                }

            }
        }
    }

    private void makeAllAdvertisements(List<Advertisement> advertisements) {
        if (advertisements.size() > 0) {
            checkSet(advertisements);
        }

        for (int i = 0; i < advertisements.size(); i++) {
            List<Advertisement> newAdvertisements = new ArrayList<>(advertisements);
            newAdvertisements.remove(i);
            makeAllAdvertisements(newAdvertisements);
        }
    }


}
