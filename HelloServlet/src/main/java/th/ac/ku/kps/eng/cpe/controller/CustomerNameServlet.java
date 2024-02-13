package th.ac.ku.kps.eng.cpe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import th.ac.ku.kps.eng.cpe.dao.CustomerDAO;
import th.ac.ku.kps.eng.cpe.entity.Customer;

public class CustomerNameServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String[] value = req.getParameterValues("dee");
		ArrayList<Customer> listOfCustomer = new CustomerDAO().getAllCustomers();
		
		for (int i = 0; i < value.length; i++) {
			String paramValue = value[i];
			out.write(paramValue);
			//System.out.println(paramValue);
			for (int j = 0; j < listOfCustomer.size(); j++) {
				Customer c = listOfCustomer.get(j);
				if (c.getName().equals(paramValue)) {
					
					ByteArrayOutputStream op = new ByteArrayOutputStream();
					ObjectMapper mapper = new ObjectMapper();
					
					mapper.writeValue(op, c);
					byte[] data = op.toByteArray();
					//System.out.println(new String(data).toString());
					out.write(new String(data).toString());
				}
			}
		}
		out.close();
	}
}