package senchaserver.model;

import lombok.Data;

/**
 * @author Markitanov Vadim
 * @since 11.09.2021
 */
@Data
public class FilterParam {
    public String property; // Название поля
    public String operator; // Оператор, к примеру like
    public Object value; // Значение

    public FilterParam(String property, String operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }
}
