package web.web.controller.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchTO {

    private String word;
    private Integer count;
    private String url;
}
