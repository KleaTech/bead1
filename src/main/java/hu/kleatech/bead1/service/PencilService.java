//@author Bozzay, Ádám
package hu.kleatech.bead1.service;

import hu.kleatech.bead1.dao.PencilRepository;
import hu.kleatech.bead1.model.Color;
import hu.kleatech.bead1.model.Pencil;
import hu.kleatech.bead1.model.PencilCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PencilService {

	@Autowired
	private PencilRepository pencilRepository;

	public Pencil addPencil(Color color, String brand, int length, int sharpness, PencilCase pc) {
		return pencilRepository.save(new Pencil(color, brand, length, sharpness, pc));
	}
	public void removePencil(Pencil pencil) {
		pencilRepository.delete(pencil);
	}
	public void sharpenPencil(Pencil pencil, int percentage) {
		if(pencil.getSharpness() + percentage > 100) {
			pencil.setSharpness(100);
			pencil.setLength(pencil.getLength() - (percentage-100));
		}
		else {
			pencil.setSharpness(percentage);
		}
		pencilRepository.save(pencil);
	}
	public void usePencil(Pencil pencil, int percentage) {
		if(pencil.getSharpness() - percentage < 0) pencil.setSharpness(0);
		else pencil.setSharpness(pencil.getSharpness()-percentage);
		pencilRepository.save(pencil);
	}
	public void breakPencil(Pencil pencil) {
		pencil.setSharpness(0);
		pencilRepository.save(pencil);
	}
	public List<Pencil> getPencils(Color color, String brand) {
		return pencilRepository.findByColorAndBrand(color, brand);
	}
}
