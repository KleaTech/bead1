//@author Bozzay, Ádám
package hu.kleatech.bead1.controller;

import hu.kleatech.bead1.dao.PencilCaseRepository;
import hu.kleatech.bead1.model.Color;
import hu.kleatech.bead1.model.Pencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ObjectTransferHandler {

	@Autowired private PencilCaseRepository pencilCaseRepository;

	Pencil conv(PencilTransfer dto) {
		return new Pencil(dto.color, dto.brand, dto.length, dto.sharpness
			,pencilCaseRepository.findOne(dto.pencilCaseId));
	}

	static class PencilTransfer {
	    private Color color;
	    private String brand;
	    private int length;
	    private int sharpness;
	    private Long pencilCaseId;

	    PencilTransfer() {}
	    void setColor(Color color) {this.color = color;}
	    void setBrand(String brand) {this.brand = brand;}
	    void setLength(int length) {this.length = length;}
	    void setSharpness(int sharpness) {this.sharpness = sharpness;}
	    void setPencilCaseId(Long pencilCaseId) {this.pencilCaseId = pencilCaseId;}
    }
}
