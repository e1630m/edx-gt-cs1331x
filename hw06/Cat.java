public class Cat extends Pet {
    private int miceCaught;

    private static final int DEFAULT_MICE_CAUGHT = 0;

    public Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, DEFAULT_MICE_CAUGHT);
    }

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        this.miceCaught = Math.max(miceCaught, 0);
    }
    
    public int getMiceCaught() {
        return this.miceCaught;
    }

    public int treat() {
        double health = this.getHealth();
        int painLevel = this.getPainLevel();
        this.heal();
        if (this.miceCaught < 4)
            return (int) (painLevel * 2 / health);
        return this.miceCaught <= 7 ? (int) (painLevel / health) : (int) (painLevel / (health * 2));
    }

    public void speak() {
        super.speak();
        StringBuilder bark = new StringBuilder();
        bark.append("meow ".repeat(Math.max(0, this.getPainLevel())));
        String b = this.getPainLevel() > 5 ? bark.toString().toUpperCase() : bark.toString();
        System.out.println(b);
    }

    public boolean equals(Object o) {
    if (o instanceof Cat)
        return super.equals(o) && this.miceCaught == ((Cat) o).getMiceCaught();
    return false;
    }
}
