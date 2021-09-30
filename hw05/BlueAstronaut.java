import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
    private int numTasks;
    private int taskSpeed;
    private boolean taskDoneFirstTime = true;

    public static final int DEFAULT_NUM_TASKS = 6;
    public static final int DEFAULT_TASK_SPEED = 10;

    public BlueAstronaut(String name) {
        this(name, Player.DEFAULT_SUS_LEVEL, DEFAULT_NUM_TASKS, DEFAULT_TASK_SPEED);
    }

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
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

    public void completeTask() {
        if (!this.isFrozen())
            if (this.taskSpeed > 20)
                this.numTasks -= 2;
            else
                this.numTasks--;
            this.numTasks = Math.max(this.numTasks, 0);
            if (this.taskDoneFirstTime == true && this.numTasks == 0) {
                this.taskDoneFirstTime = false;
                System.out.println("I have completed all my tasks");
                this.setSusLevel((int) Math.floor(this.getSusLevel() * 0.5));
            }
    }

    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut b = (BlueAstronaut) o;
            return this.getName().equals(b.getName()) && this.isFrozen() == b.isFrozen() && this.getSusLevel() == b.getSusLevel()
                    && this.numTasks == b.numTasks && this.taskSpeed == b.taskSpeed;
        }
        return false;
    }

    public String toString() {
        if (this.getSusLevel() > 15)
            return (super.toString() + " I have " + this.numTasks + " left over.").toUpperCase();
        return super.toString() + " I have " + this.numTasks + " left over.";
    }
}
