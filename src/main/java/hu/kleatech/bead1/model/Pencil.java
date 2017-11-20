//@author Bozzay, Ádám
package hu.kleatech.bead1.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PencilTable")
public class Pencil implements Serializable {
	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private Color color;
	@Column(name = "brand")
	private String brand;
	@Column(name = "lengthColumn") //percentage
	private int length;
	@Column(name = "sharpness")
	private int sharpness; //percentaage
	@ManyToOne
	@JoinColumn(name = "pencilCase")
	private PencilCase pencilCase;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	protected Pencil() {}
	public Pencil(Color color, String brand, int length, int sharpness, PencilCase pc) {
		this.color = color;
		this.brand = brand;
		this.length = length;
		this.sharpness = sharpness;
		this.pencilCase = pc;
	}
	public Color getColor() {
		return color;
	}
	public String getBrand() {
		return brand;
	}
	public int getLength() {
		return length;
	}
	public int getSharpness() {
		return sharpness;
	}
	public PencilCase getPencilCase() {
		return pencilCase;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setSharpness(int sharpness) {
		this.sharpness = sharpness;
	}
	@Override
	public String toString() {
		return "Pencil{" + "color=" + color + ", brand=" + brand + ", length=" + length + ", sharpness=" + sharpness + '}';
	}
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + Objects.hashCode(this.id);
		return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pencil other = (Pencil) obj;
		return Objects.equals(this.id, other.id);
	}

}
