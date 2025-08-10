import com.codeborne.selenide.Configuration;
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
        $(".modal-content").shouldBe(visible);
        $(".modal-content").$(".table").shouldHave(text("Test Testov")); //Student Name
        $(".modal-content").$(".table").shouldHave(text("testov@test.test")); //Student Email
        $(".modal-content").$(".table").shouldHave(text("Other")); //Gender
        $(".modal-content").$(".table").shouldHave(text("1234567890")); //Mobile
        $(".modal-content").$(".table").shouldHave(text("16 April,1975")); //Date of Birth
        $(".modal-content").$(".table").shouldHave(text("English, Computer Science")); //Subjects
        $(".modal-content").$(".table").shouldHave(text("Reading, Music")); //Hobbies
        $(".modal-content").$(".table").shouldHave(text("file_example_JPG_100kB.jpg")); //Picture
        $(".modal-content").$(".table").shouldHave(text("Test Street, 123")); //Address
        $(".modal-content").$(".table").shouldHave(text("Uttar Pradesh Merrut")); //State and City
    }

}