package net.necne.apistream.location.api;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.necne.apistream.location.Location;

public class GsonDownstream {

    private static final Gson GSON_1;
    private static final Gson GSON_2;

    static {
        GsonBuilder gb = new GsonBuilder()
        .setPrettyPrinting()
        ;

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = new JsonObject();
                jo.addProperty("key", src.getId());
                jo.addProperty("name", src.getName());
				return jo;
			}

        });
        GSON_1 = gb.create();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = GSON_1.toJsonTree(src).getAsJsonObject();
                jo.addProperty("sortCode", src.getSortCode());
				return jo;
			}

        });
        GSON_2 = gb.create();

    }

    public static Gson getGson(int version) {
        switch(version){
            case 1: 
                return GSON_1;
            case 2:
                return GSON_2;
            default: 
                throw new IllegalArgumentException("Unsupported version " + version);
        }
    }

}