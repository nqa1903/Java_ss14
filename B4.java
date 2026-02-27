import java.util.*;

public class B4 {
    public static void main(String[] args) {
        List<String> danhSach = Arrays.asList(
                "Cúm A",
                "Sốt xuất huyết",
                "Cúm A",
                "Covid-19",
                "Cúm A",
                "Sốt xuất huyết"
        );

        Map<String, Integer> thongKe = new TreeMap<>();

        for (String benh : danhSach) {
            thongKe.put(benh, thongKe.getOrDefault(benh, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : thongKe.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ca");
        }
    }
}