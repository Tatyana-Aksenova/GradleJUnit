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
public class TagsItem{
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;
}