import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public HashMap<String, Node> nodes;
    public ArrayList<Creature> creatures;

    public Graph() {
        nodes = new HashMap<>();
        creatures = new ArrayList<>();
    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(description));
    }

    public void addDirectedEdge(String name1, String name2) {
        nodes.get(name1).addNeighbor(name2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public String getRandomRoom() {
        ArrayList<String> rooms = new ArrayList<>(nodes.keySet());
        if (rooms.size() > 0) {
            int random = (int) (rooms.size() * Math.random());
            return rooms.get(random);
        }
        return null;
    }

    public void addCreature(Creature creature, String room) {
        if (creature == null || room == null) System.out.println("problem");
        nodes.get(room).addCreature(creature);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public class Node {
        private HashMap<String, Node> neighbors;
        private HashMap<String, Item> items;
        private ArrayList<Creature> creatures;
        private String description;

        Node(String description) {
            neighbors = new HashMap<>();
            items = new HashMap<>();
            creatures = new ArrayList<>();
            this.description = description;
        }

        private void addNeighbor(String name) {
            neighbors.put(name, nodes.get(name));
        }

        public void addItem(String name, String description) {
            items.put(name, new Item(description));
        }

        public void addItem(String name) {
            items.put(name, new Item(null));
        }

        public void addItem(String name, Item item) {
            items.put(name, item);
        }

        public boolean destroyItem(String name) {
            return (items.remove(name) != null);
        }

        public Item getItem(String name) {
            return items.get(name);
        }

        public Item removeItem(String name) {
            return items.remove(name);
        }

        public String getItemNames() {
            String output = "";
            for (String name : items.keySet())
                output = output + ", " + name + " - " + items.get(name).getDescription();
            if (output.length() > 0)
                return output.substring(2);
            return output;
        }

        public String getDescription() {
            return description;
        }

        public HashMap<String, Item> getItems() {
            return items;
        }

        public void displayInventory() {
            for (String name : items.keySet()) {
                System.out.println(name + ", ");
            }
            System.out.println("\b\b");
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<String> getNeighbors() {
            ArrayList<String> output = new ArrayList<String>(neighbors.keySet());
            return output;
        }

        public String getNeighborsNameAndDescriptions() {
            String output = "";
            for (String name : neighbors.keySet())
                output = output + ", " + name + " - " + neighbors.get(name).description;
            if (output.length() > 0)
                return output.substring(2);
            return output;
        }

        public Node getNeighbor(String nodeName) {
            return neighbors.get(nodeName);
        }

        public void addCreature(Creature creature) {
            creatures.add(creature);
        }

        public void removeCreature(Creature creature) {
            creatures.remove(creature);
        }

        public void displayCreatures() {
            int chickens = 0;
            int wumpuses = 0;
            int popStars = 0;
            for (Creature c : creatures) {
                if (c instanceof Chicken) chickens++;
                if (c instanceof Wumpus) wumpuses++;
                if (c instanceof PopStar) popStars++;
            }
            System.out.println("There are " + chickens + " chickens");
            System.out.println("There are " + wumpuses + " wumpuses");
            System.out.println("There are " + popStars + " popStars");
        }
    }
}

