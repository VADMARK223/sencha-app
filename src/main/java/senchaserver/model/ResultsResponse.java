package senchaserver.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
@Data
public class ResultsResponse<T> {
    private List<T> results = new ArrayList<>();
    private Long count;
}
