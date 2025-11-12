package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By maleGenderRadio = By.xpath("//label[@for='gender-radio-1']");
    private By femaleGenderRadio = By.xpath("//label[@for='gender-radio-2']");
    private By mobileField = By.id("userNumber");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbySportsCheckbox = By.xpath("//label[@for='hobbies-checkbox-1']");
    private By hobbyReadingCheckbox = By.xpath("//label[@for='hobbies-checkbox-2']");
    private By currentAddressField = By.id("currentAddress");
    private By stateDropdown = By.id("state");
    private By cityDropdown = By.id("city");
    private By submitButton = By.id("submit");
    private By confirmationModal = By.className("modal-content");
    private By modalTitle = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");

        // Đợi trang load xong
        wait.until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );

        // Đợi form xuất hiện
        waitForVisibility(firstNameField);
    }
    // Actions

    public void fillFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void fillLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void fillEmail(String email) {
        type(emailField, email);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            clickWithJS(maleGenderRadio);
        } else if (gender.equalsIgnoreCase("Female")) {
            clickWithJS(femaleGenderRadio);
        }
    }


    public void fillMobile(String mobile) {
        type(mobileField, mobile);
    }

    public void fillDateOfBirth(String date) {
        click(dateOfBirthInput);
        driver.findElement(dateOfBirthInput).sendKeys(Keys.CONTROL + "a");
        driver.findElement(dateOfBirthInput).sendKeys(date);
        driver.findElement(dateOfBirthInput).sendKeys(Keys.ENTER);
    }

    public void fillSubjects(String subject) {
        driver.findElement(subjectsInput).sendKeys(subject);
        driver.findElement(subjectsInput).sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        if (hobby.equalsIgnoreCase("Sports")) {
            scrollToElement(hobbySportsCheckbox);
            clickWithJS(hobbySportsCheckbox);
        } else if (hobby.equalsIgnoreCase("Reading")) {
            scrollToElement(hobbyReadingCheckbox);
            clickWithJS(hobbyReadingCheckbox);
        }
    }

    public void fillCurrentAddress(String address) {
        type(currentAddressField, address);
    }

    public void selectState(String state) {
        scrollToElement(stateDropdown);
        click(stateDropdown);
        By stateOption = By.xpath("//div[text()='" + state + "']");
        click(stateOption);
    }

    public void selectCity(String city) {
        click(cityDropdown);
        By cityOption = By.xpath("//div[text()='" + city + "']");
        click(cityOption);
    }

    public void clickSubmit() {
        scrollToElement(submitButton);
        clickWithJS(submitButton);
    }

    private void scrollToElement(By submitButton) {
    }

    public boolean isModalDisplayed() {
        return isElementVisible(confirmationModal);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    // Complete registration flow
    public void fillRegistrationForm(String firstName, String lastName, String email,
                                     String gender, String mobile, String dateOfBirth,
                                     String subject, String hobby, String address,
                                     String state, String city) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillEmail(email);
        selectGender(gender);
        fillMobile(mobile);
        fillDateOfBirth(dateOfBirth);
        fillSubjects(subject);
        selectHobby(hobby);
        fillCurrentAddress(address);
        selectState(state);
        selectCity(city);
        clickSubmit();
    }
}