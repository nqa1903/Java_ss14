import java.util.*;

public class B1 {
    public static void main(String[] args) {

        List<String> input = Arrays.asList(
                "Nguyễn Văn A – Yên Bái",
                "Trần Thị B – Thái Bình",
                "Nguyễn Văn A – Yên Bái",
                "Lê Văn C – Hưng Yên"
        );

        // LinkedHashSet: không trùng + giữ thứ tự chèn
        Set<String> danhSachChoKham = new LinkedHashSet<>(input);

        System.out.println("Danh sách gọi khám:");
        for (String ten : danhSachChoKham) {
            System.out.println(ten);
        }
    }
}