import java.util.*;

class Patient {
    String name;
    int severity;
    int arrivalTime;

    Patient(String name, int severity, int arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return name + " (Mức " + severity + ", đến lúc " + arrivalTime + ")";
    }
}

public class B5 {
    public static void main(String[] args) {
        Comparator<Patient> uuTien = Comparator
                .comparingInt((Patient p) -> p.severity)
                .thenComparingInt(p -> p.arrivalTime)
                .thenComparing(p -> p.name);

        Set<Patient> hangDoi = new TreeSet<>(uuTien);

        hangDoi.add(new Patient("Bệnh nhân A", 3, 800));
        hangDoi.add(new Patient("Bệnh nhân B", 1, 815));
        hangDoi.add(new Patient("Bệnh nhân C", 1, 805));

        for (Patient p : hangDoi) {
            System.out.println(p);
        }
    }
}