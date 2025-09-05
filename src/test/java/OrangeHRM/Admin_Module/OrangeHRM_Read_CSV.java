package OrangeHRM.Admin_Module;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OrangeHRM_Read_CSV extends OrangeHRM_Test_Base {

	@DataProvider(name = "Login_Test_Data")
	public Object[][] getCSVTestData_login() throws IOException {
		List<Object[]> data = new ArrayList<>();

		InputStream isThread = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("OrangeHRM_Data/OrangeHRM_Login.csv");

		BufferedReader buffR = new BufferedReader(new InputStreamReader(isThread));
		String line;
		boolean firstLine = true;

		while ((line = buffR.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] values = line.split(",", -1);

			String username = values[0];
			String password = values[1];
			boolean expected = Boolean.parseBoolean(values[2].trim());

			data.add(new Object[] { username, password, expected });
		}

		return data.toArray(new Object[0][]);
	}

	@DataProvider(name = "Reset_Password_Test_Data")
	public Object[][] getCSVTestData_resetPassword() throws IOException {
		List<Object[]> data = new ArrayList<>();

		InputStream isThread = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("OrangeHRM_Data/OrangeHRM_Reser_Password.csv");

		BufferedReader buffR = new BufferedReader(new InputStreamReader(isThread));
		String line;
		boolean firstLine = true;

		while ((line = buffR.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] values = line.split(",", -1);

			String username = values[0];
			boolean expected = Boolean.parseBoolean(values[1].trim());

			data.add(new Object[] { username, expected });
		}

		return data.toArray(new Object[0][]);
	}

	@DataProvider(name = "Search_Test_Data")
	public Object[][] getCSVTestData_search() throws IOException {
		List<Object[]> data = new ArrayList<>();

		InputStream isThread = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("OrangeHRM_Data/OrangeHRM_Search.csv");

		BufferedReader buffR = new BufferedReader(new InputStreamReader(isThread));
		String line;
		boolean firstLine = true;

		while ((line = buffR.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] values = line.split(",", -1);

			String username = values[0];
			String userRole = values[1];
			String employeeName = values[2];
			String status = values[3];
			boolean expected = Boolean.parseBoolean(values[4].trim());

			data.add(new Object[] { username, userRole, employeeName, status, expected });
		}

		return data.toArray(new Object[0][]);
	}

	@DataProvider(name = "Add_Test_Data")
	public Object[][] getCSVTestData_add() throws IOException {
		List<Object[]> data = new ArrayList<>();

		InputStream isThread = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("OrangeHRM_Data/OrangeHRM_Add.csv");

		BufferedReader buffR = new BufferedReader(new InputStreamReader(isThread));
		String line;
		boolean firstLine = true;

		while ((line = buffR.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] values = line.split(",", -1);

			String userRole = values[0];
			String employeeName = values[1];
			String status = values[2];
			String username = values[3];
			String password = values[4];
			String confirmPassword = values[5];
			boolean expected = Boolean.parseBoolean(values[6].trim());

			data.add(new Object[] { userRole, employeeName, status, username, password, confirmPassword, expected });
		}

		return data.toArray(new Object[0][]);
	}

	@DataProvider(name = "Edit_Test_Data")
	public Object[][] getCSVTestData_edit() throws IOException {
		List<Object[]> data = new ArrayList<>();

		InputStream isThread = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("OrangeHRM_Data/OrangeHRM_Edit.csv");

		BufferedReader buffR = new BufferedReader(new InputStreamReader(isThread));
		String line;
		boolean firstLine = true;

		while ((line = buffR.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] values = line.split(",", -1);

			String userRole = values[0];
			String employeeName = values[1];
			String status = values[2];
			String username = values[3];
			String password = values[4];
			String confirmPassword = values[5];
			boolean expected = Boolean.parseBoolean(values[6].trim());

			data.add(new Object[] { userRole, employeeName, status, username, password, confirmPassword, expected });
		}

		return data.toArray(new Object[0][]);
	}
}