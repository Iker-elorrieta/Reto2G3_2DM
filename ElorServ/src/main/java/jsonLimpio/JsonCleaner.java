package jsonLimpio;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import modelo.Centro;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class JsonCleaner {

    public static void limpiarJSON(String inputPath, String outputPath) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setLenient()
                    .registerTypeAdapter(Long.class, (JsonDeserializer<Long>) (json, type, ctx) -> {
                        try {
                            String s = json.getAsString().trim();
                            return s.isEmpty() ? 0L : Long.parseLong(s);
                        } catch (Exception e) {
                            return 0L;
                        }
                    })
                    .registerTypeAdapter(Integer.class, (JsonDeserializer<Integer>) (json, type, ctx) -> {
                        try {
                            String s = json.getAsString().trim();
                            return s.isEmpty() ? 0 : Integer.parseInt(s);
                        } catch (Exception e) {
                            return 0;
                        }
                    })
                    .registerTypeAdapter(Double.class, (JsonDeserializer<Double>) (json, type, ctx) -> {
                        try {
                            String s = json.getAsString().trim();
                            return s.isEmpty() ? 0.0 : Double.parseDouble(s);
                        } catch (Exception e) {
                            return 0.0;
                        }
                    })
                    .create();

            JsonObject root = gson.fromJson(new FileReader(inputPath), JsonObject.class);
            JsonArray array = root.getAsJsonArray("CENTROS");

            Type listType = new TypeToken<List<Centro>>(){}.getType();
            List<Centro> centros = gson.fromJson(array, listType);

            JsonObject nuevoRoot = new JsonObject();
            nuevoRoot.add("CENTROS", gson.toJsonTree(centros));

            FileWriter writer = new FileWriter(outputPath);
            gson.toJson(nuevoRoot, writer);
            writer.close();

            System.out.println("JSON limpio generado en: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
