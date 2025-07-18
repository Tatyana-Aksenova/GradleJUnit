package tests.junit5.api.models.swaggerpets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category{
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;
}