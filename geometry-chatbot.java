import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTaker {
    public static String output(String[] commands) {
        Map<String, Shape> shapes = new HashMap<>();
        List<String> outputList = new ArrayList<>();

        for (String command : commands) {
            String[] tokens = command.split(" ");
            String operation = tokens[0];

            if (operation.equals("add")) {
                String shapeType = tokens[1];
                String id = tokens[2];

                if (shapeType.equals("rectangle") && tokens.length == 5) {
                    int width = Integer.parseInt(tokens[3]);
                    int height = Integer.parseInt(tokens[4]);
                    shapes.put(id, new Rectangle(width, height));
                } else if (shapeType.equals("triangle") && tokens.length == 5) {
                    int base = Integer.parseInt(tokens[3]);
                    int height = Integer.parseInt(tokens[4]);
                    shapes.put(id, new Triangle(base, height));
                }
            } else if (operation.equals("area")) {
                String id = tokens[1];
                if (shapes.containsKey(id)) {
                    int area = shapes.get(id).calculateArea();
                    outputList.add(String.valueOf(area));
                } else {
                    outputList.add("error");
                }
            }
        }

        return String.join(",", outputList);
    }

    // Shape interface
    interface Shape {
        int calculateArea();
    }

    // Rectangle class implementing the Shape interface
    static class Rectangle implements Shape {
        int width;
        int height;

        Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public int calculateArea() {
            return width * height;
        }
    }

    // Triangle class implementing the Shape interface
    static class Triangle implements Shape {
        int base;
        int height;

        Triangle(int base, int height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public int calculateArea() {
            return (base * height) / 2;
        }
    }

    // Example usage
    public static void main(String[] args) {
        String[] commands = {
                "add triangle t1 4 5",
                "area t1",
                "add rectangle r1 3 3",
                "area r1"
        };

        String result = output(commands);
        System.out.println(result); // Output: "10,9"
    }
}
