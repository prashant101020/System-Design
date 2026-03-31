package StructuralDesignPattern;
// This pattern is used to reduce the number of objects created and to decrease memory footprint and increase performance. It is a way to use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory.
// The Flyweight pattern is a structural design pattern that allows programs to support vast quantities of objects by keeping their memory consumption low. It does this by sharing as much data as possible with similar objects; it is a way to use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface TreeType{
    void render(int x, int y);
}

class concreteTreeType implements TreeType{
    private String name;
    private String color;
    private String texture;

    public concreteTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void render(int x, int y) {
        System.out.println("Rendering tree at (" + x + ", " + y + ") with name: " + name + ", color: " + color + ", texture: " + texture);
    }
}

class TreeTypeFactory{
    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new concreteTreeType(name, color, texture));
        }
        return treeTypes.get(key);
    }
    public int getTypeCount() {
        return treeTypes.size();
    }
}

class Tree{
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void render() {
        type.render(x, y);
    }
}

class Forest{
    private final TreeTypeFactory factory= new TreeTypeFactory();
    private final List<Tree> trees = new ArrayList<>();
    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = factory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void render() {
        for (Tree tree : trees) {
            tree.render();
        }
    }
}
public class FlyWeight {
    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.plantTree(1, 2, "Oak", "Green", "Rough");
        forest.plantTree(3, 4, "Pine", "Green", "Smooth");
        forest.plantTree(5, 6, "Oak", "Green", "Rough");
        forest.render();
    }
}
