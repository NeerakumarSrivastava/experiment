package com.home.java.letcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoverMaxDistination {

    public static void main(String[] args) {
        int location[] = {14, 12, 16, 27, 11, 15, 17};
        int maxtime = 40;
        Map<Integer, List<Location>> listMap = new HashMap<>();

        // Index
        for (int i = location.length - 1; i > -1; i--) {
            List<Location> list = new ArrayList<>();
            Location location1 = new Location(1, location[i]);
            list.add(location1);
            listMap.put(i, list);
            int k = i + 1;
            while (k < location.length) {
                List<Location> list1=listMap.get(k);
                for(Location location2:list1)
                {



                }
                k++;
            }


        }


    }

    static class Location {
        public int getCoverLocation() {
            return coverLocation;
        }

        public void setCoverLocation(int coverLocation) {
            this.coverLocation = coverLocation;
        }

        public int getTimeconsume() {
            return timeconsume;
        }

        public void setTimeconsume(int timeconsume) {
            this.timeconsume = timeconsume;
        }

        private int coverLocation;
        private int timeconsume;

        Location(int coverLocation, int timeconsume) {
            this.coverLocation = coverLocation;
            this.timeconsume = timeconsume;
        }

    }


}
