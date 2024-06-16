package ant.rentathing.products;

import ant.rentathing.classes.products.Drill;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DrillTest {
    @Test
    void testDrillPriceCalculation() throws IOException {
        Drill drill = new Drill("High power drill", "Bosch", "ModelX");
        assertEquals(5.0, drill.calculatePricePerDay());
        assertEquals(1.0, drill.calculateInsurancePerDay());
    }

    @Test
    void testCalculateTotalPrice() throws IOException {
        Drill drill = new Drill("High power drill", "Bosch", "ModelX");

        assertEquals(30.0, drill.calculateTotalPrice(5, true), 0.01);

        assertEquals(6.0, drill.calculateTotalPrice(1, true), 0.01);
        assertEquals(180.0, drill.calculateTotalPrice(30, true), 0.01);
    }

    @Test
    void testDecisionConditions() throws IOException {
        Drill drill = new Drill("High power drill", "Bosch", "ModelX");

        boolean productAvailable = true;
        boolean userAuthorized = true;
        boolean rentalPeriodValid = true;

        boolean rentalAllowed = productAvailable && userAuthorized && rentalPeriodValid;
        assertTrue(rentalAllowed);

        rentalAllowed = !productAvailable && userAuthorized && rentalPeriodValid;
        assertFalse(rentalAllowed);

        rentalAllowed = productAvailable && !userAuthorized && rentalPeriodValid;
        assertFalse(rentalAllowed);

        rentalAllowed = productAvailable && userAuthorized && !rentalPeriodValid;
        assertFalse(rentalAllowed);
    }
}
