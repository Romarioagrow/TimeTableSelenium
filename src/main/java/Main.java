import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        /*List<WebElement> classes = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("dd")));*/

        /*List<WebElement> time = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td.shedule-weekday-time:nth-child(2)")));

        List<WebElement> cabinets = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.shedule-weekday-row:nth-child(2) > td:nth-child(3) > dl:nth-child(1)")));
*/
/*

        List<WebElement> time = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td.shedule-weekday-time:nth-child(2)")));

        List<WebElement> cabinets = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.shedule-weekday-row:nth-child(2) > td:nth-child(3) > dl:nth-child(1)")));
*/

        //List<WebElement> time = driver.findElements(By.cssSelector("td.shedule-weekday-time:nth-child(2)"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> time = driver.findElements(By.className("shedule-weekday-item"));

        List<WebElement> cabinets = driver.findElements(By.cssSelector("tr.shedule-weekday-row:nth-child(2) > td:nth-child(3) > dl:nth-child(1)"));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        System.out.println("size: " + time.size());
        int i=0;
        for(WebElement element1 :time){
            System.out.println("Section "+i+":"+element1.getText());
            i++;
        }

        /*time.forEach(webElement -> {
            System.out.println("webElement time: " + webElement.toString());
            System.out.println(webElement.getText());
            //String ee = element.getAttribute("class");
            //System.out.println("ee: "  + ee);
            //System.out.println("time: " + webElement.getText());
        });*/
        cabinets.forEach(webElement -> {
            System.out.println("webElement cabinets: " + webElement.toString());
            //System.out.println("cabinets: " + webElement.getText());
        });

        /*List<WebElement> cabinets = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cabinet")));
        List<WebElement> teachers = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("teacher")));*/

        //tr.shedule-weekday-row:nth-child(2) > td:nth-child(3) > dl:nth-child(1)
        /*
        classes.forEach(webElement -> {
            System.out.println(webElement.getText());
        });

        time.forEach(webElement -> {
            System.out.println(webElement.getText());
        });

        cabinets.forEach(webElement -> {
            System.out.println(webElement.getText());
        });
        teachers.forEach(webElement -> {
            System.out.println(webElement.getText());
        });
        */

        //showElementData(classes, time, cabinets, teachers);

        /*showElementData(classes);
        showElementData(time);
        showElementData(cabinets);
        showElementData(teachers);*/
    }

    private static void showElementData(List<WebElement> classes, List<WebElement> times, List<WebElement> cabinets, List<WebElement> teachers) {
        //System.out.println("WebElement.toString: " + webElement.toString());
        //System.out.println("WebElement.getText: " + webElement.getText());
        //elements.forEach(System.out::println);

        int totalClasses = classes.size();

        for (int count = 0; count < totalClasses; count++) {
            if (classes.get(count).getText() == null) continue;

            String class1 = classes.get(count).getText();
            String time = times.get(count).getText();
            String cabinet = cabinets.get(count).getText();
            String teacher = teachers.get(count).getText();


            String result = String.format("Предмет: %s, Время: %s, Ауд: %s, Преподователь: %s", class1, time, cabinet, teacher);

            System.out.println(result);
        }


        /*elements.forEach(webElement -> {
            System.out.println(webElement.getText());
        });*/
    }

    private static void showElementData(List<WebElement> elements) {
        //System.out.println("WebElement.toString: " + webElement.toString());
        //System.out.println("WebElement.getText: " + webElement.getText());
        //elements.forEach(System.out::println);

        elements.forEach(webElement -> {
            System.out.println(webElement.getText());
        });
    }
}
