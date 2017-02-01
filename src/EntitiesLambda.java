import java.util.function.Consumer;

public class EntitiesLambda {
	
	public void build() {
		builder.system(s -> {
			s.entity("Person", e -> {
				e.field(String.class,"name");
				e.field(Integer.class, "age");
			});
			s.entity("Student", "Person", e -> {
				e.field(String.class, "id");
			});
		});
	}
	
	private IBuilder builder;
	private interface IBuilder {
		void system(Consumer<IEntityBuilder> f);
	}
	private interface IEntityBuilder {
		Object entity(String name, Consumer<IEntityMemberBuilder> f);
		Object entity(String name, String base, Consumer<IEntityMemberBuilder> f);
	}
	private interface IEntityMemberBuilder {
		void field(Class<? extends Object> type, String name);	
	}
}
