//@author Bozzay, Ádám
package hu.kleatech.bead1.controller;

import hu.kleatech.bead1.controller.ObjectTransferHandler.PencilTransfer;
import hu.kleatech.bead1.model.Color;
import hu.kleatech.bead1.model.Pencil;
import hu.kleatech.bead1.model.User;
import hu.kleatech.bead1.service.PencilCaseService;
import hu.kleatech.bead1.service.PencilService;
import hu.kleatech.bead1.service.UserService;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static hu.kleatech.bead1.common.Logger.LOGGER;

@Controller
public class HomeController {

	@Autowired private UserService userService;
	@Autowired private PencilService pencilService;
	@Autowired private PencilCaseService pencilCaseService;
	@Autowired private ObjectTransferHandler toc;

	private HttpHeaders emptyHeaders = new HttpHeaders();

	@RequestMapping("/")
	public String index(Model model, Locale locale) {
		User actualUser = userService.getUser("bela123");
		model.addAttribute("allColor", Color.values());
		model.addAttribute("actualUser", actualUser);
		model.addAttribute("allPencilCase", pencilCaseService.getPencilCases(actualUser));
		return "index";
	}

	@PostMapping("/pencilCase")
	public ResponseEntity<String> addPencilCase() {
		try {
			pencilCaseService.addPencilCase(userService.getUser("bela123"));
			return responseEntity("New pencil case added", HttpStatus.OK);
		}
		catch (Exception e) {
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/pencil")
	public ResponseEntity<String> addPencil(@RequestBody PencilTransfer pencilTransfer) {
		try {
			Pencil pencil = toc.conv(pencilTransfer);
			LOGGER.log("New pencil request recieved: " + pencil);
			pencilService.addPencil(pencil);
			return responseEntity("Pencil add successful", HttpStatus.OK);
		}
		catch(Exception e) {
			LOGGER.log("Error at addPencil()", e);
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/pencil")
	public ResponseEntity<String> deletePencil(@RequestParam("id") Long id) {
		try {
			pencilService.removePencil(id);
			return responseEntity("Pencil deleted", HttpStatus.OK);
		}
		catch (Exception e) {
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/brake")
	public ResponseEntity<String> brakePencil(@RequestParam("id") Long id) {
		try {
			pencilService.breakPencil(id);
			return responseEntity("Pencil broken", HttpStatus.OK);
		}
		catch (Exception e) {
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/use")
	public ResponseEntity<String> usePencil(@RequestParam("id") Long id, @RequestParam("percentage") Integer percentage) {
		try {
			pencilService.usePencil(id, percentage);
			return responseEntity("Pencil used", HttpStatus.OK);
		}
		catch (Exception e) {
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sharpen")
	public ResponseEntity<String> sharpenPencil(@RequestParam("id") Long id, @RequestParam("percentage") Integer percentage) {
		try {
			pencilService.sharpenPencil(id, percentage);
			return responseEntity("Pencil sharpened", HttpStatus.OK);
		}
		catch (Exception e) {
			return responseEntity("Unexpected error", HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<String> responseEntity(String s, HttpStatus status) {
		return new ResponseEntity<>("[\"" + s + "\"]", status);
	}
}
