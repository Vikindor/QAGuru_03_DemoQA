package qaguru.vikindor;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    public void fillFormTest(){
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

        $("#uploadPicture").uploadFile(new File
                ("src/test/data/file_example_JPG_100kB.jpg"));

        $("#currentAddress").val("Test Street, 123");

        $("#state").click();
        $(byText("Uttar Pradesh")).click();

        $("#city").click();
        $(byText("Merrut")).click();

        $("#submit").click();

        //Modal window
        $(".modal-content").shouldBe();
        $("#closeLargeModal").click();
    }

}
