public abstract class Pet {
    private String name;
    private double health;
    private int painLevel;

    public Pet(String name, double health, int painLevel) {
        this.name = name;
        this.health = health;
        this.painLevel = painLevel;
    }

    public String getName() {
        return this.name;
    }

    public double getHealth() {
        return this.health;
    }

    public int getPainLevel() {
        return this.painLevel;
    }

    public abstract int treat();

    public void speak() {
        String s = "Hello! My name is " + this.name;
        s = this.painLevel > 5 ? s.toUpperCase() : s;
        System.out.println(s);
    }

    public boolean equals(Object o) {
        return o instanceof Pet && name.equals(((Pet) o).name);
    }

    protected void heal() {
        this.health = 1.0;
        this.painLevel = 1;
    }
}
