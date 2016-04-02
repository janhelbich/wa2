package cz.cvut.wa2.hw3.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.wa2.hw3.model.Brand;
import cz.cvut.wa2.hw3.model.Customer;
import cz.cvut.wa2.hw3.service.CustomerStoreService;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/customers", "/customers/*" })
public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = -4391941009795895497L;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServlet.class);

	private final CustomerStoreService customerService = new CustomerStoreService ();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		
		
		Matcher customerById = Pattern.compile(".*customers\\/(\\d+)(\\/)?").matcher(req.getRequestURI());
		Matcher customerNew = Pattern.compile(".*customers\\/new(\\/)?").matcher(req.getRequestURI());
		if (Pattern.matches(".*customers(\\/)?", req.getRequestURI())) {
			listCustomers(req, resp);
		} else if (customerById.find()) {
			getCustomer(req, resp, customerById.group(1));
		} else if (customerNew.find()) {
			getCustomerById(req, resp, null);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Customer customer = null;
		String id = req.getParameter("id");

		if (id == null || "".equals(id)) {
			logger.debug("creating new customer");
			customer = new Customer();
		} else {
			logger.debug("got customer ID " + id);
			customer = customerService.find(Customer.class, Long.parseLong(id));
		}
		
		customer.setFirstName(req.getParameter("firstName"));
		customer.setSurname(req.getParameter("surname"));

		customerService.save(customer);
		getCustomerById(req, resp, customer.getId());
	}
	
	private void getCustomer(HttpServletRequest req, HttpServletResponse resp, String textId) throws IOException, ServletException {
		Long id = null;
		try {
			id = Long.parseLong(textId);
		} catch (Exception e) {
		}
		
		if (id == null) {
			logger.warn("Customer ID provided in wrong format: " + textId);
			badRequest(textId, resp);
			return;
		}
		
		getCustomerById(req, resp, id);
	}
	
	private void getCustomerById(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException, ServletException {
		logger.info("finding customer with id " + id);
		
		if (id != null) {
			req.setAttribute("customer", customerService.findFull(id));
		} else {
			req.setAttribute("customer", new Customer());
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/customers/customer.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void listCustomers(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setAttribute("customers", customerService.findAllSimple(Customer.class));
		resp.setStatus(200);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/customers/customers.jsp");
		requestDispatcher.forward(req, resp);
	}

	public void badRequest(String msg, HttpServletResponse resp) {
		try {
			resp.setStatus(400);
			resp.getWriter().write(msg);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				resp.sendError(500);
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
	}
}
