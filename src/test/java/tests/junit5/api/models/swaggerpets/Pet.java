package tests.junit5.api.models.swaggerpets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("status")
	private String status;
}