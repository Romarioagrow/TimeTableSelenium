import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Log
public class Main {
    public static void main(String[] args) {
        startTimetableParser();
    }

    private static void startTimetableParser() {
        WebDriver driver = new FirefoxDriver();
        String urfu = "https://urfu.ru/";
        driver.get(urfu);

        WebElement elementToStudents = driver.findElement(By.linkText("Студентам"));
        elementToStudents.click();

        WebElement elementRaspisanie = driver.findElement(By.linkText("Расписание занятий"));
        elementRaspisanie.click();

        WebElement groupNumber = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("group_number")));
        groupNumber.sendKeys("РИЗ-380028у");

        WebElement calendar = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label.button-link")));
        calendar.click();

        WebElement calendarDate = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText("30")));
        calendarDate.click();

        /*Собрать данные со страницы*/
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shedule-weekday-item")));

        List<WebElement> scheduleList = driver.findElements(By.className("shedule-weekday-item"));
        showElementData(scheduleList);
    }

    private static void showElementData(List<WebElement> scheduleList) {
        System.out.println("size: " + scheduleList.size());
        for(WebElement element1 : scheduleList){
            System.out.println(element1.getText());
        }
    }
}
