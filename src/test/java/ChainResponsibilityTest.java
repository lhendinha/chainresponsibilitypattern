import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChainResponsibilityTest {
    TeamLeader teamLeader = null;
    ProjectLeader projectLeader = null;
    HR hr = null;
    Manager manager = null;

    @BeforeEach
    void setUp() {
        teamLeader = new TeamLeader();
        projectLeader = new ProjectLeader();
        hr = new HR();
        manager = new Manager();

        teamLeader.setSuperVisor(projectLeader);
        projectLeader.setSuperVisor(hr);
        hr.setSuperVisor(manager);
    }

    @Test
    void testLeaveTier4Day5ReasonRegular() {
        System.out.println("Running: testLeaveTier4Day5ReasonRegular");

        Leave leave1 = new Leave(5,4, ReasonType.REGULAR);
        assertEquals(teamLeader.applyLeave(leave1), "Your leave days has been APPROVED by TeamLeader");
    }

    @Test
    void testLeaveTier5Day5ReasonRegular() {
        System.out.println("Running: testLeaveTier5Day5ReasonRegular");

        Leave leave2 = new Leave(5,5, ReasonType.REGULAR);
        assertEquals(teamLeader.applyLeave(leave2), "You employee Tier level is too low for request 5 days");
    }

    @Test
    void testLeaveTier3Day12ReasonRegular() {
        System.out.println("Running: testLeaveTier3Day12ReasonRegular");

        Leave leave3 = new Leave(12,3, ReasonType.REGULAR);
        assertEquals(teamLeader.applyLeave(leave3), "Your leave days has been APPROVED by Project Leader");
    }

    @Test
    void testLeaveTier2Day18ReasonCritical() {
        System.out.println("Running: testLeaveTier2Day18ReasonCritical");

        Leave leave4 = new Leave(18,2, ReasonType.CRITICAL);
        assertEquals(teamLeader.applyLeave(leave4), "Your leave days has been APPROVED by HR");
    }

    @Test
    void testLeaveTier2Day18ReasonRegular() {
        System.out.println("Running: testLeaveTier2Day18ReasonRegular");

        Leave leave5 = new Leave(18,2, ReasonType.REGULAR);
        assertEquals(teamLeader.applyLeave(leave5), "Your leave request has been DENIED by HR");
    }

    @Test
    void testLeaveTier2Day30ReasonSpecial() {
        System.out.println("Running: testLeaveTier2Day30ReasonSpecial");

        Leave leave6 = new Leave(30,2, ReasonType.SPECIAL);
        assertEquals(teamLeader.applyLeave(leave6), "Your leave days has been APPROVED by Manager");
    }
}
