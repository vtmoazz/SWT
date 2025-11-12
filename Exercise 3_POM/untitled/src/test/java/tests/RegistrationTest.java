package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests using Page Object Model")
public class RegistrationTest extends BaseTest {

    static RegistrationPage registrationPage;

    @BeforeAll
    static void initPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should register successfully with valid data")
    void testRegistrationSuccess() {
        registrationPage.navigate();


        registrationPage.fillFirstName("John");
        registrationPage.fillLastName("Doe");
        registrationPage.fillEmail("john.doe@example.com");
        registrationPage.selectGender("Male");
        registrationPage.fillMobile("1234567890");
        registrationPage.fillDateOfBirth("15 Jan 1990");
        registrationPage.fillSubjects("Maths");
        registrationPage.selectHobby("Sports");
        registrationPage.fillCurrentAddress("123 Main Street");
        registrationPage.selectState("NCR");
        registrationPage.selectCity("Delhi");
        registrationPage.clickSubmit();

        assertTrue(registrationPage.isModalDisplayed(), "Confirmation modal should be displayed");
        assertEquals("Thanks for submitting the form", registrationPage.getModalTitle());
    }

    @Test
    @Order(2)
    @DisplayName("Should fail when required fields are empty")
    void testRegistrationWithEmptyFields() {
        registrationPage.navigate();
        registrationPage.clickSubmit();

        assertFalse(registrationPage.isModalDisplayed(), "Modal should not appear with empty fields");
    }

    @ParameterizedTest(name = "Test {index}: {0} {1}")
    @Order(3)
    @CsvSource({
            "Alice, Smith, alice@test.com, Female, 9876543210, 20 Mar 1995, English, Reading, 456 Park Ave, Haryana, Karnal",
            "Bob, Johnson, bob@test.com, Male, 5551234567, 10 Jul 1988, Physics, Sports, 789 Oak Rd, Uttar Pradesh, Agra"
    })
    void testRegistrationWithMultipleData(String firstName, String lastName, String email,
                                          String gender, String mobile, String dob,
                                          String subject, String hobby, String address,
                                          String state, String city) {
        registrationPage.navigate();

        registrationPage.fillRegistrationForm(firstName, lastName, email, gender, mobile,
                dob, subject, hobby, address, state, city);

        assertTrue(registrationPage.isModalDisplayed(),
                "Registration should succeed for: " + firstName + " " + lastName);
    }

    @ParameterizedTest(name = "CSV File: {0} {1}")
    @Order(4)
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    void testRegistrationFromCSV(String firstName, String lastName, String email,
                                 String gender, String mobile, String dob,
                                 String subject, String hobby, String address,
                                 String state, String city) {
        registrationPage.navigate();

        registrationPage.fillRegistrationForm(firstName, lastName, email, gender, mobile,
                dob, subject, hobby, address, state, city);

        assertTrue(registrationPage.isModalDisplayed(),
                "Registration should succeed from CSV data");
    }
}