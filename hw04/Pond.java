public class Pond {
    public static void main(String[] args) {
        Frog fr1 = new Frog("Peepo");
        Frog fr2 = new Frog("Pepe", 10, 15);
        Frog fr3 = new Frog("Peepaw", 4.6);
        Frog fr4 = new Frog("PepeKing", 2, 35);
        Fly fl1 = new Fly(1, 3);
        Fly fl2 = new Fly(6);
        Fly fl3 = new Fly(10, 16);
        Frog.setSpecies("1331 Frogs");
        System.out.println(fr1.toString());
        fr1.eat(fl2);
        System.out.println(fl2.toString());
        fr1.grow(8);
        fr1.eat(fl2);
        System.out.println(fl2.toString());
        System.out.println(fr1.toString());
        System.out.println(fr4.toString());
        fr3.grow(4);
        System.out.println(fr3.toString());
        System.out.println(fr2.toString());
    }
}
