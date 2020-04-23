package net.necne.apistream.location;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class LocationDao {
    static final List<Location> LOCATIONS;

    static {
        int id = 0;
        List<Location> locations = new ArrayList<>();
        locations.add( Location.builder().id(++id).sortCode(1).name("One").state(LocationState.AVAILABLE).build() );
        locations.add( Location.builder().id(++id).sortCode(2).name("Two").state(LocationState.LOCKED).build() );
        locations.add( Location.builder().id(++id).sortCode(5).name("Three").state(LocationState.AUDIT_NEEDED).build());
        locations.add( Location.builder().id(++id).sortCode(6).name("Four").state(LocationState.AUDIT_LOCKED).build());

        LOCATIONS = new ImmutableList.Builder<Location>().addAll(locations).build();
    }

    public static List<Location> find() {
        return LOCATIONS;
    }

}