public class Frog {
    private String name;
    private int age;
    private double tongueSpeed;
    private boolean isFroglet;
    public static String species = "Rare Pepe";
    public static double MIN_TONGUESPEED = 5;

    public Frog(String name) {
        this(name, 5, 5);
    }
    
    public Frog(String name, double ageInYears) {
        this(name, (int) (ageInYears * 12), 5);
    }

    public Frog(String name, int age, double tongueSpeed) {
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        this.isFroglet = false;
        this.updateIsFroglet();
    }

    public void grow(int addAge) {
        int gFactorU12 = 1, gFactorO30 = -1;
        int tSInc = this.age < 12 ? (int) Math.min(Math.ceil(12.0 - this.age), addAge) * gFactorU12 : 0;
        int tSDec = this.age + addAge > 30 ? (int) (this.age + addAge - 30) * gFactorO30 : 0;
        this.age += addAge;
        this.updateIsFroglet();
        this.tongueSpeed = Math.max(this.tongueSpeed + tSInc + tSDec, MIN_TONGUESPEED);
    }

    public void eat(Fly fly) {
        if (!fly.isDead()) {
            if (this.tongueSpeed > fly.getSpeed()) {
                if (fly.getMass() >= this.age * 0.5)
                    this.grow(1);
                fly.setMass(0);
            } else {
                fly.setMass(fly.getMass() + 1);
            }
        }
    }

    public String toString() {
        return String.format(
            "My name is %s and I’m a rare frog%s. I’m %d months old and my tongue has a speed of %.2f",
            this.name, this.isFroglet ? "let" : "", this.age, this.tongueSpeed);
    }

    public static void setSpecies(String str) {
        species = str;
    }

    public static String getSpecies() {
        return species;
    }

    private void updateIsFroglet() {
        if (1 < this.age && this.age < 7)
            this.isFroglet = true;
        else
            this.isFroglet = false;
    }

    public static void main(String[] args) {
        Frog f = new Frog("d", 1.59);
        System.out.println(f.toString());
        Frog f2 = new Frog("f");
        System.out.println(f2.toString());
    }
}
