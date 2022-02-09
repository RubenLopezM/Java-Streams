import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Fichero {
    public static void main(String[] arg) {

        try {
            FileReader entrada = new FileReader("personas.txt");
            BufferedReader mibuffer= new BufferedReader(entrada);

            List<Persona> personas = new ArrayList<>();

            String linea="";
            String nombre;
            String poblacion;
            int edad;


            while (linea!= null) {
                linea=mibuffer.readLine();
                if (linea!=null){
                    try {
                        nombre = linea.split(":")[0];
                        poblacion = linea.split(":")[1];
                        edad = Integer.parseInt(linea.split(":")[2]);

                        Optional <String> optNombre= Optional.ofNullable(nombre);
                        Optional <String> optPoblacion = Optional.ofNullable(poblacion);
                        Optional <Integer> optEdad = Optional.ofNullable(edad);


                        if (optPoblacion.get() == "")
                            poblacion = "Desconocida";

                        if (optNombre.isPresent() && optPoblacion.isPresent() && optEdad.isPresent()){
                            Persona persona = new Persona(nombre, poblacion, edad);
                            personas.add(persona);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }

            }
            personas.stream().filter(persona -> persona.getEdad() < 25).forEach(System.out::println);
            entrada.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

