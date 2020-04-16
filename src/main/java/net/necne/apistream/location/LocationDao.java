package net.necne.apistream.location;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class LocationDao {
    static final List<Location> LOCATIONS;

    static {
        List<Location> locations = new ArrayList<>();
        locations.add( Location.builder().id(1).sortCode(1).name("One").build() );
        locations.add( Location.builder().id(2).sortCode(2).name("Two").build() );

        LOCATIONS = new ImmutableList.Builder<Location>().addAll(locations).build();
    }

    public static List<Location> find() {
        return LOCATIONS;
    }

}