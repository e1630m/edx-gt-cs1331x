import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    private String skill;

    public static final String DEFAULT_SKILL = "experienced";

    public RedAstronaut(String name) {
        this(name, DEFAULT_SUS_LEVEL, DEFAULT_SKILL);
    }

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    public void emergencyMeeting() {
        if (!this.isFrozen()) {
            Player[] pl = Player.getPlayers();
            Arrays.sort(pl);
            int i = pl.length - 1;
            for (; i > 0; i--) {
                if (pl[i].getSusLevel() != pl[i - 1].getSusLevel() && pl[i] != this) {
                    if (!pl[i].isFrozen()) {
                        pl[i].setFrozen(true);
                        break;
                    }
                } else if (pl[i].getSusLevel() == pl[i - 1].getSusLevel() && !pl[i].isFrozen() && !pl[i - 1].isFrozen())
                    return;
            }
            if (i == 0 && !pl[0].isFrozen() && pl[0] != this)
                pl[0].setFrozen(true);
            this.gameOver();
        }
    }

    public void freeze(Player p) {
        if (!this.isFrozen() && !(p instanceof Impostor)) {
            if (this.getSusLevel() < p.getSusLevel())
                p.setFrozen(true);
            else
                this.setSusLevel(this.getSusLevel() * 2);
            this.gameOver();
        } 
    }

    public void sabotage(Player p) {
        if (!this.isFrozen() && !(p instanceof Impostor))
            p.setSusLevel(this.getSusLevel() < 20 ? ((int) (p.getSusLevel() * 1.5)) : ((int) (p.getSusLevel() * 1.25)));
    }

    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut r = (RedAstronaut) o;
            return this.getName().equals(r.getName()) && this.isFrozen() == r.isFrozen() && this.getSusLevel() == r.getSusLevel()
                    && this.skill == r.skill;
        }
        return false;
    }

    public String toString() {
        if (this.getSusLevel() > 15)
            return (super.toString() + " I am an " + this.skill + " player!").toUpperCase();
        return super.toString() + " I am an " + this.skill + " player!";
    }
}
