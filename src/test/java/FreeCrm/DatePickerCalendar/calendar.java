package FreeCrm.DatePickerCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class calendar {

	public WebDriver driver;

	@Test
	public void CalendarControl() throws InterruptedException, ParseException {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Eclipse Selenium Practice\\DatePickerCalendar\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		/*
		 * driver.get("https://www.goibibo.com/");
		 * driver.manage().window().maximize();
		 * driver.findElement(By.xpath("//input[@placeholder='Departure']")).
		 * click();
		 */

		driver.get("https://jqueryui.com/datepicker/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='Datepicker']")).click();
		WebElement switchFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(switchFrame);
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();

		selectDate("13/12/2019");

	}

	public void selectDate(String date) throws ParseException {

		Date currentDate = new Date();
		System.out.println(currentDate);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date bookingDate = sdf.parse(date);

		String days = new SimpleDateFormat("d").format(bookingDate);
		String months = new SimpleDateFormat("MMMM").format(bookingDate);
		String year = new SimpleDateFormat("yyyy").format(bookingDate);

		System.out.println(days + "=== " + months + " ==== " + year);

		String bookingMonthandYear = months + " " + year;
		System.out.println("Booking Month and Year is == > " + months + " " + year);

		while (true) {
			/*
			 * String calendarCurrentMonthYear =
			 * driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
			 * .getText();
			 */
			String currentMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String currentYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

			String calendarCurrentMonthYear = currentMonth + " " + currentYear;

			if (bookingMonthandYear.equals(calendarCurrentMonthYear)) {
				driver.findElement(By.xpath("//a[text()='" + days + "']")).click();
				break;
			}

			else {
				if (bookingDate.compareTo(currentDate) > 0)
					/*
					 * driver.findElement(By.xpath(
					 * "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"
					 * )) .click();
					 */
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();

				else if (bookingDate.compareTo(currentDate) < 0)
					/*
					 * driver.findElement(By.xpath(
					 * "DayPicker-NavButton DayPicker-NavButton--prev"
					 * )).click();
					 */

					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			}

		}

	}

}
