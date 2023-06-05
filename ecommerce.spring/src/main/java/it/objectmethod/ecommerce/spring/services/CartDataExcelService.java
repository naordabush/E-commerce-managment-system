package it.objectmethod.ecommerce.spring.services;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.spring.models.CartItemObject;

@Service
public class CartDataExcelService {

	@Autowired
	private CartItemService cartItemService;

	public void generateExcelFile() {
		LocalDate previousDay = LocalDate.now().minusDays(1);

		// Fetch cart item data from the previous day using the cartItemService
		List<CartItemObject> cartItems = cartItemService.getCartItemsByDate(previousDay);

		Workbook workbook = new XSSFWorkbook(); // Create XLSX workbook

		Sheet sheet = workbook.createSheet("Data"); // Create a sheet named "Data"
		// first column
		Row headerRow = sheet.createRow(0); // Create a header row

		// Create a cell in the header row and Set the value of the cell
		headerRow.createCell(0).setCellValue("id");
		headerRow.createCell(1).setCellValue("cart_id");
		headerRow.createCell(2).setCellValue("item_id");
		headerRow.createCell(3).setCellValue("quantity");
		headerRow.createCell(4).setCellValue("order_time");

		// Iterate over your cart items and populate the data in the sheet
		int rowIndex = 1; // Start from the second row

		for (CartItemObject cartItem : cartItems) {
			Row dataRow = sheet.createRow(rowIndex++);

			// Set data cells with the cart item data
			dataRow.createCell(0).setCellValue(cartItem.getId().toString()); // id column
			dataRow.createCell(1).setCellValue(cartItem.getCart().getId().toString()); // Cart ID column
			dataRow.createCell(2).setCellValue(cartItem.getItem().getId().toString()); // Item ID column
			dataRow.createCell(3).setCellValue(cartItem.getQuantity().toString()); // Quantity column
			dataRow.createCell(4).setCellValue(cartItem.getOrderTime().toString()); // Date column
		}

		try (FileOutputStream fileOut = new FileOutputStream("C:/Users/naord/Desktop/exported_order_data.xlsx")) {
			workbook.write(fileOut);
			System.out.println("Excel file generated successfully.");
		} catch (Exception e) {
			System.out.println("Error generating Excel file: " + e.getMessage());
			e.printStackTrace(); // Log the exception stack trace
		}

	}
}