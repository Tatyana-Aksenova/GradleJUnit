package tests.junit5.api.models.students;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("courses")
	private List<String> courses;

	@JsonProperty("programme")
	private String programme;

	@JsonProperty("email")
	private String email;
}