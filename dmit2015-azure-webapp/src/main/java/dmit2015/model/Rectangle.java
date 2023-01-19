package dmit2015.model;

import lombok.Data;

//@Data A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields,
// and @Setter on all non-final fields, and @RequiredArgsConstructor!
@Data
public class Rectangle {

    private double length;
    private double width;

    public double area() {
        return width * length;
    }

    public double perimeter() {
        return 2 * (length + width);
    }

    public double diagonal() {
        return Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }
}
