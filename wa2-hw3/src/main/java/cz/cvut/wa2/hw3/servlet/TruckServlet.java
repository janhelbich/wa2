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
import cz.cvut.wa2.hw3.model.Truck;
import cz.cvut.wa2.hw3.service.TruckStoreService;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/trucks", "/trucks/*" })
public class TruckServlet extends HttpServlet {

	private static final long serialVersionUID = -4391941009795895497L;
	
	private static final Logger logger = LoggerFactory.getLogger(TruckServlet.class);

	private final TruckStoreService truckService = new TruckStoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		
		Matcher truckById = Pattern.compile(".*trucks\\/(\\d+)(\\/)?").matcher(req.getRequestURI());
		Matcher truckNew = Pattern.compile(".*trucks\\/new(\\/)?").matcher(req.getRequestURI());
		if (Pattern.matches(".*trucks(\\/)?", req.getRequestURI())) {
			listTrucks(req, resp);
		} else if (truckById.find()) {
			getTrucks(req, resp, truckById.group(1));
		} else if (truckNew.find()) {
			getTruckById(req, resp, null);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Truck truck = null;
		String brand = req.getParameter("brand");
		String colour = req.getParameter("colour");
		String licencePlate = req.getParameter("licencePlate");
		String maxKgLoad = req.getParameter("maxKgLoad");
		String id = req.getParameter("id");
		if (id == null || "".equals(id)) {
			logger.debug("creating new truck");
			truck = new Truck();
		} else {
			logger.debug("got car ID " + id);
			truck = truckService.find(Truck.class, Long.parseLong(id));
		}
		
		if (brand != null && !"".equals(brand)) {
			truck.setBrand(truckService.find(Brand.class, Long.parseLong(brand)));
		}
		truck.setColour(colour);
		truck.setLicencePlate(licencePlate);
		
		try {
			truck.setMaxKgLoad(Float.parseFloat(maxKgLoad));
		} catch (Exception e) {
			logger.warn("Truck max load provided in wrong format, got: " + maxKgLoad);
		}
		
		truckService.save(truck);
		
		getTruckById(req, resp, truck.getId());
	}
	
	private void getTrucks(HttpServletRequest req, HttpServletResponse resp, String textId) throws IOException, ServletException {
		Long id = null;
		try {
			id = Long.parseLong(textId);
		} catch (Exception e) {
		}
		
		if (id == null) {
			logger.warn("Truck ID provided in wrong format.");
			badRequest(textId, resp);
			return;
		}
		
		getTruckById(req, resp, id);
	}
	
	private void getTruckById(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException, ServletException {
		logger.info("finding truck with id " + id);
		
		if (id != null) {
			req.setAttribute("truck", truckService.findFull(id));
		} else {
			req.setAttribute("truck", new Truck());
		}
		
		// pro vytvoreni comboboxu znacek proste vylistuju vsechny a hotovo
		req.setAttribute("brands", truckService.findAllSimple(Brand.class));
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/trucks/truck.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void listTrucks(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setAttribute("trucks", truckService.listAllTrucksFull());
		resp.setStatus(200);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/trucks/trucks.jsp");
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
