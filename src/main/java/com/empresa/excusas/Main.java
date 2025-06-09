package com.empresa.excusas;

import com.empresa.excusas.clases.AdministradorProntuarios;
import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.Prontuario;
import com.empresa.excusas.clases.encargados.*;
import com.empresa.excusas.clases.modoOperacion.*;
import com.empresa.excusas.clases.tiposExcusas.*;
import com.empresa.excusas.clasesAbstractas.Empleado;
import com.empresa.excusas.clasesAbstractas.TipoExcusa;
import com.empresa.excusas.interfaces.Encargado;
import com.empresa.excusas.interfaces.ModoOperacion;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Empleado> empleados = new ArrayList<>();
    private static Encargado cadenaEncargados;

    // TRIVIALES: Demoras comunes
    private static final List<String> PALABRAS_TRIVIALES = Arrays.asList(
            "dormido", "dormir", "despertador", "alarma", "sueño", "cansado", "levantarse", "despertar",
            "quedé dormido", "me quedé dormido", "oversleep", "madrugada", "trasnoché", "trasnoche",
            "colectivo", "bondi", "micro", "ómnibus", "omnibus", "autobús", "autobus", "bus",
            "subte", "subway", "metro", "tren", "ferrocarril", "estación", "estacion", "parada",
            "transporte", "público", "publico", "perdí", "perdi", "no pasó", "no paso", "cancelado",
            "huelga", "paro", "demora", "retraso", "frecuencia", "servicio",
            "taxi", "uber", "cabify", "remis", "auto", "coche", "vehículo", "vehiculo", "moto",
            "bicicleta", "bici", "patineta", "scooter", "combustible", "nafta", "gasolina",
            "pinchadura", "pinchazo", "rueda", "llanta", "batería", "bateria", "arrancar",
            "reloj", "hora", "horario", "tiempo", "tarde", "temprano", "confundí", "confundi",
            "cambio de hora", "horario de verano", "horario de invierno",
            "tránsito", "transito", "embotellamiento", "congestion", "atasco", "cola",
            "choque", "colisión", "colision", "accidente de tránsito", "accidente vial",
            "obra", "construcción", "construccion", "reparación", "reparacion",
            "corte de calle", "desvío", "desvio", "calle cortada", "ruta cortada",
            "manifestación", "manifestacion", "protesta", "marcha", "piquete", "corte de ruta", "bloqueo",
            "evento", "festival", "concierto", "maratón", "maraton", "carrera", "competencia",
            "filmación", "filmacion", "grabación", "grabacion",
            "mudanza", "camión", "camion", "container", "contenedor", "grúa", "grua", "remolque",
            "delivery", "reparto", "carga", "descarga"
    );

    private static final List<String> PALABRAS_MODERADAS_LUZ = Arrays.asList(
            "luz", "corte", "electricidad", "apagón", "apagon", "blackout", "suministro", "energía", "energia",
            "edesur", "edenor", "edelap", "empresa eléctrica", "empresa electrica", "distribuidora",
            "transformador", "cables", "poste", "instalación", "instalacion", "cortocircuito",
            "sobrecarga", "falla eléctrica", "falla electrica", "sin luz", "oscuridad", "generador",
            "ups", "respaldo", "emergencia eléctrica", "emergencia electrica"
    );

    private static final List<String> PALABRAS_MODERADAS_FAMILIAR = Arrays.asList(
            "familiar", "familia", "pariente", "cuidar", "cuidado", "atender", "acompañar",
            "hijo", "hija", "niño", "niña", "bebé", "bebe", "padre", "madre", "papá", "papa", "mamá", "mama",
            "abuelo", "abuela", "hermano", "hermana", "tío", "tio", "tía", "tia", "primo", "prima",
            "suegro", "suegra", "cuñado", "cuñada", "yerno", "nuera", "esposo", "esposa", "pareja",
            "enfermo", "enferma", "enfermedad", "hospital", "clínica", "clinica", "médico", "medico",
            "doctor", "doctora", "emergencia", "urgencia", "ambulancia", "internación", "internacion",
            "cirugía", "cirugia", "operación", "operacion", "tratamiento", "medicamento", "medicina",
            "consulta", "turno médico", "turno medico", "análisis", "analisis", "estudio", "radiografía", "radiografia",
            "accidente", "lesión", "lesion", "fractura", "dolor", "malestar", "fiebre", "gripe", "covid",
            "coronavirus", "síntoma", "sintoma", "contagio", "cuarentena", "aislamiento"
    );

    // COMPLEJAS: Situaciones raras pero POSIBLES
    private static final List<String> PALABRAS_COMPLEJAS = Arrays.asList(
            "paloma", "pájaro", "pajaro", "ave", "gaviota", "cuervo", "loro", "perro", "gato",
            "rata", "ratón", "raton", "serpiente", "araña", "arana", "insecto", "abeja", "avispa",
            "hormiga", "cucaracha", "murciélago", "murcielago", "mapache", "zorro", "jabalí", "jabali",
            "robó", "robo", "robaron", "ladrón", "ladron", "delincuente", "asalto", "hurto", "sustrajeron",
            "cartera", "billetera", "celular", "teléfono", "telefono", "llaves", "documentos", "mochila", "bolso",
            "secuestro", "amenaza", "extorsión", "extorsion",
            "inundación", "inundacion", "tormenta", "granizo", "tornado", "huracán", "huracan",
            "terremoto", "sismo", "temblor", "alud", "avalancha", "derrumbe", "deslizamiento",
            "árbol", "arbol", "caído", "caido", "rama", "semáforo", "semaforo", "cartel", "letrero",
            "explosión", "explosion", "incendio", "fuego", "humo", "gas", "fuga",
            "balacera", "tiroteo", "bomba", "evacuación", "evacuacion", "cordón policial", "cordon policial",
            "emergencia química", "emergencia quimica", "derrame", "contaminación", "contaminacion"
    );

    // INVEROSÍMILES: Situaciones IMPOSIBLES/fantásticas
    private static final List<String> PALABRAS_INVEROSIMILES = Arrays.asList(
            "alien", "aliens", "extraterrestre", "extraterrestres", "ovni", "ufo", "platillo volador",
            "abducido", "abducida", "abducción", "abduccion", "secuestro alienígena", "secuestro alienigena",
            "nave espacial", "mothership", "experimento", "sonda", "rayo tractor",
            "fantasma", "espíritu", "espiritu", "aparición", "aparicion", "poltergeist", "ente", "alma",
            "zombie", "zombi", "muerto viviente", "no-muerto", "vampiro", "hombre lobo", "licántropo", "licantropo",
            "unicornio", "pegaso", "centauro", "minotauro", "sirena", "tritón", "triton", "ninfa",
            "dragón", "dragon", "serpiente gigante", "kraken", "leviatán", "leviatan", "hidra",
            "fénix", "fenix", "grifo", "hipogrifo", "quimera", "esfinge", "basilisco",
            "mago", "maga", "hechicero", "hechicera", "brujo", "bruja", "chamán", "chaman",
            "hechizo", "maleficio", "maldición", "maldicion", "conjuro", "encantamiento", "sortilegio",
            "varita", "báculo", "baculo", "cristal", "amuleto", "talismán", "talisman", "poción", "pocion",
            "portal", "dimensión", "dimension", "teletransporte", "teletransportación", "teletransportacion",
            "viaje en el tiempo", "máquina del tiempo", "maquina del tiempo", "paradoja temporal",
            "dios", "diosa", "deidad", "semidiós", "semidios", "ángel", "angel", "arcángel", "arcangel",
            "demonio", "diablo", "satanás", "satanas", "lucifer", "ente maligno", "posesión", "posesion",
            "exorcismo", "ritual", "invocación", "invocacion", "sacrificio", "ofrenda",
            "superhéroe", "superheroe", "superpoder", "volar", "vuelo", "invisibilidad", "invisible",
            "telepatía", "telepatia", "telequinesis", "telequinesia", "precognición", "precognicion",
            "rayos x", "visión", "vision", "fuerza sobrehumana", "velocidad sobrehumana", "inmortalidad",
            "robot", "androide", "cyborg", "inteligencia artificial", "ia", "matrix", "realidad virtual",
            "holografía", "holografia", "holograma", "rayo láser", "rayo laser", "campo de fuerza",
            "antigravedad", "levitación", "levitacion", "dispositivo alienígena", "dispositivo alienigena"
    );

    private static class AnalisisPalabras {
        public List<String> triviales = new ArrayList<>();
        public List<String> moderadasLuz = new ArrayList<>();
        public List<String> moderadasFamiliar = new ArrayList<>();
        public List<String> complejas = new ArrayList<>();
        public List<String> inverosimiles = new ArrayList<>();

        public boolean tieneMultiplesCategorias() {
            int categorias = 0;
            if (!triviales.isEmpty()) categorias++;
            if (!moderadasLuz.isEmpty()) categorias++;
            if (!moderadasFamiliar.isEmpty()) categorias++;
            if (!complejas.isEmpty()) categorias++;
            if (!inverosimiles.isEmpty()) categorias++;
            return categorias > 1;
        }

        public int getTotalPalabras() {
            return triviales.size() + moderadasLuz.size() + moderadasFamiliar.size() +
                    complejas.size() + inverosimiles.size();
        }
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("🏢 BIENVENIDO AL SISTEMA DE GESTIÓN DE EXCUSAS - EXCUSAS S.A. 🏢");
        System.out.println("=".repeat(80));

        inicializarSistema();
        cargarEmpleadosIniciales();

        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    procesarNuevaExcusa();
                    break;
                case 2:
                    procesarExcusaConAsistente();
                    break;
                case 3:
                    registrarNuevoEmpleado();
                    break;
                case 4:
                    mostrarEmpleados();
                    break;
                case 5:
                    mostrarCadenaEncargados();
                    break;
                case 6:
                    mostrarEjemplosExcusas();
                    break;
                case 7:
                    gestionarProntuarios();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("👋 ¡Gracias por usar el sistema de Excusas S.A.!");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Por favor, seleccione una opción del 1 al 8.");
            }

            if (continuar) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void inicializarSistema() {
        ModoOperacion normal = new ModoNormal();
        ModoOperacion vago = new ModoVago();
        ModoOperacion productivo = new ModoProductivo();

        Encargado recepcionista = new Recepcionista("Ana García", "ana@empresa.com", 100, normal);
        Encargado supervisor = new SupervisorArea("Carlos López", "carlos@empresa.com", 101, productivo);

        // Hacer que el modo vago sea aleatorio (50% de probabilidad)
        ModoOperacion modoGerenteRRHH = Math.random() < 0.5 ? vago : normal;
        Encargado gerenteRRHH = new GerenteRRHH("María Rodríguez", "maria@empresa.com", 102, modoGerenteRRHH);

        Encargado ceo1 = new CEO("Roberto Silva", "roberto@empresa.com", 103, normal);
        Encargado ceo2 = new CEO("Laura Martínez", "laura@empresa.com", 104, normal);
        Encargado encargadoPorDefecto = new EncargadoPorDefecto("Sistema", "sistema@empresa.com", 999, normal);

        recepcionista.setSiguiente(supervisor);
        supervisor.setSiguiente(gerenteRRHH);
        gerenteRRHH.setSiguiente(ceo1);
        ceo1.setSiguiente(ceo2);
        ceo2.setSiguiente(encargadoPorDefecto);

        cadenaEncargados = recepcionista;

        System.out.println("✅ Sistema inicializado correctamente.");
        System.out.println("📋 Cadena de encargados configurada.");
        System.out.println("🤖 Asistente inteligente con detección de categorías mixtas activado.");
        System.out.println("🎲 Modo del Gerente RRHH: " + (modoGerenteRRHH instanceof ModoVago ? "VAGO" : "NORMAL"));
    }

    private static void cargarEmpleadosIniciales() {
        empleados.add(new Empleado("Juan Pérez", "juan@empresa.com", 1));
        empleados.add(new Empleado("Sofia González", "sofia@empresa.com", 2));
        empleados.add(new Empleado("Diego Morales", "diego@empresa.com", 3));
        empleados.add(new Empleado("Valentina Torres", "valentina@empresa.com", 4));
        empleados.add(new Empleado("Carlos Mendoza", "carlos.mendoza@empresa.com", 5));

        System.out.println("👥 Empleados iniciales cargados.");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 MENÚ PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. 📝 Procesar excusa (modo manual)");
        System.out.println("2. 🤖 Procesar excusa (con asistente inteligente)");
        System.out.println("3. 👤 Registrar nuevo empleado");
        System.out.println("4. 👥 Ver empleados registrados");
        System.out.println("5. 🔗 Ver cadena de encargados");
        System.out.println("6. 💡 Ver ejemplos de excusas por categoría");
        System.out.println("7. 📋 Gestionar prontuarios");
        System.out.println("8. 🚪 Salir");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void procesarNuevaExcusa() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📝 PROCESAR NUEVA EXCUSA (MODO MANUAL)");
        System.out.println("=".repeat(50));

        Empleado empleadoSeleccionado = seleccionarEmpleado();
        if (empleadoSeleccionado == null) return;

        System.out.print("\n📄 Ingrese la descripción de la excusa: ");
        String descripcion = scanner.nextLine().trim();

        if (descripcion.isEmpty()) {
            System.out.println("❌ La descripción no puede estar vacía.");
            return;
        }

        TipoExcusa tipoExcusa = seleccionarTipoExcusa(descripcion);
        if (tipoExcusa == null) return;

        procesarExcusa(empleadoSeleccionado, descripcion, tipoExcusa);
    }

    private static void procesarExcusaConAsistente() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🤖 PROCESAR EXCUSA CON ASISTENTE INTELIGENTE");
        System.out.println("=".repeat(50));

        Empleado empleadoSeleccionado = seleccionarEmpleado();
        if (empleadoSeleccionado == null) return;

        System.out.print("\n📄 Ingrese la descripción de la excusa: ");
        String descripcion = scanner.nextLine().trim();

        if (descripcion.isEmpty()) {
            System.out.println("❌ La descripción no puede estar vacía.");
            return;
        }

        AnalisisPalabras analisis = analizarPalabrasCompleto(descripcion);

        System.out.println("\n🤖 ANÁLISIS AUTOMÁTICO AVANZADO:");
        System.out.println("📄 Descripción: \"" + descripcion + "\"");
        System.out.println("=".repeat(60));

        mostrarAnalisisDetallado(analisis);

        TipoExcusa tipoExcusa;

        if (analisis.tieneMultiplesCategorias()) {
            System.out.println("\n⚠️ DETECCIÓN DE CATEGORÍAS MIXTAS");
            System.out.println("Se detectaron palabras de múltiples categorías.");
            tipoExcusa = manejarCategoriasMultiples(descripcion, analisis);
        } else {
            tipoExcusa = clasificarExcusaAutomaticamente(descripcion, analisis);
            System.out.println("🏷️ Tipo detectado: " + tipoExcusa.getClass().getSimpleName());
            System.out.println("🎯 Será procesada por: " + obtenerEncargadoResponsable(tipoExcusa));
        }

        if (tipoExcusa == null) return;

        System.out.print("\n¿Confirma la clasificación? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (!confirmacion.equals("s") && !confirmacion.equals("si")) {
            System.out.println("🔄 Seleccione manualmente el tipo de excusa:");
            tipoExcusa = seleccionarTipoExcusa(descripcion);
            if (tipoExcusa == null) return;
        }

        procesarExcusa(empleadoSeleccionado, descripcion, tipoExcusa);
    }

    private static AnalisisPalabras analizarPalabrasCompleto(String descripcion) {
        String descripcionLower = descripcion.toLowerCase();
        AnalisisPalabras analisis = new AnalisisPalabras();

        for (String palabra : PALABRAS_TRIVIALES) {
            if (descripcionLower.contains(palabra)) {
                analisis.triviales.add(palabra);
            }
        }
        for (String palabra : PALABRAS_MODERADAS_LUZ) {
            if (descripcionLower.contains(palabra)) {
                analisis.moderadasLuz.add(palabra);
            }
        }
        for (String palabra : PALABRAS_MODERADAS_FAMILIAR) {
            if (descripcionLower.contains(palabra)) {
                analisis.moderadasFamiliar.add(palabra);
            }
        }
        for (String palabra : PALABRAS_COMPLEJAS) {
            if (descripcionLower.contains(palabra)) {
                analisis.complejas.add(palabra);
            }
        }
        for (String palabra : PALABRAS_INVEROSIMILES) {
            if (descripcionLower.contains(palabra)) {
                analisis.inverosimiles.add(palabra);
            }
        }

        return analisis;
    }

    private static void mostrarAnalisisDetallado(AnalisisPalabras analisis) {
        System.out.println("🔍 PALABRAS CLAVE DETECTADAS:");

        if (!analisis.triviales.isEmpty()) {
            System.out.println("   😴 Triviales: " + String.join(", ", analisis.triviales));
        }
        if (!analisis.moderadasLuz.isEmpty()) {
            System.out.println("   ⚡ Moderadas (Luz): " + String.join(", ", analisis.moderadasLuz));
        }
        if (!analisis.moderadasFamiliar.isEmpty()) {
            System.out.println("   ❤️ Moderadas (Familiar): " + String.join(", ", analisis.moderadasFamiliar));
        }
        if (!analisis.complejas.isEmpty()) {
            System.out.println("   🤔 Complejas: " + String.join(", ", analisis.complejas));
        }
        if (!analisis.inverosimiles.isEmpty()) {
            System.out.println("   👽 Inverosímiles: " + String.join(", ", analisis.inverosimiles));
        }

        if (analisis.getTotalPalabras() == 0) {
            System.out.println("   ❓ No se detectaron palabras clave específicas");
        }
    }

    private static TipoExcusa manejarCategoriasMultiples(String descripcion, AnalisisPalabras analisis) {
        System.out.println("\n🎯 REGLAS DE PRIORIDAD PARA CATEGORÍAS MIXTAS:");
        System.out.println("1. 👽 Inverosímil tiene máxima prioridad");
        System.out.println("2. 🤔 Compleja tiene segunda prioridad");
        System.out.println("3. ⚡❤️ Moderadas tienen tercera prioridad");
        System.out.println("4. 😴 Trivial tiene menor prioridad");

        TipoExcusa sugerencia = null;
        String razonamiento = "";

        if (!analisis.inverosimiles.isEmpty()) {
            sugerencia = new ExcusaInverosimil(descripcion);
            razonamiento = "Contiene elementos inverosímiles: " + String.join(", ", analisis.inverosimiles);
        } else if (!analisis.complejas.isEmpty()) {
            sugerencia = new ExcusaCompleja(descripcion);
            razonamiento = "Contiene elementos complejos: " + String.join(", ", analisis.complejas);
        } else if (!analisis.moderadasLuz.isEmpty() || !analisis.moderadasFamiliar.isEmpty()) {
            sugerencia = new ExcusaModerada(descripcion);
            List<String> moderadas = new ArrayList<>();
            moderadas.addAll(analisis.moderadasLuz);
            moderadas.addAll(analisis.moderadasFamiliar);
            razonamiento = "Contiene elementos moderados: " + String.join(", ", moderadas);
        } else if (!analisis.triviales.isEmpty()) {
            sugerencia = new ExcusaTrivial(descripcion);
            razonamiento = "Contiene elementos triviales: " + String.join(", ", analisis.triviales);
        }

        System.out.println("\n🤖 CLASIFICACIÓN SUGERIDA:");
        System.out.println("🏷️ Tipo: " + sugerencia.getClass().getSimpleName());
        System.out.println("💭 Razonamiento: " + razonamiento);
        System.out.println("🎯 Encargado: " + obtenerEncargadoResponsable(sugerencia));

        System.out.println("\n❓ OPCIONES:");
        System.out.println("1. ✅ Aceptar sugerencia automática");
        System.out.println("2. 🔄 Seleccionar manualmente");
        System.out.print("Seleccione una opción (1-2): ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion == 1) {
                return sugerencia;
            } else if (opcion == 2) {
                return seleccionarTipoExcusa(descripcion);
            } else {
                System.out.println("❌ Opción inválida. Usando sugerencia automática.");
                return sugerencia;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Usando sugerencia automática.");
            return sugerencia;
        }
    }

    private static TipoExcusa clasificarExcusaAutomaticamente(String descripcion, AnalisisPalabras analisis) {
        if (!analisis.inverosimiles.isEmpty()) {
            return new ExcusaInverosimil(descripcion);
        } else if (!analisis.complejas.isEmpty()) {
            return new ExcusaCompleja(descripcion);
        } else if (!analisis.moderadasLuz.isEmpty() || !analisis.moderadasFamiliar.isEmpty()) {
            return new ExcusaModerada(descripcion);
        } else if (!analisis.triviales.isEmpty()) {
            return new ExcusaTrivial(descripcion);
        } else {
            return new ExcusaCompleja(descripcion);
        }
    }

    private static String obtenerEncargadoResponsable(TipoExcusa tipoExcusa) {
        if (tipoExcusa instanceof ExcusaTrivial) {
            return "Recepcionista";
        } else if (tipoExcusa instanceof ExcusaModerada) {
            return "Supervisor de Área";
        } else if (tipoExcusa instanceof ExcusaCompleja) {
            return "Gerente de RRHH";
        } else if (tipoExcusa instanceof ExcusaInverosimil) {
            return "CEO";
        }
        return "Encargado por Defecto";
    }

    private static void procesarExcusa(Empleado empleado, String descripcion, TipoExcusa tipoExcusa) {
        Excusa excusa = new Excusa(empleado, tipoExcusa);

        System.out.println("\n" + "🔄 PROCESANDO EXCUSA...");
        System.out.println("=".repeat(80));
        System.out.println("👤 Empleado: " + empleado.getNombre() + " (Legajo: " + empleado.getLegajo() + ")");
        System.out.println("📧 Email: " + empleado.getEmail());
        System.out.println("📄 Excusa: " + descripcion);
        System.out.println("🏷️ Tipo: " + tipoExcusa.getClass().getSimpleName());
        System.out.println("🎯 Encargado responsable: " + obtenerEncargadoResponsable(tipoExcusa));
        System.out.println("=".repeat(80));

        cadenaEncargados.manejarExcusa(excusa);

        System.out.println("=".repeat(80));
        System.out.println("✅ Excusa procesada exitosamente.");
    }

    private static Empleado seleccionarEmpleado() {
        System.out.println("\n👥 SELECCIONAR EMPLEADO:");

        for (int i = 0; i < empleados.size(); i++) {
            Empleado emp = empleados.get(i);

            List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();
            long cantidadProntuarios = prontuarios.stream()
                    .filter(p -> p.getLegajoEmpleado() == emp.getLegajo())
                    .count();

            String indicadorProntuarios = cantidadProntuarios > 0 ?
                    " 📋(" + cantidadProntuarios + " prontuario" + (cantidadProntuarios > 1 ? "s" : "") + ")" : "";

            System.out.println((i + 1) + ". " + emp.getNombre() + " (" + emp.getEmail() + ") - Legajo: " +
                    emp.getLegajo() + indicadorProntuarios);
        }

        System.out.print("\nSeleccione el número del empleado: ");

        try {
            int seleccion = Integer.parseInt(scanner.nextLine());
            if (seleccion >= 1 && seleccion <= empleados.size()) {
                return empleados.get(seleccion - 1);
            } else {
                System.out.println("❌ Selección inválida.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, ingrese un número válido.");
            return null;
        }
    }

    private static TipoExcusa seleccionarTipoExcusa(String descripcion) {
        System.out.println("\n🏷️ SELECCIONAR TIPO DE EXCUSA:");
        System.out.println("1. 😴 Trivial (me quedé dormido, perdí el transporte)");
        System.out.println("2. ⚡ Moderada (corte de luz, cuidado familiar)");
        System.out.println("3. 🤔 Compleja (situaciones extrañas pero posibles)");
        System.out.println("4. 👽 Inverosímil (aliens, situaciones imposibles)");

        System.out.print("\nSeleccione el tipo de excusa (1-4): ");

        try {
            int tipo = Integer.parseInt(scanner.nextLine());

            switch (tipo) {
                case 1:
                    return new ExcusaTrivial(descripcion);
                case 2:
                    return new ExcusaModerada(descripcion);
                case 3:
                    return new ExcusaCompleja(descripcion);
                case 4:
                    return new ExcusaInverosimil(descripcion);
                default:
                    System.out.println("❌ Tipo de excusa inválido.");
                    return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, ingrese un número válido.");
            return null;
        }
    }

    private static void gestionarProntuarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 GESTIÓN DE PRONTUARIOS");
        System.out.println("=".repeat(60));
        System.out.println("1. 📊 Ver todos los prontuarios");
        System.out.println("2. 👤 Ver prontuarios por empleado");
        System.out.println("3. 📈 Estadísticas de prontuarios");
        System.out.println("4. 🔍 Buscar prontuario por legajo");
        System.out.println("5. ⬅️ Volver al menú principal");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1:
                mostrarTodosProntuarios();
                break;
            case 2:
                mostrarProntuariosPorEmpleado();
                break;
            case 3:
                mostrarEstadisticasProntuarios();
                break;
            case 4:
                buscarProntuarioPorLegajo();
                break;
            case 5:
                return;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    private static void mostrarTodosProntuarios() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("📊 TODOS LOS PRONTUARIOS REGISTRADOS");
        System.out.println("=".repeat(70));

        List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();

        if (prontuarios.isEmpty()) {
            System.out.println("📭 No hay prontuarios registrados.");
            System.out.println("💡 Los prontuarios se crean cuando un CEO aprueba excusas inverosímiles.");
            return;
        }

        for (int i = 0; i < prontuarios.size(); i++) {
            Prontuario p = prontuarios.get(i);
            System.out.println((i + 1) + ". 📋 PRONTUARIO #" + (i + 1));
            System.out.println("   👤 Empleado: " + p.getNombreEmpleado());
            System.out.println("   📧 Email: " + p.getEmailEmpleado());
            System.out.println("   🔢 Legajo: " + p.getLegajoEmpleado());
            System.out.println("   📄 Excusa: " + p.getExcusa().getTipoExcusa().getDescripcion());
            System.out.println("   🏷️ Tipo: " + p.getExcusa().getTipoExcusa().getClass().getSimpleName());
            System.out.println("   " + "-".repeat(50));
        }

        System.out.println("\n📊 Total de prontuarios: " + prontuarios.size());
    }

    private static void mostrarProntuariosPorEmpleado() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👤 PRONTUARIOS POR EMPLEADO");
        System.out.println("=".repeat(60));

        Empleado empleadoSeleccionado = seleccionarEmpleado();
        if (empleadoSeleccionado == null) return;

        List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();
        List<Prontuario> prontuariosEmpleado = prontuarios.stream()
                .filter(p -> p.getLegajoEmpleado() == empleadoSeleccionado.getLegajo())
                .collect(Collectors.toList());

        System.out.println("\n📋 PRONTUARIOS DE " + empleadoSeleccionado.getNombre().toUpperCase());
        System.out.println("=".repeat(60));

        if (prontuariosEmpleado.isEmpty()) {
            System.out.println("✅ Este empleado no tiene prontuarios.");
            System.out.println("💡 Los prontuarios se crean cuando un CEO aprueba excusas inverosímiles.");
            return;
        }

        for (int i = 0; i < prontuariosEmpleado.size(); i++) {
            Prontuario p = prontuariosEmpleado.get(i);
            System.out.println((i + 1) + ". 📄 " + p.getExcusa().getTipoExcusa().getDescripcion());
            System.out.println("   🏷️ Tipo: " + p.getExcusa().getTipoExcusa().getClass().getSimpleName());
            System.out.println();
        }

        System.out.println("📊 Total de prontuarios para " + empleadoSeleccionado.getNombre() + ": " + prontuariosEmpleado.size());

        if (prontuariosEmpleado.size() >= 3) {
            System.out.println("⚠️ ALERTA: Este empleado tiene " + prontuariosEmpleado.size() + " prontuarios.");
            System.out.println("   Considere una reunión con RRHH para evaluar la situación.");
        }
    }

    private static void mostrarEstadisticasProntuarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📈 ESTADÍSTICAS DE PRONTUARIOS");
        System.out.println("=".repeat(60));

        List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();

        if (prontuarios.isEmpty()) {
            System.out.println("📭 No hay prontuarios para mostrar estadísticas.");
            return;
        }

        System.out.println("📊 RESUMEN GENERAL:");
        System.out.println("   📋 Total de prontuarios: " + prontuarios.size());

        long empleadosConProntuarios = prontuarios.stream()
                .mapToInt(Prontuario::getLegajoEmpleado)
                .distinct()
                .count();
        System.out.println("   👥 Empleados con prontuarios: " + empleadosConProntuarios);

        if (!prontuarios.isEmpty()) {
            var empleadoMasProntuarios = prontuarios.stream()
                    .collect(Collectors.groupingBy(Prontuario::getNombreEmpleado, Collectors.counting()))
                    .entrySet().stream()
                    .max(java.util.Map.Entry.comparingByValue())
                    .orElse(null);

            if (empleadoMasProntuarios != null) {
                System.out.println("   🏆 Empleado más 'creativo': " + empleadoMasProntuarios.getKey() +
                        " (" + empleadoMasProntuarios.getValue() + " prontuarios)");
            }
        }

        System.out.println("\n📋 DETALLE POR EMPLEADO:");
        prontuarios.stream()
                .collect(Collectors.groupingBy(Prontuario::getNombreEmpleado, Collectors.counting()))
                .forEach((nombre, cantidad) ->
                        System.out.println("   • " + nombre + ": " + cantidad + " prontuario(s)")
                );
    }

    private static void buscarProntuarioPorLegajo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🔍 BUSCAR PRONTUARIO POR LEGAJO");
        System.out.println("=".repeat(50));

        System.out.print("🔢 Ingrese el número de legajo: ");

        try {
            int legajo = Integer.parseInt(scanner.nextLine());

            List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();
            List<Prontuario> prontuariosEncontrados = prontuarios.stream()
                    .filter(p -> p.getLegajoEmpleado() == legajo)
                    .collect(Collectors.toList());

            if (prontuariosEncontrados.isEmpty()) {
                System.out.println("❌ No se encontraron prontuarios para el legajo " + legajo);

                boolean empleadoExiste = empleados.stream().anyMatch(emp -> emp.getLegajo() == legajo);
                if (empleadoExiste) {
                    Empleado empleado = empleados.stream()
                            .filter(emp -> emp.getLegajo() == legajo)
                            .findFirst().get();
                    System.out.println("✅ El empleado " + empleado.getNombre() + " existe pero no tiene prontuarios.");
                } else {
                    System.out.println("❌ No existe un empleado con el legajo " + legajo);
                }
                return;
            }

            Prontuario primer = prontuariosEncontrados.get(0);
            System.out.println("\n📋 PRONTUARIOS ENCONTRADOS PARA:");
            System.out.println("👤 " + primer.getNombreEmpleado() + " (Legajo: " + legajo + ")");
            System.out.println("📧 " + primer.getEmailEmpleado());
            System.out.println("=".repeat(50));

            for (int i = 0; i < prontuariosEncontrados.size(); i++) {
                Prontuario p = prontuariosEncontrados.get(i);
                System.out.println((i + 1) + ". 📄 " + p.getExcusa().getTipoExcusa().getDescripcion());
                System.out.println("   🏷️ " + p.getExcusa().getTipoExcusa().getClass().getSimpleName());
                System.out.println();
            }

            System.out.println("📊 Total: " + prontuariosEncontrados.size() + " prontuario(s)");

        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, ingrese un número de legajo válido.");
        }
    }

    private static void mostrarEjemplosExcusas() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💡 EJEMPLOS DE EXCUSAS POR CATEGORÍA");
        System.out.println("=".repeat(60));

        System.out.println("😴 EXCUSAS TRIVIALES (Procesadas por Recepcionista):");
        System.out.println("   • Me quedé dormido");
        System.out.println("   • Perdí el colectivo");
        System.out.println("   • Había mucho tránsito");
        System.out.println("   • Obras en la calle");
        System.out.println("   • Manifestación cortó el paso");
        System.out.println("   • Choque en la autopista");
        System.out.println();

        System.out.println("⚡ EXCUSAS MODERADAS (Procesadas por Supervisor de Área):");
        System.out.println("   • Se cortó la luz en todo el barrio");
        System.out.println("   • Tuve que cuidar a un familiar enfermo");
        System.out.println("   • Hubo un apagón en mi zona");
        System.out.println("   • Mi hijo tuvo una emergencia médica");
        System.out.println("   • Falla en el suministro eléctrico");
        System.out.println();

        System.out.println("🤔 EXCUSAS COMPLEJAS (Procesadas por Gerente de RRHH):");
        System.out.println("   • Una paloma robó mi bicicleta");
        System.out.println("   • Se cayó un árbol y bloqueó mi calle");
        System.out.println("   • Hubo una inundación en mi barrio");
        System.out.println("   • Me robaron el auto en la madrugada");
        System.out.println("   • Una serpiente bloqueó mi entrada");
        System.out.println("   • Hubo un incendio en el edificio");
        System.out.println();

        System.out.println("👽 EXCUSAS INVEROSÍMILES (Procesadas por CEO):");
        System.out.println("   • Fui abducido por aliens");
        System.out.println("   • Un unicornio bloqueó mi camino");
        System.out.println("   • Me convertí en zombie temporalmente");
        System.out.println("   • Un mago me hechizó");
        System.out.println("   • Viajé en el tiempo por accidente");
        System.out.println("   • Un dragón quemó mi casa");
        System.out.println();

        System.out.println("🔀 EJEMPLOS DE CATEGORÍAS MIXTAS:");
        System.out.println("   • \"Mi familiar se convirtió en unicornio\"");
        System.out.println("     → Familiar (moderada) + Unicornio (inverosímil) = INVEROSÍMIL");
        System.out.println("   • \"Un alien cortó la luz de mi casa\"");
        System.out.println("     → Alien (inverosímil) + Luz (moderada) = INVEROSÍMIL");
        System.out.println("   • \"Una paloma robó mi auto mientras había tránsito\"");
        System.out.println("     → Paloma+robo (compleja) + Tránsito (trivial) = COMPLEJA");
        System.out.println("   • \"Había obras porque se cayó un árbol\"");
        System.out.println("     → Obras (trivial) + Árbol caído (compleja) = COMPLEJA");
    }

    private static void registrarNuevoEmpleado() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("👤 REGISTRAR NUEVO EMPLEADO");
        System.out.println("=".repeat(50));

        System.out.print("📝 Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        System.out.print("📧 Email: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty() || !email.contains("@")) {
            System.out.println("❌ Email inválido.");
            return;
        }

        System.out.print("🔢 Número de legajo: ");

        try {
            int legajo = Integer.parseInt(scanner.nextLine());

            boolean legajoExiste = empleados.stream().anyMatch(emp -> emp.getLegajo() == legajo);
            if (legajoExiste) {
                System.out.println("❌ Ya existe un empleado con ese número de legajo.");
                return;
            }

            Empleado nuevoEmpleado = new Empleado(nombre, email, legajo);
            empleados.add(nuevoEmpleado);

            System.out.println("✅ Empleado registrado exitosamente:");
            System.out.println("   👤 " + nombre);
            System.out.println("   📧 " + email);
            System.out.println("   🔢 Legajo: " + legajo);

        } catch (NumberFormatException e) {
            System.out.println("❌ El legajo debe ser un número válido.");
        }
    }

    private static void mostrarEmpleados() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("👥 EMPLEADOS REGISTRADOS");
        System.out.println("=".repeat(50));

        if (empleados.isEmpty()) {
            System.out.println("📭 No hay empleados registrados.");
            return;
        }

        List<Prontuario> prontuarios = AdministradorProntuarios.getInstancia().getProntuarios();

        for (int i = 0; i < empleados.size(); i++) {
            Empleado emp = empleados.get(i);

            long cantidadProntuarios = prontuarios.stream()
                    .filter(p -> p.getLegajoEmpleado() == emp.getLegajo())
                    .count();

            System.out.println((i + 1) + ". 👤 " + emp.getNombre());
            System.out.println("   📧 " + emp.getEmail());
            System.out.println("   🔢 Legajo: " + emp.getLegajo());

            if (cantidadProntuarios > 0) {
                System.out.println("   📋 Prontuarios: " + cantidadProntuarios);
                if (cantidadProntuarios >= 3) {
                    System.out.println("   ⚠️ ATENCIÓN: Empleado con múltiples prontuarios");
                }
            } else {
                System.out.println("   ✅ Sin prontuarios");
            }
            System.out.println();
        }
    }

    private static void mostrarCadenaEncargados() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🔗 CADENA DE ENCARGADOS Y SUS RESPONSABILIDADES");
        System.out.println("=".repeat(70));

        System.out.println("1. 🏢 RECEPCIONISTA (Modo: Normal)");
        System.out.println("   👤 Ana García - ana@empresa.com");
        System.out.println("   📋 Acepta: Excusas triviales (dormido, transporte)");
        System.out.println();

        System.out.println("2. 👨‍💼 SUPERVISOR DE ÁREA (Modo: Productivo)");
        System.out.println("   👤 Carlos López - carlos@empresa.com");
        System.out.println("   📋 Acepta: Excusas moderadas (corte luz, familiar)");
        System.out.println();

        System.out.println("3. 👔 GERENTE DE RRHH (Modo: Vago)");
        System.out.println("   👤 María Rodríguez - maria@empresa.com");
        System.out.println("   📋 Acepta: Excusas complejas (paloma, accidentes)");
        System.out.println();

        System.out.println("4. 🎩 CEO (Modo: Normal)");
        System.out.println("   👤 Roberto Silva - roberto@empresa.com");
        System.out.println("   👤 Laura Martínez - laura@empresa.com");
        System.out.println("   📋 Acepta: Excusas extremadamente inverosímiles");
        System.out.println();

        System.out.println("5. ❌ ENCARGADO POR DEFECTO");
        System.out.println("   👤 Sistema - sistema@empresa.com");
        System.out.println("   📋 Respuesta: 'Excusa rechazada: necesitamos pruebas contundentes'");
    }
}
