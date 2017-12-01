//@author Bozzay, Ádám
package hu.kleatech.bead1.model;

import java.util.Arrays;
import java.util.Collection;

public enum Color {
	RED, GREEN, BLUE, WHITE, GREY, PEACH, LIGHTBLUE, BLACK, GRAPHITE, BROWN, ORANGE, PINK, PURPLE;

	public static Collection<Color> valueList() {
		return Arrays.asList(values());
	}
}
