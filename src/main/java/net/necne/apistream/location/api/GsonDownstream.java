package net.necne.apistream.location.api;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.necne.apistream.location.Location;
import net.necne.apistream.location.LocationState;

public class GsonDownstream {

    private static final Gson GSON_1;
    private static final Gson GSON_2;
    private static final Gson GSON_3;
    private static final Gson GSON_4;

    static {
        GsonBuilder gb = new GsonBuilder().setPrettyPrinting();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = new JsonObject();
                jo.addProperty("key", src.getId());
                jo.addProperty("name", LocationUtil.getLegacyNameLabel(src));
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

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = GSON_2.toJsonTree(src).getAsJsonObject();
                jo.addProperty("name", src.getName());
                jo.addProperty("status", src.getState().getLabel());
				return jo;
			}

        });
        GSON_3 = gb.create();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = GSON_3.toJsonTree(src).getAsJsonObject();
                jo.remove("status");
                jo.addProperty("changeQuantity", src.getState().isChangeQuantity());
                jo.addProperty("pendingReconciliation", src.getState().isPendingReconciliation());
				return jo;
			}

        });
        GSON_4 = gb.create();

    }

    public static Gson getGson(int version) {
        switch(version){
            case 4: return GSON_4;
            case 3: return GSON_3;
            case 2: return GSON_2;
            case 1: return GSON_1;
            default: 
                throw new IllegalArgumentException("Unsupported version " + version);
        }
    }

}