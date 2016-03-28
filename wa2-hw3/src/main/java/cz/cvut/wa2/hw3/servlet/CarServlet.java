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
import cz.cvut.wa2.hw3.model.Car;
import cz.cvut.wa2.hw3.service.CarStoreService;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/cars", "/cars/*" })
public class CarServlet extends HttpServlet {

	private static final long serialVersionUID = -4391941009795895497L;
	
	private static final Logger logger = LoggerFactory.getLogger(CarServlet.class);

	private final CarStoreService carService = new CarStoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		
		
		Matcher carById = Pattern.compile(".*cars\\/(\\d+)(\\/)?").matcher(req.getRequestURI());
		Matcher carNew = Pattern.compile(".*cars\\/new(\\/)?").matcher(req.getRequestURI());
		if (Pattern.matches(".*cars(\\/)?", req.getRequestURI())) {
			listCars(req, resp);
		} else if (carById.find()) {
			getCar(req, resp, carById.group(1));
		} else if (carNew.find()) {
			getCarById(req, resp, null);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Car car = (Car) req.getSession().getAttribute("car");
		String brand = req.getParameter("brand");
		String colour = req.getParameter("colour");
		String licencePlate = req.getParameter("licencePlate");
		if (car == null || car.getId() == null) {
			logger.debug("creating new car");
			car = new Car();
		}
		
		if (brand != null && !"".equals(brand)) {
			car.setBrand(carService.find(Brand.class, Long.parseLong(brand)));
		}
		car.setColour(colour);
		car.setLicencePlate(licencePlate);
		carService.save(car);
		
		getCarById(req, resp, car.getId());
	}
	
	private void getCar(HttpServletRequest req, HttpServletResponse resp, String textId) throws IOException, ServletException {
		Long id = null;
		try {
			id = Long.parseLong(textId);
		} catch (Exception e) {
		}
		
		if (id == null) {
			logger.warn("Car ID provided in wrong format.");
			badRequest(textId, resp);
			return;
		}
		
		getCarById(req, resp, id);
	}
	
	private void getCarById(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException, ServletException {
		logger.info("finding car with id " + id);
		
		if (id != null) {
			req.getSession().setAttribute("car", carService.findFull(id));
		} else {
			req.getSession().setAttribute("car", new Car());
		}
		
		// pro vytvoreni comboboxu znacek proste vylistuju vsechny a hotovo
		req.getSession().setAttribute("brands", carService.findAllSimple(Brand.class));
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/cars/car.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void listCars(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getSession().setAttribute("cars", carService.findAllCarsFull());
		resp.setStatus(200);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/cars/cars.jsp");
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
