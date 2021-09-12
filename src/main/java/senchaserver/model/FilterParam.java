package senchaserver.model;

import lombok.Data;

/**
 * @author Markitanov Vadim
 * @since 11.09.2021
 */
@Data
public class FilterParam {
    private String property; // Название поля
    private FilterOperator operator; // Оператор, к примеру like
    private Object value; // Значение
}
