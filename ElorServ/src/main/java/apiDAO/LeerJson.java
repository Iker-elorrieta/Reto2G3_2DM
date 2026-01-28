package apiDAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import modelo.Centro;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class LeerJson {

    private static List<Centro> centros;

    public LeerJson() {
        try {
            Gson gson = new Gson();

            // Cargar JSON desde el classpath (funciona dentro del JAR)
            InputStream is = getClass().getClassLoader().getResourceAsStream("EuskadiLatLon.json");

            if (is == null) {
                throw new RuntimeException("No se encontró el archivo EuskadiLatLon.json en resources");
            }

            JsonObject root = gson.fromJson(new InputStreamReader(is), JsonObject.class);
            JsonArray array = root.getAsJsonArray("CENTROS");

            Type listType = new TypeToken<List<Centro>>(){}.getType();
            centros = gson.fromJson(array, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Centro> getCentros() {
        return centros;
    }

    public void crearCentro(Centro c) {
        centros.add(c);
    }

    public void actualizarCentro(int id, Centro centro) {
        for (int i = 0; i < centros.size(); i++) {
            if (centros.get(i).getCCEN().equals(id)) {
                centros.set(i, centro);
                return;
            }
        }
    }

    public void eliminarCentro(int id) {
        centros.removeIf(c -> c.getCCEN().equals(id));
    }
}
