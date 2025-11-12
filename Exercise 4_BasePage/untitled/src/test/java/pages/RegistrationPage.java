package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // URL
    private static final String URL = "https://demoqa.com/automation-practice-form";

    // Locators
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");

    private By genderLabel(String genderText) {
        return By.xpath("//label[text()='" + genderText + "']");
    }

    private final By mobile = By.id("userNumber");
    private final By dobInput = By.id("dateOfBirthInput");
    private final By subjectsInput = By.id("subjectsInput");

    private By hobbyLabel(String hobbyText) {
        return By.xpath("//label[text()='" + hobbyText + "']");
    }

    private final By upload = By.id("uploadPicture");
    private final By currentAddress = By.id("currentAddress");
    private final By stateContainer = By.id("state");
    private final By stateInput = By.id("react-select-3-input");
    private final By cityContainer = By.id("city");
    private final By cityInput = By.id("react-select-4-input");
    private final By submitBtn = By.id("submit");

    // Modal
    private final By modalTitle = By.id("example-modal-sizes-title-lg");
    private final By modalClose = By.id("closeLargeModal");

    private By modalValue(String rowHeader) {
        return By.xpath("//div[@class='table-responsive']//td[text()='" + rowHeader + "']/following-sibling::td");
    }

    // Actions
    public void navigate() {
        driver.get(URL);
        waitForPageReady();
        dismissObstructions();
        waitPresent(firstName);
    }

    public RegistrationPage setFirstName(String value) {
        type(firstName, value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        type(lastName, value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        type(email, value);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        // Accepts: Male, Female, Other
        String label = normalizeGender(gender);
        click(genderLabel(label));
        return this;
    }

    public RegistrationPage setMobile(String value) {
        type(mobile, value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String value) {
        // Example format: 10 Nov 1990
        WebElement dob = waitVisible(dobInput);
        dob.sendKeys(Keys.CONTROL + "a");
        dob.sendKeys(value);
        dob.sendKeys(Keys.ENTER);
        return this;
    }

    public RegistrationPage addSubject(String subject) {
        type(subjectsInput, subject);
        pressEnter(subjectsInput);
        return this;
    }

    public RegistrationPage selectHobby(String hobby) {
        // Accepts: Sports, Reading, Music
        scrollIntoView(hobbyLabel(hobby));
        click(hobbyLabel(hobby));
        return this;
    }

    public RegistrationPage uploadPicture(String absolutePath) {
        upload(upload, absolutePath);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        type(currentAddress, value);
        return this;
    }

    public RegistrationPage selectState(String state) {
        scrollIntoView(stateContainer);
        click(stateContainer);
        type(stateInput, state);
        pressEnter(stateInput);
        return this;
    }

    public RegistrationPage selectCity(String city) {
        click(cityContainer);
        type(cityInput, city);
        pressEnter(cityInput);
        return this;
    }

    public void submit() {
        scrollIntoView(submitBtn);
        click(submitBtn);
    }

    public boolean isSubmissionModalVisible() {
        return waitVisible(modalTitle).isDisplayed();
    }

    public String getSubmittedValue(String rowHeader) {
        return waitVisible(modalValue(rowHeader)).getText();
    }

    public void closeModal() {
        click(modalClose);
    }

    private String normalizeGender(String gender) {
        if (gender == null)
            return "Male";
        String g = gender.trim().toLowerCase();
        if (g.startsWith("m"))
            return "Male";
        if (g.startsWith("f"))
            return "Female";
        if (g.startsWith("o"))
            return "Other";
        return "Male";
    }
}
