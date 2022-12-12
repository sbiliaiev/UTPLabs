import entities.Person;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;;

public class BinaryParser {

    static public void writeToBinary(List<Person> personList, String binaryFilePath) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(binaryFilePath));

        for (Person person : personList) {
            dos.writeInt(person.getId());
            dos.flush();
            dos.writeUTF(person.getFirstName());
            dos.flush();
            dos.writeUTF(person.getSurname());
            dos.writeLong(person.getBirthdate().getTime());
        }

        dos.close();
    }

    static public List<Person> readFromBinary(String binaryFilePath) throws IOException, ParseException {
        DataInputStream dis = new DataInputStream(new FileInputStream(binaryFilePath));

        List<Person> list = new ArrayList<>();

        while (dis.available() > 0) {
            int id = dis.readInt();
            String firstName = dis.readUTF();
            String lastName = dis.readUTF();
            long birthDateTime = dis.readLong();
            Date birthDate = new Date(birthDateTime);

            Person person = new Person(firstName, lastName, birthDate, id);

            list.add(person);
        }

        return list;
    }
}
