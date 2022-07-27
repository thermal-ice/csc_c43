package MyBnB.groups;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupsConfiguration {

  @Bean
  public GroupedOpenApi allEndpointsApi() {
    return GroupedOpenApi.builder()
        .group("all")
        .pathsToMatch("/**")
        .build();
  }

  @Bean
  public GroupedOpenApi publicApi() {
    String[] matchingPaths = {"/public/**"};
    return GroupedOpenApi.builder()
        .group("public-stuff")
        .pathsToMatch(matchingPaths)
        .build();
  }

 // https://stackoverflow.com/questions/66784440/group-apis-by-parameter-or-tag-or-any-different-key
  //This isn't working fuck :(
  private OpenApiCustomiser openApiCustomiserPublic() {
    return openApi -> openApi.getPaths().entrySet()
        .removeIf(path -> path.getValue().readOperations().stream()
            .anyMatch(operation -> operation.getTags().contains("blah")));
  }
}
