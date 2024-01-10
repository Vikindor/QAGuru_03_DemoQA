package qaguru.vikindor;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    public void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").val("Test");
        $("#lastName").val("Testov");
        $("#userEmail").val("testov@test.test");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").val("1234567890");

        //Calendar
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1975");
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__month").$(byText("16")).click();

        //Subjects
        $("#subjectsInput").val("English").pressEnter();
        $("#subjectsInput").val("Computer").pressEnter();

        //Hobbies
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("file_example_JPG_100kB.jpg");

        $("#currentAddress").val("Test Street, 123");

        $("#state").click();
        $("#state").$(byText("Uttar Pradesh")).click();

        $("#city").click();
        $("#city").$(byText("Merrut")).click();

        $("#submit").click();

        //Modal window
        $(".modal-content").$(".table").shouldBe(visible).shouldHave(text("Test Testov"),
                text("testov@test.test"), text("Other"), text("1234567890"), text("16 April,1975"),
                text("English, Computer Science"), text("Reading, Music"), text("file_example_JPG_100kB.jpg"),
                text("Test Street, 123"), text("Uttar Pradesh Merrut"));
    }

    @AfterAll
    static void terminateWebDriver() {
        closeWebDriver();
    }

}