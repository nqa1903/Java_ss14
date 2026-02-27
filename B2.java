import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B2 {
    public static void main(String[] args) {

        // 1. Tạo danh mục thuốc
        Map<String, String> danhMucThuoc = new HashMap<>();
        danhMucThuoc.put("T01", "Paracetamol");
        danhMucThuoc.put("T02", "Ibuprofen");
        danhMucThuoc.put("T03", "Amoxicillin");
        danhMucThuoc.put("T04", "Aspirin");
        danhMucThuoc.put("T05", "Vitamin C");

        // 2. Nhập mã thuốc từ dược sĩ
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã thuốc: ");
        String maThuoc = sc.nextLine().trim().toUpperCase();

        // 3. Tra cứu
        if (danhMucThuoc.containsKey(maThuoc)) {
            System.out.println("Tên thuốc: " + danhMucThuoc.get(maThuoc));
        } else {
            System.out.println("Thuốc không tồn tại.");
        }

        sc.close();
    }
}