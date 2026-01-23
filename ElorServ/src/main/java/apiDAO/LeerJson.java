package apiDAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import modelo.Centro;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class LeerJson {

    private List<Centro> centros;

    public LeerJson() {
        try {
            Gson gson = new Gson();

            JsonObject root = gson.fromJson(new FileReader("EuskadiLatLon.json"), JsonObject.class); //	LECTURA DE JSON MEDIANTE GSON
            JsonArray array = root.getAsJsonArray("CENTROS");

            Type listType = new TypeToken<List<Centro>>(){}.getType();
            centros = gson.fromJson(array, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
// FUNCIONES PARA EJECUTAR EN EL CONTROLADOR MEDIANTE POST GET Y DEMÁS
    
    public List<Centro> getCentros() {
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
