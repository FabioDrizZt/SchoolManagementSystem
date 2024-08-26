public class Main {
    public static void main(String[] args) {
        GestorAcademico gestor = new GestorAcademico();

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez", "2000-01-01", Estudiante.Estado.MATRICULADO);
        Estudiante estudiante2 = new Estudiante(2, "María", "Gómez", "2001-02-02", Estudiante.Estado.MATRICULADO);

        // Crear cursos
        Curso curso1 = new Curso(1, "Matemáticas", "Curso básico de matemáticas", 5, "1.0");
        Curso curso2 = new Curso(2, "Programación", "Curso básico de programación", 4, "1.0");

        // Matricular estudiantes
        gestor.matricularEstudiante(estudiante1);
        gestor.matricularEstudiante(estudiante2);

        // Agregar cursos
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        try {
            // Inscribir estudiantes en cursos
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
            gestor.inscribirEstudianteCurso(estudiante2, curso1.getId());
            
            // Intentar inscribir nuevamente a un estudiante en el mismo curso
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
        } catch (EstudianteYaInscritoException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Desinscribir estudiantes de cursos
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());
            
            // Intentar desinscribir a un estudiante que no está inscrito
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}
