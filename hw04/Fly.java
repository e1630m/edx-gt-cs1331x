public class Fly {
    private double speed, mass;

    public Fly() {
        this(5);
    }

    public Fly(double mass) {
        this(5, 10);
    }

    public Fly(double mass, double speed) {
        this.mass = mass;
        this.speed = speed;
    }

    public double getMass() {
        return this.mass;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String toString() {
        if (this.mass == 0)
            return String.format("I’m dead, but I used to be a fly with a speed of %.2f.", this.speed);
        return String.format("I’m a speedy fly with %.2f speed and %.2f mass", this.speed, this.mass);
    }

    public void grow(int addMass) {
        double gFactorUT = 1.0, gFactorOT = -0.5, speedChange;
        int maxIncrease = (int) Math.ceil(20.0 - this.mass);
        speedChange = 20 - this.mass > 0 ? Math.min(maxIncrease, addMass) * gFactorUT : 0;
        speedChange += this.mass + addMass - 20 > 0 ? Math.floor(this.mass + addMass - 20) * gFactorOT : 0;
        this.mass += addMass;
        this.speed += speedChange;
    }

    public boolean isDead() {
        return this.mass <= 0;
    }
}
