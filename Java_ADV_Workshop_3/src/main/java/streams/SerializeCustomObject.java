package streams;

import java.io.*;

public class SerializeCustomObject {
    public static void main(String[] args) throws Exception {
        //writing the object
        Course c = new Course("Computer Science", 12);
        OutputStream os = new FileOutputStream("course.ser");
        ObjectOutputStream oos = new ObjectOutputStream(os);

        oos.writeObject(c);

        //reading the object
        InputStream is = new FileInputStream("course.ser");
        ObjectInputStream ois = new ObjectInputStream(is);
        Course readFromFile = (Course) ois.readObject();

        System.out.printf("Course name: %s, with %d students", readFromFile.field, readFromFile.numberOfStudents);
    }
}

class Course implements Serializable {
    String field;
    Integer numberOfStudents;

    public Course() {
    }
    public Course(String field, Integer numberOfStudents) {
        this.field = field;
        this.numberOfStudents = numberOfStudents;
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        String serialized = String.format("Course name: %s with %d students", field, numberOfStudents);
        out.writeBytes(serialized);
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int current = 0;
        while ((current = in.read()) != -1) {
            baos.write(current);
        }

        String result = baos.toString();
        String[] tokens = result.split("\\s+");
        this.field = tokens[2];
        this.numberOfStudents = Integer.parseInt(tokens[4]);
    }
}
