package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DemoQA Registration Form Tests (POM)")
public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Should submit registration form successfully (happy path)")
    void testSubmitRegistration() {
        RegistrationPage page = new RegistrationPage(driver);

        String first = "John";
        String last = "Doe";
        String email = "john.doe@example.com";
        String mobile = "9999999999";

        page.navigate();
        page
                .setFirstName(first)
                .setLastName(last)
                .setEmail(email)
                .selectGender("Male")
                .setMobile(mobile)
                .addSubject("Maths")
                .selectHobby("Sports")
                .setCurrentAddress("221B Baker Street")
                .selectState("NCR")
                .selectCity("Delhi");

        page.submit();

        assertTrue(page.isSubmissionModalVisible());
        assertEquals(first + " " + last, page.getSubmittedValue("Student Name"));
        assertEquals(email, page.getSubmittedValue("Student Email"));
        assertEquals("Male", page.getSubmittedValue("Gender"));
        assertEquals(mobile, page.getSubmittedValue("Mobile"));
    }

    @ParameterizedTest(name = "CSV: {0} {1}, {3}, {4}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @DisplayName("Submit registration form with CSV data")
    void testRegistrationFromCSV(
            String first,
            String last,
            String email,
            String gender,
            String mobile,
            String subjects,
            String hobbies,
            String address,
            String state,
            String city) {
        RegistrationPage page = new RegistrationPage(driver);
        page.navigate();

        page
                .setFirstName(first)
                .setLastName(last)
                .setEmail(email)
                .selectGender(gender)
                .setMobile(mobile)
                .setCurrentAddress(address)
                .selectState(state)
                .selectCity(city);

        if (subjects != null && !subjects.isBlank()) {
            for (String sub : subjects.split("\\|")) {
                page.addSubject(sub.trim());
            }
        }

        if (hobbies != null && !hobbies.isBlank()) {
            for (String hobby : hobbies.split("\\|")) {
                page.selectHobby(hobby.trim());
            }
        }

        page.submit();

        assertTrue(page.isSubmissionModalVisible());
        assertEquals(first + " " + last, page.getSubmittedValue("Student Name"));
        assertEquals(email, page.getSubmittedValue("Student Email"));
        assertEquals(gender, page.getSubmittedValue("Gender"));
        assertEquals(mobile, page.getSubmittedValue("Mobile"));

        // Close modal to avoid overlay blocking next dataset
        page.closeModal();
    }
}
