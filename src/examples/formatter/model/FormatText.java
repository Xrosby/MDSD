package examples.formatter.model;

public class FormatText extends FormatElement {

	private String text;

	public FormatText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String format(Object[] inputs) {
		return text;
	}
}
