import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer, Integer> a = new BST<>();
        a.put(8,4235678);
        a.put(3,5);
        a.put(10,1023);
        a.put(14,176);
        a.put(13,163);
        a.put(6,513);

        for(Integer b : a) {
            System.out.println(b);
        }
}
