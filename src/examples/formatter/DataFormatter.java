package examples.formatter;

import java.util.List;

import examples.formatter.model.FormatElement;

public class DataFormatter {

	public DataFormatter(List<FormatElement> model) {

	}

	public static Builder build() {
		return new Builder();
	}

	public String format(Object...inputs) {
		return "Not implemented yet";
	}

	public static class Builder {
		public Builder t(String text) {
			return null;
		}

		public Builder object(int index) {
			return null;
		}

		public Builder day(int index) {
			return null;
		}

		public Builder month(int index) {
			return null;
		}

		public Builder year(int index) {
			return null;
		}

		public DataFormatter end() {
			return null;
		}
		
	}

}
