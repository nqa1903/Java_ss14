import java.util.*;

abstract class Person {
    protected String id;
    protected String name;
    protected int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public abstract void displayInfo();
}

class Patient extends Person implements Comparable<Patient> {
    private String phone;

    public Patient(String id, String name, int age, String phone) {
        super(id, name, age);
        this.phone = phone;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public int compareTo(Patient o) {
        return this.name.compareToIgnoreCase(o.name);
    }

    @Override
    public void displayInfo() {
        System.out.println(id + " | " + name + " | " + age + " | " + phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient)) return false;
        Patient p = (Patient) obj;
        return id.equals(p.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

interface Manageable<T> {
    void add(T t);
    void update(String id);
    void delete(String id);
    void displayAll();
}

class MedicalRecord {
    private String recordId;
    private String diagnosis;
    private String date;

    public MedicalRecord(String recordId, String diagnosis, String date) {
        this.recordId = recordId;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public String getRecordId() { return recordId; }
    public String getDiagnosis() { return diagnosis; }
    public String getDate() { return date; }

    public void display() {
        System.out.println(recordId + " | " + diagnosis + " | " + date);
    }
}

class PatientService implements Manageable<Patient> {

    private Set<Patient> patients = new HashSet<>();
    private Map<String, List<MedicalRecord>> recordMap = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void add(Patient p) {
        if (patients.add(p)) System.out.println("Them thanh cong");
        else System.out.println("ID da ton tai");
    }

    @Override
    public void update(String id) {
        Patient p = findById(id);
        if (p == null) {
            System.out.println("Khong tim thay");
            return;
        }
        System.out.print("Ten moi: ");
        p.setName(sc.nextLine());
        System.out.print("Tuoi moi: ");
        p.setAge(Integer.parseInt(sc.nextLine()));
        System.out.print("SDT moi: ");
        p.setPhone(sc.nextLine());
        System.out.println("Da cap nhat");
    }

    @Override
    public void delete(String id) {
        Patient p = findById(id);
        if (p != null) {
            patients.remove(p);
            recordMap.remove(id);
            System.out.println("Da xoa");
        } else System.out.println("Khong tim thay");
    }

    @Override
    public void displayAll() {
        if (patients.isEmpty()) System.out.println("Danh sach rong");
        else patients.forEach(Patient::displayInfo);
    }

    public Patient findById(String id) {
        for (Patient p : patients)
            if (p.getId().equalsIgnoreCase(id))
                return p;
        return null;
    }

    public void searchByName(String keyword) {
        patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(Patient::displayInfo);
    }

    public void sortByName() {
        List<Patient> list = new ArrayList<>(patients);
        Collections.sort(list);
        list.forEach(Patient::displayInfo);
    }

    public void sortByAge() {
        List<Patient> list = new ArrayList<>(patients);
        list.sort((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        list.forEach(Patient::displayInfo);
    }

    public void sortById() {
        List<Patient> list = new ArrayList<>(patients);
        list.sort((a, b) -> a.getId().compareToIgnoreCase(b.getId()));
        list.forEach(Patient::displayInfo);
    }

    public void addRecord(String patientId, MedicalRecord r) {
        recordMap.computeIfAbsent(patientId, k -> new ArrayList<>()).add(r);
        System.out.println("Them ho so thanh cong");
    }

    public void viewRecords(String patientId) {
        List<MedicalRecord> list = recordMap.get(patientId);
        if (list == null) System.out.println("Khong co ho so");
        else list.forEach(MedicalRecord::display);
    }

    public void deleteRecord(String patientId, String recordId) {
        List<MedicalRecord> list = recordMap.get(patientId);
        if (list == null) return;
        list.removeIf(r -> r.getRecordId().equals(recordId));
        System.out.println("Da xoa ho so");
    }
}

public class BTTH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientService service = new PatientService();

        while (true) {
            System.out.println("\n========= QUAN LY PHONG KHAM =========");
            System.out.println("1. Them benh nhan");
            System.out.println("2. Cap nhat benh nhan");
            System.out.println("3. Xoa benh nhan");
            System.out.println("4. Hien thi danh sach benh nhan");
            System.out.println("5. Them ho so kham");
            System.out.println("6. Xem ho so theo benh nhan");
            System.out.println("7. Xoa ho so kham");
            System.out.println("8. Tim benh nhan theo ten");
            System.out.println("9. Sap xep benh nhan");
            System.out.println("0. Thoat");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Tuoi: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("SDT: ");
                    String phone = sc.nextLine();
                    service.add(new Patient(id, name, age, phone));
                    break;

                case 2:
                    System.out.print("Nhap ID: ");
                    service.update(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Nhap ID: ");
                    service.delete(sc.nextLine());
                    break;

                case 4:
                    service.displayAll();
                    break;

                case 5:
                    System.out.print("Patient ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Record ID: ");
                    String rid = sc.nextLine();
                    System.out.print("Chan doan: ");
                    String d = sc.nextLine();
                    System.out.print("Ngay: ");
                    String date = sc.nextLine();
                    service.addRecord(pid, new MedicalRecord(rid, d, date));
                    break;

                case 6:
                    System.out.print("Patient ID: ");
                    service.viewRecords(sc.nextLine());
                    break;

                case 7:
                    System.out.print("Patient ID: ");
                    String p1 = sc.nextLine();
                    System.out.print("Record ID: ");
                    String r1 = sc.nextLine();
                    service.deleteRecord(p1, r1);
                    break;

                case 8:
                    System.out.print("Nhap ten can tim: ");
                    service.searchByName(sc.nextLine());
                    break;

                case 9:
                    System.out.println("1. Theo ten");
                    System.out.println("2. Theo tuoi");
                    System.out.println("3. Theo id");
                    int sort = Integer.parseInt(sc.nextLine());
                    if (sort == 1) service.sortByName();
                    else if (sort == 2) service.sortByAge();
                    else service.sortById();
                    break;

                case 0:
                    return;
            }
        }
    }
}