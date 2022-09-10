package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {

    }

    public List<Advertisement> getActiveVideos() {
        List<Advertisement> activeVideos = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.isActive()) {
                activeVideos.add(advertisement);
            }
        }
        return activeVideos;
    }

    public List<Advertisement> getNotActiveVideos() {
        List<Advertisement> activeVideos = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (!advertisement.isActive()) {
                activeVideos.add(advertisement);
            }
        }
        return activeVideos;
    }


}
