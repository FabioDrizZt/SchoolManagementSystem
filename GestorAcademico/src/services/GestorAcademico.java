import java.util.ArrayList;
import java.util.HashMap;


public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        ArrayList<Estudiante> estudiantesInscritos = inscripciones.get(idCurso);
        if (estudiantesInscritos == null) {
            estudiantesInscritos = new ArrayList<>();
            inscripciones.put(idCurso, estudiantesInscritos);
        }

        if (estudiantesInscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en el curso.");
        }

        estudiantesInscritos.add(estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        ArrayList<Estudiante> estudiantesInscritos = inscripciones.get(idCurso);
        if (estudiantesInscritos == null || estudiantesInscritos.stream().noneMatch(e -> e.getId() == idEstudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso.");
        }

        estudiantesInscritos.removeIf(e -> e.getId() == idEstudiante);
    }
}
