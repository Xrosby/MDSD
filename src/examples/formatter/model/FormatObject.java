package examples.formatter.model;

import java.util.Calendar;

public class FormatObject extends FormatElement {

	private int index;
	private FormatKind kind;

	public FormatObject(FormatKind kind, int index) {
		this.kind = kind; this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public FormatKind getKind() {
		return kind;
	}

	@Override
	public String format(Object[] inputs) {
		switch(kind) {
		case OBJECT: return inputs[index].toString();
		case DAY:
		case MONTH:
		case YEAR:
			return Integer.toString(((Calendar)inputs[index]).get(selectSelector(kind)));
		default:
			throw new Error("Internal error, inconsistent model");
		}
	}

	private static int selectSelector(FormatKind kind) {
		switch(kind) {
		case DAY: return Calendar.DAY_OF_MONTH;
		case MONTH: return Calendar.MONTH;
		case YEAR: return Calendar.YEAR;
		default: throw new Error("Internal error, inconsistent model");
		}
	}

}
