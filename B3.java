import java.util.LinkedHashSet;
import java.util.Set;

public class B3 {
    public static void main(String[] args) {
        Set<String> thuoc = new LinkedHashSet<>();
        thuoc.add("Aspirin");
        thuoc.add("Caffeine");
        thuoc.add("Paracetamol");

        Set<String> diUng = new LinkedHashSet<>();
        diUng.add("Penicillin");
        diUng.add("Aspirin");

        Set<String> canhBao = new LinkedHashSet<>(thuoc);
        canhBao.retainAll(diUng);

        Set<String> thanhPhanAnToan = new LinkedHashSet<>(thuoc);
        thanhPhanAnToan.removeAll(diUng);

        System.out.println("Cảnh báo dị ứng: " + canhBao);
        System.out.println("Thành phần an toàn: " + thanhPhanAnToan);
    }
}