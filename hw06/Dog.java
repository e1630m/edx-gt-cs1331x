public class Dog extends Pet {
    private double droolRate;

    private static final double DEFAULT_DROLL_RATE = 5.0;

    public Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, DEFAULT_DROLL_RATE);
    }

    public Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        this.droolRate = droolRate;
    }
    
    public double getDroolRate() {
        return this.droolRate;
    }

    public int treat() {
        double health = this.getHealth();
        int painLevel = this.getPainLevel();
        this.heal();
        if (this.droolRate < 3.5)
            return (int) (painLevel * 2 / health);
        return this.droolRate <= 7.5 ? (int) (painLevel / health) : (int) (painLevel / (health * 2));
    }

    public void speak() {
        super.speak();
        StringBuilder bark = new StringBuilder();
        bark.append("bark ".repeat(Math.max(0, this.getPainLevel())));
        String b = this.getPainLevel() > 5 ? bark.toString().toUpperCase() : bark.toString();
        System.out.println(b);
    }

    public boolean equals(Object o) {
    if (o instanceof Dog)
        return super.equals(o) && this.droolRate == ((Dog) o).getDroolRate();
    return false;
    }
}
