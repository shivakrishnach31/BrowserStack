package scripts;

import java.sql.Timestamp;
import java.util.Date;

import org.testng.annotations.Test;

import testUtils.Helper;

public class FinalScript extends Helper{
	String downloaded;
	@Test
	public void stockCountAutomation() throws Exception{
		query = "select * from selenium.product;";     
		rs= stmt.executeQuery(query); 
		metaData = rs.getMetaData();
		count = metaData.getColumnCount(); //number of column
		columnName = new String[count];
		for (int i = 1; i <= count; i++)
		{
			columnName[i-1] = metaData.getColumnLabel(i); 
			System.out.println(columnName[i-1]);
		}
		while (rs.next()){
			String product_id = rs.getString("product_id");                                        
			System.out.println(product_id);
			String product_url = rs.getString("url");                                        
			System.out.println(product_url);
			if (product_url.contains("alza")) {
				Alza.automate(product_url);
			} else 
				if (product_url.contains("czc.cz")) {
				CZC.automate(product_url);
			} else if (product_url.contains("tsbohemia")) {
				Tsbohemia.automate(product_url);
			}

			int quantity_in_stock = itemsInStock;
//			query = "select now();";
//			rs1= stmt.executeQuery(query);
//
//			while (rs1.next()) {
//				downloaded =	rs1.getString("now()");
//			}
			Date date = new Date();
			Timestamp downloaded = new Timestamp(date.getTime());
			String sql = "INSERT INTO selenium.result (product_id,downloaded,quantity_in_stock)"+"values(?,?,?)";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString (1, product_id);
			preparedStmt.setTimestamp(2, downloaded);
			preparedStmt.setInt(3, quantity_in_stock);
			//stmt.executeUpdate(sql);
			preparedStmt.execute();

		}  
	}

}
