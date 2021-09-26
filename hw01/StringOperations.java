public class StringOperations {
    public static void main(String[] args) {
        String name = "John Doe";
        System.out.println(name);
        System.out.println(name.toLowerCase());
        System.out.println(name.replace('n', 'A'));
        String url = "www.gatech.edu";
        System.out.println(url);
        String[] split = url.split("\\.");
        System.out.println(split);
        System.out.println(java.util.Arrays.toString(split));
        System.out.println(url.split("\\.")[1].concat("1331"));
    }
}
